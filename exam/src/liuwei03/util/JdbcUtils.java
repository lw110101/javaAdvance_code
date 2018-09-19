package liuwei03.util;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static DataSource dataSource;
	// 初始化连接池
	static {
		dataSource = new ComboPooledDataSource();
	}

	// 创建核心工具类
	public static QueryRunner getQueryRunner() {
		// 创建QueryRunner对象，传入连接池对象
		// 在创建QueryRunner对象的时候，如果传入了数据源对象；
		// 那么在使用QueryRunner对象方法的时候，就不需要传入连接对象；
		// 会自动从数据源中获取连接(不用关闭连接)
		return new QueryRunner(dataSource);
		
	}
}
