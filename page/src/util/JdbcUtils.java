package util;



import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static DataSource dataSource;
	// ��ʼ�����ӳ�
	static {
		dataSource = new ComboPooledDataSource();
	}

	// �������Ĺ�����
	public static QueryRunner getQueryRunner() {
		// ����QueryRunner���󣬴������ӳض���
		// �ڴ���QueryRunner�����ʱ���������������Դ����
		// ��ô��ʹ��QueryRunner���󷽷���ʱ�򣬾Ͳ���Ҫ�������Ӷ���
		// ���Զ�������Դ�л�ȡ����(���ùر�����)
		return new QueryRunner(dataSource);
		
	}
}
