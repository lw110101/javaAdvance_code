package d_dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import c_jdbc.Admin;
import util.JdbcUtil;

public class App_Query {
	private Connection conn;

	/***** 一、查询， 自定义结果集封装数据 *****/
	@Test
	public void testQuery() {
		String sql = "select * from admin where id=?";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		// 查询
		try {
			Admin admin = qr.query(conn, sql, new ResultSetHandler<Admin>() {

				@Override
				public Admin handle(ResultSet rs) throws SQLException {
					while (rs.next()) {
						Admin admin = new Admin();
						admin.setId(rs.getInt("id"));
						admin.setUserName(rs.getString("userName"));
						admin.setPassword(rs.getString("password"));
						return admin;
					}
					return null;
				}
			}, 10);
			System.out.println(admin);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/***** 二、查询， 自定义结果集封装数据 *****/
	/***
	 * ResultSetHandler ArrayHandler：把结果集中的第一行数据转成对象数组。
	 * ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
	 * 
	 * BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
	 * BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List
	 * 
	 * ColumnListHandler(列名)：将结果集中某一列的数据存放到List中。
	 * 
	 * KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map
	 * 
	 * 里，其key为指定的key。
	 * 
	 * MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
	 * MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
	 */
	@Test
	public void testBeanHandler() {

		String sql = "select * from admin where id=?";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询      BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
		try {
			Admin admin = qr.query(conn, sql, new BeanHandler<Admin>(Admin.class), 13);
			System.out.println(admin);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBeanListHandler() {

		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询       BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List
		try {
			List<Admin> list = qr.query(conn, sql, new BeanListHandler<Admin>(Admin.class));
			System.out.println(list);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testArrayHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询      ArrayHandler：把结果集中的第一行数据转成对象数组。
		try {
			Object[] obj = qr.query(conn, sql, new ArrayHandler());
			for (int i = 0; i < obj.length; i++) {
				Object object = obj[i];
				System.out.print(object+" ");
			}
			System.out.println();
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testArrayListHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询      ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
		try {
			 List<Object[]> arrayList = qr.query(conn, sql, new ArrayListHandler());
			for (Object[] objects : arrayList) {
				for (int i = 0; i < objects.length; i++) {
					Object object = objects[i];
					System.out.print(object+" ");	
				}
				System.out.println();
			}
		
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testColumnListHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询     ColumnListHandler(列名)：将结果集中某一列的数据存放到List中。
		try {
			List<String> columnValues = qr.query(conn, sql, new ColumnListHandler<String>("userName"));
			
			System.out.println(columnValues);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testKeyedHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询    KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map
		/**
		 * 以name为map2的键，以name获取的数据封装到一个map1作为map2的值，
		 * map1以列名为键，列的数据为值
		 */
		
		try {
			Map<Integer, Map<String, Object>> map = qr.query(conn, sql, new KeyedHandler<Integer>("id"));
			
			System.out.println(map);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
	 //* MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
	@Test
	public void testMapHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询  MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
		
		try {
			 Map<String, Object> admin = qr.query(conn, sql, new MapHandler());
			
			System.out.println(admin);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testMapListHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询  MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
		
		try {
			 List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());
			
			System.out.println(list);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ScalarHandler 查询返回结果记录的第一行的第一列  (在聚合函数统计的时候用)
	@Test
	public void testScalarHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// 创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询 ScalarHandler 查询返回结果记录的第一行的第一列  (在聚合函数统计的时候用)
		
		try {
			Integer id = qr.query(conn, sql, new ScalarHandler<Integer>());
			
			System.out.println(id);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
