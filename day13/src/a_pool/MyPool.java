package a_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * �Զ������ӳ� 1���������ӳ���MyPool.java 2.����ȫ�ֱ���:�������������ǰ����������ʼ�������������ӳ� 3.���캯����ѭ����������
 * 4.�жϣ� �����ǰ������<��ʼ����������ֱ�Ӵ����ӳ�ȡ��>�Ļ�������һ���µ����� �жϣ��Ƿ�ﵽ����������� �ﵽ���׳��쳣��û�дﵽ�����������
 * 5.�رշŻ����ӳ� ������ӳص���<��ʼ�������رշŻ����ӳأ�����ֱ�ӹر�
 * 
 * @author hasee ����1:58:08
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

	// 2. ����һ���µ����ӵķ���
	private Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql:///day13", "root", "root");
			/********** ��con������� **************/
			Connection proxyCon = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), // �������,
					/*
					 * con.getClass().getInterfaces(), // ��Ŀ�������һ����������ʱ�� new
					 * Class[]{Connection.class},
					 */
					new Class[] {Connection.class}, // Ŀ�����ʵ�ֵĽӿ�
					new InvocationHandler() {
						@Override // ������con���󷽷���ʱ�� �Զ�������������
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							// ��������ֵ
							Object result = null;
							if (method.getName().equals("close")) {
								System.out.println("���񷽷���ʼִ��");
								close(conn);
								System.out.println("���񷽷�ִ�н���");
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

	// 3.��ȡ����
	public Connection getConnection() {

		// 1.�ж��Ƿ������ӣ��еĻ���ֱ��ȡ��
		if (pool.size() > 0) {
			return pool.removeFirst();
		}
		// 2.���ӳ���û�����ӣ� �жϣ����û�дﵽ�����������������
		if (currentCount < maxCount) {
			currentCount++;
			return createConnection();
		}

		// 3 �����ǰ�Ѿ��ﵽ������������׳��쳣
		throw new RuntimeException("��ǰ�����Ѿ��ﵽ���������Ŀ ��");
	}

	// �ͷ�����
	public void close(Connection conn) {
		// 4.1 �жϣ� �ص���Ŀ���С�ڳ�ʼ�����ӣ��ͷ������
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
		System.out.println("��ǰ����������:" + pool.currentCount);

		Connection conn1 = pool.getConnection();
		pool.getConnection();
		pool.getConnection();
		pool.getConnection();
		Connection conn2 =pool.getConnection();

		System.out.println("��ǰ������:" + pool.currentCount);

		// �ͷ���Դ
		conn1.close();
		conn2.close();
		System.out.println("���ӳ�:" + pool.pool.size());
		System.out.println("��ǰ����:" + pool.currentCount);

	}
}
