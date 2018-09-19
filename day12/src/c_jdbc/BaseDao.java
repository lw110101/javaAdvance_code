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
	 * ͨ�� �ĸ��·���
	 * 
	 * @param sql
	 *            ���µ�sql���(update/insert/delete)
	 * @param paramsValue
	 *            sql�����ռλ����Ӧ��ֵ(���û��ռλ��������null)
	 */
	public void update(String sql, Object[] paramsValue) {
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ����ִ��sql����statement����
			pstmt = conn.prepareStatement(sql);
			// ��ȡ��������
			int parameterCount = pstmt.getParameterMetaData().getParameterCount();
			// ����ռλ��������ֵ
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < parameterCount; i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			// ִ�и���
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}

	/**
	 * ͨ�� �ĸ��·���
	 * 
	 * @param sql
	 *            ���µ�sql���(update/insert/delete)
	 * @param paramsValue
	 *            sql�����ռλ����Ӧ��ֵ(���û��ռλ��������null)
	 */
	public <T> List<T> query(String sql, Object[] paramsValue, Class<T> clazz) {
		try {
			List<T> list = new ArrayList<T>();
			//����
			T t=null;
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ����ִ��sql����statement����
			pstmt = conn.prepareStatement(sql);
			// ��ȡ��������
			int parameterCount = pstmt.getParameterMetaData().getParameterCount();
			// ����ռλ��������ֵ
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < parameterCount; i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			// ִ�в�ѯ
			rs=pstmt.executeQuery();
			//��ȡ�����Ԫ����
			ResultSetMetaData metaData = rs.getMetaData();
			//---> ��ȡ�еĸ���
			int columnCount = metaData.getColumnCount();
			//���������
			while(rs.next()) {
				//Ҫ��װ����
				t=clazz.newInstance();
				//����ÿһ��ÿһ��
				for (int i = 0; i < columnCount; i++) {
					//��ȡ����
					String columnName = metaData.getColumnName(i+1);
					//��ȡֵ
					Object columnValue = rs.getObject(columnName);
					//ʹ��beantils�����װ����
					BeanUtils.copyProperty(t, columnName, columnValue);	
				}
				// �ѷ�װ��ϵĶ�����ӵ�list������
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
