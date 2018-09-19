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

/*数据访问层*/
public class EmployeeDao implements IEmployeeDao {

	@Override
	public void getAll(PageBean<Employee> pb) {

		// 获取总记录数封装到pb 中
		int totalCount = this.getTotalCount();
		pb.setTotalCounts(totalCount);
		// 总页数
		int totalPages = totalCount / pb.getPageCount();
		pb.settotalPages(totalPages);
		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0;       当前页设置当前页为1;
		 * 	   2. 如果当前页 > 最大页数；  当前页设置为最大页数
		 */
		// 判断
		if(pb.getCurrent_page()<=0) {
			pb.setCurrent_page(1);
		}
		if(pb.getCurrent_page()>pb.gettotalPages()) {
			pb.setCurrent_page(pb.gettotalPages());
		}
		// 分页查询
		int current_page = pb.getCurrent_page();// 当前行
		int pageCount = pb.getPageCount();
		int start = (current_page - 1) * pageCount;// 起始行

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
