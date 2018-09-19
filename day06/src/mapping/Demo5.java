package mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**È±Ê¡Â·¾¶
 * Servlet implementation class Demo1
 */
@WebServlet(name="Demo5",urlPatterns="/Demo5.html")
public class Demo5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Demo5.doGet()");
	}
}
