package b_metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import util.JdbcUtil;

/**
 * Ԫ���ݵĻ�ȡ
 * 
 * @author hasee ����10:21:58
 */
public class Demo1 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/*** ��ȡ���ݿ��Ԫ���� ***/
	@Test
	public void testDataBase_metaData() {
		try {
			conn = JdbcUtil.getConnection();
			// ��ȡ���ݿ�Ԫ����

			DatabaseMetaData metaData = conn.getMetaData();
			String databaseProductName = metaData.getDatabaseProductName();
			System.out.println("���ݿ��Ʒ���ƣ�" + databaseProductName);
			String userName = metaData.getUserName();
			System.out.println("ʹ�������ƣ�" + userName);
			String driverName = metaData.getDriverName();
			System.out.println("����������:" + driverName);
			String driverVersion = metaData.getDriverVersion();
			System.out.println("�������汾��:" + driverVersion);
			System.out.println("���ǣ�"+metaData.getJDBCMajorVersion() );
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
	}

	/*** ��ȡ����Ԫ���� ***/
	@Test
	public void testParams_metaData() {
		String sql = "select * from admin where id=?";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.executeQuery();
			// ��ȡ����Ԫ����
			ParameterMetaData parameterMetaData = pstmt.getParameterMetaData();
			// ��ȡ����������
			int parameterCount = parameterMetaData.getParameterCount();
			System.out.println("����������" + parameterCount);
			System.out.println(parameterMetaData.getParameterTypeName(1));
			int parameterType = parameterMetaData.getParameterType(1);
			System.out.println("����������:"+parameterType);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
	}

	/*** ��ȡ�����Ԫ���� ***/
	@Test
	public void testResultSet_metaData() {
		String sql = "select * from admin ";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// ��ȡ�����Ԫ����
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				// ��ȡ�еĸ���
				int count = metaData.getColumnCount();
				// ������ȡ�е�����
				for (int i = 0; i < count; i++) {
					String columnName = metaData.getColumnName(i + 1);
					// ��ȡֵ
					Object value = rs.getObject(columnName);
					String columnType = metaData.getColumnTypeName(i + 1);
					System.out.println(columnType + ":" + columnName + " =" + value);
				}
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
}
