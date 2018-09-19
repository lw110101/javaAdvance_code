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
 * 4. ���Ʋ㿪�� Servlet implementation class IndexServlet
 */

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����Serviceʵ��
	private IEmployeeService employeeService = new EmployeeService();
	private PageBean<Employee> pb = new PageBean<Employee>();

	private String uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1. ��ȡ����ǰҳ�������� (��һ�η��ʵ�ǰҳΪnull)
			String currentPage = request.getParameter("currentPage");
			if (currentPage == null || currentPage.trim().length() == 0) {
				currentPage = "1";
			}
			// ���ݲ���
			pb.setCurrent_page(Integer.parseInt(currentPage));
			// ��pb���ݵ�service��
			employeeService.getAll(pb);
			// 4. ����pageBean���󣬵�request����
			request.setAttribute("PageBean", pb);
			// 5. ��ת
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
