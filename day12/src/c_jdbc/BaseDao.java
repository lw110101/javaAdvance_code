package c_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import util.JdbcUtil;

public class BaseDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 通用 的更新方法
	 * 
	 * @param sql
	 *            更新的sql语句(update/insert/delete)
	 * @param paramsValue
	 *            sql语句中占位符对应的值(如果没有占位符，传入null)
	 */
	public void update(String sql, Object[] paramsValue) {
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 创建执行sql语句的statement对象
			pstmt = conn.prepareStatement(sql);
			// 获取参数个数
			int parameterCount = pstmt.getParameterMetaData().getParameterCount();
			// 设置占位符参数的值
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < parameterCount; i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			// 执行更新
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}

	/**
	 * 通用 的更新方法
	 * 
	 * @param sql
	 *            更新的sql语句(update/insert/delete)
	 * @param paramsValue
	 *            sql语句中占位符对应的值(如果没有占位符，传入null)
	 */
	public <T> List<T> query(String sql, Object[] paramsValue, Class<T> clazz) {
		try {
			List<T> list = new ArrayList<T>();
			//对象
			T t=null;
			// 获取连接
			conn = JdbcUtil.getConnection();
			// 创建执行sql语句的statement对象
			pstmt = conn.prepareStatement(sql);
			// 获取参数个数
			int parameterCount = pstmt.getParameterMetaData().getParameterCount();
			// 设置占位符参数的值
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < parameterCount; i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			// 执行查询
			rs=pstmt.executeQuery();
			//获取结果集元数据
			ResultSetMetaData metaData = rs.getMetaData();
			//---> 获取列的个数
			int columnCount = metaData.getColumnCount();
			//遍历结果集
			while(rs.next()) {
				//要封装对象
				t=clazz.newInstance();
				//遍历每一行每一列
				for (int i = 0; i < columnCount; i++) {
					//获取列名
					String columnName = metaData.getColumnName(i+1);
					//获取值
					Object columnValue = rs.getObject(columnName);
					//使用beantils组件封装数据
					BeanUtils.copyProperty(t, columnName, columnValue);	
				}
				// 把封装完毕的对象，添加到list集合中
				list.add(t);
			}
			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
}
