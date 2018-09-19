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
 * 元数据的获取
 * 
 * @author hasee 下午10:21:58
 */
public class Demo1 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/*** 获取数据库的元数据 ***/
	@Test
	public void testDataBase_metaData() {
		try {
			conn = JdbcUtil.getConnection();
			// 获取数据库元数据

			DatabaseMetaData metaData = conn.getMetaData();
			String databaseProductName = metaData.getDatabaseProductName();
			System.out.println("数据库产品名称：" + databaseProductName);
			String userName = metaData.getUserName();
			System.out.println("使用者名称：" + userName);
			String driverName = metaData.getDriverName();
			System.out.println("驱动器名称:" + driverName);
			String driverVersion = metaData.getDriverVersion();
			System.out.println("驱动器版本是:" + driverVersion);
			System.out.println("这是："+metaData.getJDBCMajorVersion() );
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
	}

	/*** 获取参数元数据 ***/
	@Test
	public void testParams_metaData() {
		String sql = "select * from admin where id=?";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.executeQuery();
			// 获取参数元数据
			ParameterMetaData parameterMetaData = pstmt.getParameterMetaData();
			// 获取参数个个数
			int parameterCount = parameterMetaData.getParameterCount();
			System.out.println("参数个数：" + parameterCount);
			System.out.println(parameterMetaData.getParameterTypeName(1));
			int parameterType = parameterMetaData.getParameterType(1);
			System.out.println("参数类型是:"+parameterType);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt);
		}
	}

	/*** 获取结果集元数据 ***/
	@Test
	public void testResultSet_metaData() {
		String sql = "select * from admin ";
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 获取结果集元数据
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				// 获取列的个数
				int count = metaData.getColumnCount();
				// 遍历获取列的名称
				for (int i = 0; i < count; i++) {
					String columnName = metaData.getColumnName(i + 1);
					// 获取值
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
