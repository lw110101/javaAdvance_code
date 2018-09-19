package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.IEmployeeDao;
import entity.Employee;
import util.JdbcUtils;
import util.PageBean;

/*���ݷ��ʲ�*/
public class EmployeeDao implements IEmployeeDao {

	@Override
	public void getAll(PageBean<Employee> pb) {

		// ��ȡ�ܼ�¼����װ��pb ��
		int totalCount = this.getTotalCount();
		pb.setTotalCounts(totalCount);
		// ��ҳ��
		int totalPages = totalCount / pb.getPageCount();
		pb.settotalPages(totalPages);
		/*
		 * ���⣺ jspҳ�棬�����ǰҳΪ��ҳ���ٵ����һҳ����
		 *              �����ǰҳΪĩҳ���ٵ���һҳ��ʾ�����⣡
		 * �����
		 * 	   1. �����ǰҳ <= 0;       ��ǰҳ���õ�ǰҳΪ1;
		 * 	   2. �����ǰҳ > ���ҳ����  ��ǰҳ����Ϊ���ҳ��
		 */
		// �ж�
		if(pb.getCurrent_page()<=0) {
			pb.setCurrent_page(1);
		}
		if(pb.getCurrent_page()>pb.gettotalPages()) {
			pb.setCurrent_page(pb.gettotalPages());
		}
		// ��ҳ��ѯ
		int current_page = pb.getCurrent_page();// ��ǰ��
		int pageCount = pb.getPageCount();
		int start = (current_page - 1) * pageCount;// ��ʼ��

		String sql = "select * from employee limit ?,?";

		try {
			QueryRunner qr = JdbcUtils.getQueryRunner();
			List<Employee> dataPages = qr.query(sql, new BeanListHandler<>(Employee.class), start, pageCount);
			pb.setPageData(dataPages);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from employee";
		try {
			QueryRunner qr = JdbcUtils.getQueryRunner();
			Long totalCounts = qr.query(sql, new ScalarHandler<Long>());
			return totalCounts.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
