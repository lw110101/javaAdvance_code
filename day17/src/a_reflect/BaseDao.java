package a_reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import utils.JdbcUtils;

public class BaseDao<T> {

	// ���浱ǰ�������ʵ�����Ͷ���
	private Class clazz;
	// ����
	private String tableName;

	// ���캯����1.��ȡ��ǰ������Ĳ��������� 2.��ȡ�����������е�ʵ�ʻ�����
	public BaseDao() {
		Type type = this.getClass().getGenericSuperclass(); // ��ǰ������ĸ���,��Ϊ����������
		// ǿת
		ParameterizedType pt = (ParameterizedType) type;
		// ��ȡ������������ʵ�����Ͷ���
		Type[] types = pt.getActualTypeArguments();
		// �����һ��Ԫ�ؼ�Ϊʵ�����Ͷ���
		clazz = (Class) types[0];
		// ��ȡ����
		tableName = clazz.getSimpleName();
	}

	/**
	 * ������ѯ
	 */
	public T findById(int id) {
		/*
		 * �õ���ǰ������ĸ���
		 */
		String sql = "select * from " + tableName + " where id=?";

		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
