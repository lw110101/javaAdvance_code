package c_exp;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.ResultSetHandler;

import utils.Column;
import utils.Id;
import utils.JdbcUtils;
import utils.Table;

/**
 * ����Ż������⣺ 1. �����ݿ������������һ�¡� 2. �ֶ������Բ�һ���� 3. ��������id
 * 
 */
public class BaseDao<T> {
	// ���������͵��ֽ���
	private Class<T> clazz;
	// ����
	private String tableName;
	// ����
	private String id_primary;
	// �����ֶ�

	public BaseDao() {
		// 1.�õ���ǰ������Ĳ��������͵�ʵ������
		Type type = this.getClass().getGenericSuperclass(); // BaseDao<T>

		// 2.ǿת�ɲ���������
		ParameterizedType pt = (ParameterizedType) type;

		// 3.��ȡʵ������
		Type[] types = pt.getActualTypeArguments();

		// 4.��һ��������Ϊadmin.calss
		clazz = (Class<T>) types[0];

		/*************** ��ȡ���� ********************/
		Table table = clazz.getAnnotation(Table.class); // ��ȡtableע����
		// ��ȡע���ֵ
		tableName = table.tableName();

		/************** ��ȡ�����������ֶ� ***************************/
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// �����������
			field.setAccessible(true);
			// ��ȡÿһ��id��ע��
			Id idAnnotation = field.getAnnotation(Id.class);
			if (idAnnotation != null) {
				/*********** �����ֶ� ************/
				Column column = field.getAnnotation(Column.class);
				id_primary = column.columnName();
				break;
			}

		}
		/***************** ���� *****************************/
		System.out.println("������" + id_primary);
		System.out.println("������" + tableName);
	}

	/**
	 * ����id��ѯ�������
	 */
	public T findById(int id) {
		try {
			// ��ѯ���
			String sql = "select * from  " + tableName + " where " + id_primary + " =?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}
	
	/**
	 * ��ѯ���еĽ����
	 */

	public List<T> getAll() {
		try {
			// ��ѯ���
			String sql = "select * from  " + tableName ;

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	
	}
}

/********************* �Զ��嵥������� **************************/
class BeanHandler<T> implements ResultSetHandler<T> {

	private Class<T> clazz;

	// ���췽��,����һ��class����
	public BeanHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	// ��װ������ķ���
	@Override
	public T handle(ResultSet rs) throws SQLException {

		try {
			// ����Ҫ��װ�Ķ���
			T t = clazz.newInstance();
			// ��ȡ��һ��
			if (rs.next()) {
				// a.��ȡҪ��װ��fileds
				Field[] fields = clazz.getDeclaredFields();
				// b.����
				for (Field field : fields) {
					// c.��ȡÿ��field������
					System.out.println("ziduan:"+field);
					String fieldName = field.getName();
					// d.��ȡÿ�������ϵ�Columnע��
					Column column = field.getAnnotation(Column.class);
					// e.��ȡע�������ֵ------�ֶ�����
					String columnName = column.columnName();
					// f.��ȡ�ֶ�ֵ
					Object columnValue = rs.getObject(columnName);
					// g.ʹ��beanUtils��װ����
					BeanUtils.setProperty(t, fieldName, columnValue);
				}
			}
			// h.����ֵ
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

/*********************�Զ����װ��������*******************************/
class BeanListHandler<T> implements ResultSetHandler<List<T>>{
	private Class<T> clazz;
	
	public BeanListHandler(Class<T> clazz) {
		this.clazz=clazz;
	}

	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list=new ArrayList<T>();
		try {
			// ��ȡ��һ��
			while (rs.next()) {
				// ����Ҫ��װ�Ķ���
				T t = clazz.newInstance();
				// a.��ȡҪ��װ��fileds
				Field[] fields = clazz.getDeclaredFields();
				// b.����
				for (Field field : fields) {
					// c.��ȡÿ��field������
					String fieldName = field.getName();
					// d.��ȡÿ�������ϵ�Columnע��
					Column column = field.getAnnotation(Column.class);
					// e.��ȡע�������ֵ------�ֶ�����
					String columnName = column.columnName();
					// f.��ȡ�ֶ�ֵ
					Object columnValue = rs.getObject(columnName);
					// g.ʹ��beanUtils��װ����
					BeanUtils.setProperty(t, fieldName, columnValue);
				}
				//������ӵ�������
				list.add(t);
			}
			// h.����ֵ
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
