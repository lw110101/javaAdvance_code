package a_reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import utils.JdbcUtils;

public class BaseDao<T> {

	// 保存当前运行类的实际类型定义
	private Class clazz;
	// 表名
	private String tableName;

	// 构造函数：1.获取当前运行类的参数化类型 2.获取参数化类型中的实际化类型
	public BaseDao() {
		Type type = this.getClass().getGenericSuperclass(); // 当前运行类的父类,即为参数化类型
		// 强转
		ParameterizedType pt = (ParameterizedType) type;
		// 获取参数化类型中实际类型定义
		Type[] types = pt.getActualTypeArguments();
		// 数组第一个元素即为实际类型定义
		clazz = (Class) types[0];
		// 获取表名
		tableName = clazz.getSimpleName();
	}

	/**
	 * 主键查询
	 */
	public T findById(int id) {
		/*
		 * 得到当前运行类的父类
		 */
		String sql = "select * from " + tableName + " where id=?";

		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
