package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Employee;
import service.IEmployeeService;
import service.impl.EmployeeService;
import util.PageBean;

/**
 * 4. 控制层开发 Servlet implementation class IndexServlet
 */

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 创建Service实例
	private IEmployeeService employeeService = new EmployeeService();
	private PageBean<Employee> pb = new PageBean<Employee>();

	private String uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1. 获取“当前页”参数； (第一次访问当前页为null)
			String currentPage = request.getParameter("currentPage");
			if (currentPage == null || currentPage.trim().length() == 0) {
				currentPage = "1";
			}
			// 传递参数
			pb.setCurrent_page(Integer.parseInt(currentPage));
			// 将pb传递到service上
			employeeService.getAll(pb);
			// 4. 保存pageBean对象，到request域中
			request.setAttribute("PageBean", pb);
			// 5. 跳转
			uri = "/WEB-INF/page.jsp";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		request.getRequestDispatcher(uri).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
