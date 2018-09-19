package a_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 自定义连接池 1、创建连接池类MyPool.java 2.构造全局变量:最大连接数、当前连接数、初始化连接数、连接池 3.构造函数，循环创建连接
 * 4.判断： 如果当前连接数<初始化连接数，直接从连接池取，>的话，创建一个新的连接 判断，是否达到最大连接数； 达到，抛出异常；没有达到最大连接数，
 * 5.关闭放回连接池 如果连接池的数<初始化数，关闭放回连接池，否则直接关闭
 * 
 * @author hasee 下午1:58:08
 */
public class MyPool {
	private int maxCount = 6;
	private int currentCount = 0;
	private int initCount = 3;
	private LinkedList<Connection> pool = new LinkedList<Connection>();

	public MyPool() {
		for (int i = 0; i < 3; i++) {
			Connection conn = createConnection();
			currentCount++;
			pool.add(conn);
		}

	}

	// 2. 创建一个新的连接的方法
	private Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///day13", "root", "root");
			/********** 对con对象代理 **************/
			Connection proxyCon = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), // 类加载器,
					/*
					 * con.getClass().getInterfaces(), // 当目标对象是一个具体的类的时候 new
					 * Class[]{Connection.class},
					 */
					new Class[] {Connection.class}, // 目标对象实现的接口
					new InvocationHandler() {
						@Override // 当调用con对象方法的时候， 自动触发事务处理器
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							// 方法返回值
							Object result = null;
							if (method.getName().equals("close")) {
								System.out.println("事务方法开始执行");
								close(conn);
								System.out.println("事务方法执行结束");
							} else {
								result = method.invoke(proxy, args);
							}
							return result;
						}
					});
			return proxyCon;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	// 3.获取连接
	public Connection getConnection() {

		// 1.判断是否有连接，有的话，直接取出
		if (pool.size() > 0) {
			return pool.removeFirst();
		}
		// 2.连接池中没有连接： 判断，如果没有达到最大连接数，创建；
		if (currentCount < maxCount) {
			currentCount++;
			return createConnection();
		}

		// 3 如果当前已经达到最大连接数，抛出异常
		throw new RuntimeException("当前连接已经达到最大连接数目 ！");
	}

	// 释放连接
	public void close(Connection conn) {
		// 4.1 判断： 池的数目如果小于初始化连接，就放入池中
		if (pool.size() < initCount) {
			pool.addLast(conn);
		} else {
			try {
				currentCount--;
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		MyPool pool = new MyPool();
		System.out.println("当前连接数量是:" + pool.currentCount);

		Connection conn1 = pool.getConnection();
		pool.getConnection();
		pool.getConnection();
		pool.getConnection();
		Connection conn2 =pool.getConnection();

		System.out.println("当前连接数:" + pool.currentCount);

		// 释放资源
		conn1.close();
		conn2.close();
		System.out.println("连接池:" + pool.pool.size());
		System.out.println("当前连接:" + pool.currentCount);

	}
}
