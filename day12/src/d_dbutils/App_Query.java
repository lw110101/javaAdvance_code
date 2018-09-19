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

	/***** һ����ѯ�� �Զ���������װ���� *****/
	@Test
	public void testQuery() {
		String sql = "select * from admin where id=?";
		conn = JdbcUtil.getConnection();
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		// ��ѯ
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

	/***** ������ѯ�� �Զ���������װ���� *****/
	/***
	 * ResultSetHandler ArrayHandler���ѽ�����еĵ�һ������ת�ɶ������顣
	 * ArrayListHandler���ѽ�����е�ÿһ�����ݶ�ת��һ�����飬�ٴ�ŵ�List�С�
	 * 
	 * BeanHandler����������еĵ�һ�����ݷ�װ��һ����Ӧ��JavaBeanʵ���С�
	 * BeanListHandler����������е�ÿһ�����ݶ���װ��һ����Ӧ��JavaBeanʵ���У���ŵ�List
	 * 
	 * ColumnListHandler(����)�����������ĳһ�е����ݴ�ŵ�List�С�
	 * 
	 * KeyedHandler(name)����������е�ÿһ�����ݶ���װ��һ��Map��ٰ���Щmap�ٴ浽һ��map
	 * 
	 * ���keyΪָ����key��
	 * 
	 * MapHandler����������еĵ�һ�����ݷ�װ��һ��Map�key��������value���Ƕ�Ӧ��ֵ��
	 * MapListHandler����������е�ÿһ�����ݶ���װ��һ��Map�Ȼ���ٴ�ŵ�List
	 */
	@Test
	public void testBeanHandler() {

		String sql = "select * from admin where id=?";
		conn = JdbcUtil.getConnection();
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ      BeanHandler����������еĵ�һ�����ݷ�װ��һ����Ӧ��JavaBeanʵ���С�
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
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ       BeanListHandler����������е�ÿһ�����ݶ���װ��һ����Ӧ��JavaBeanʵ���У���ŵ�List
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
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ      ArrayHandler���ѽ�����еĵ�һ������ת�ɶ������顣
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
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ      ArrayListHandler���ѽ�����е�ÿһ�����ݶ�ת��һ�����飬�ٴ�ŵ�List�С�
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
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ     ColumnListHandler(����)�����������ĳһ�е����ݴ�ŵ�List�С�
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
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ    KeyedHandler(name)����������е�ÿһ�����ݶ���װ��һ��Map��ٰ���Щmap�ٴ浽һ��map
		/**
		 * ��nameΪmap2�ļ�����name��ȡ�����ݷ�װ��һ��map1��Ϊmap2��ֵ��
		 * map1������Ϊ�����е�����Ϊֵ
		 */
		
		try {
			Map<Integer, Map<String, Object>> map = qr.query(conn, sql, new KeyedHandler<Integer>("id"));
			
			System.out.println(map);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//MapHandler����������еĵ�һ�����ݷ�װ��һ��Map�key��������value���Ƕ�Ӧ��ֵ��
	 //* MapListHandler����������е�ÿһ�����ݶ���װ��һ��Map�Ȼ���ٴ�ŵ�List
	@Test
	public void testMapHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ  MapHandler����������еĵ�һ�����ݷ�װ��һ��Map�key��������value���Ƕ�Ӧ��ֵ��
		
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
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ  MapListHandler����������е�ÿһ�����ݶ���װ��һ��Map�Ȼ���ٴ�ŵ�List
		
		try {
			 List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());
			
			System.out.println(list);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ScalarHandler ��ѯ���ؽ����¼�ĵ�һ�еĵ�һ��  (�ھۺϺ���ͳ�Ƶ�ʱ����)
	@Test
	public void testScalarHandler() {
 
		String sql = "select * from admin ";
		conn = JdbcUtil.getConnection();
		// ����DbUtils���Ĺ��������
		QueryRunner qr = new QueryRunner();
		//��ѯ ScalarHandler ��ѯ���ؽ����¼�ĵ�һ�еĵ�һ��  (�ھۺϺ���ͳ�Ƶ�ʱ����)
		
		try {
			Integer id = qr.query(conn, sql, new ScalarHandler<Integer>());
			
			System.out.println(id);
			DbUtils.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
