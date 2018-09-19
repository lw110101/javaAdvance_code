package mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//¾«È·Æ¥Åä
/**
 * Servlet implementation class Demo1
 */
@WebServlet(name="Demo1",urlPatterns="/day06/Demo1")
public class Demo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Demo1.doGet()");
	}
}
