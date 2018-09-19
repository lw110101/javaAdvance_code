package mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Ä£ºýÆ¥Åä
 * Servlet implementation class Demo1
 */
@WebServlet(name="Demo2",urlPatterns="/Demo2")
public class Demo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Demo2.doGet()");
	}
}
