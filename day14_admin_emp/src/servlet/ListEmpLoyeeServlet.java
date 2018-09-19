package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Employee;
import service.IEmployeeService;
import service.impl.EmployeeService;

/**
 * Servlet implementation class ListEmpLoyeesServlet
 */
@WebServlet("/ListEmpLoyeeServlet")
public class ListEmpLoyeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Serviceʵ��
	private IEmployeeService employeeService = new EmployeeService();
	// ��ת��Դ
	private String uri;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Employee> list = employeeService.getEmployees();
			request.setAttribute("list", list);
			uri = "/listEmployees.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			uri = "/error/error.jsp";
		}
		// ת��
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
