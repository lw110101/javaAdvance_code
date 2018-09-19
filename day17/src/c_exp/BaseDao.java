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
 * 解决优化的问题： 1. 当数据库表名与类名不一致、 2. 字段与属性不一样、 3. 主键不叫id
 * 
 */
public class BaseDao<T> {
	// 参数化类型的字节码
	private Class<T> clazz;
	// 表名
	private String tableName;
	// 主键
	private String id_primary;
	// 其他字段

	public BaseDao() {
		// 1.拿到当前运行类的参数化类型的实际类型
		Type type = this.getClass().getGenericSuperclass(); // BaseDao<T>

		// 2.强转成参数化类型
		ParameterizedType pt = (ParameterizedType) type;

		// 3.获取实际类型
		Type[] types = pt.getActualTypeArguments();

		// 4.第一个参数即为admin.calss
		clazz = (Class<T>) types[0];

		/*************** 获取表名 ********************/
		Table table = clazz.getAnnotation(Table.class); // 获取table注解类
		// 获取注解的值
		tableName = table.tableName();

		/************** 获取主键与其他字段 ***************************/
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 设置允许访问
			field.setAccessible(true);
			// 获取每一个id的注解
			Id idAnnotation = field.getAnnotation(Id.class);
			if (idAnnotation != null) {
				/*********** 其他字段 ************/
				Column column = field.getAnnotation(Column.class);
				id_primary = column.columnName();
				break;
			}

		}
		/***************** 测试 *****************************/
		System.out.println("主键：" + id_primary);
		System.out.println("表名：" + tableName);
	}

	/**
	 * 根据id查询单个结果
	 */
	public T findById(int id) {
		try {
			// 查询语句
			String sql = "select * from  " + tableName + " where " + id_primary + " =?";

			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}
	
	/**
	 * 查询所有的结果集
	 */

	public List<T> getAll() {
		try {
			// 查询语句
			String sql = "select * from  " + tableName ;

			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	
	}
}

/********************* 自定义单个结果集 **************************/
class BeanHandler<T> implements ResultSetHandler<T> {

	private Class<T> clazz;

	// 构造方法,传入一个class参数
	public BeanHandler(Class<T> clazz) {
		this.clazz = clazz;
	}

	// 封装结果集的方法
	@Override
	public T handle(ResultSet rs) throws SQLException {

		try {
			// 创建要封装的对象
			T t = clazz.newInstance();
			// 读取下一行
			if (rs.next()) {
				// a.获取要封装的fileds
				Field[] fields = clazz.getDeclaredFields();
				// b.遍历
				for (Field field : fields) {
					// c.获取每个field的名称
					System.out.println("ziduan:"+field);
					String fieldName = field.getName();
					// d.获取每个属性上的Column注解
					Column column = field.getAnnotation(Column.class);
					// e.获取注解的属性值------字段名称
					String columnName = column.columnName();
					// f.获取字段值
					Object columnValue = rs.getObject(columnName);
					// g.使用beanUtils封装对象
					BeanUtils.setProperty(t, fieldName, columnValue);
				}
			}
			// h.返回值
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

/*********************自定义封装多个结果集*******************************/
class BeanListHandler<T> implements ResultSetHandler<List<T>>{
	private Class<T> clazz;
	
	public BeanListHandler(Class<T> clazz) {
		this.clazz=clazz;
	}

	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list=new ArrayList<T>();
		try {
			// 读取下一行
			while (rs.next()) {
				// 创建要封装的对象
				T t = clazz.newInstance();
				// a.获取要封装的fileds
				Field[] fields = clazz.getDeclaredFields();
				// b.遍历
				for (Field field : fields) {
					// c.获取每个field的名称
					String fieldName = field.getName();
					// d.获取每个属性上的Column注解
					Column column = field.getAnnotation(Column.class);
					// e.获取注解的属性值------字段名称
					String columnName = column.columnName();
					// f.获取字段值
					Object columnValue = rs.getObject(columnName);
					// g.使用beanUtils封装对象
					BeanUtils.setProperty(t, fieldName, columnValue);
				}
				//对象添加到集合中
				list.add(t);
			}
			// h.返回值
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
