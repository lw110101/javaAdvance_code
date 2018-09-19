package cookie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HistServlet
 */
@WebServlet("/HistServlet")
public class HistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// 获取当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd__hh:mm:ss");
		String curTime = format.format(new Date());
		// 获取cookie
		Cookie[] cookies = request.getCookies();
		String lastTime = null;
		// 第n次访问
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lastTime")) {
					lastTime = cookie.getValue();
					response.getWriter().write("欢迎回来，上次访问时间是:" + lastTime + "当前时间是:" + curTime);
					cookie.setValue(curTime);
					cookie.setMaxAge(1 * 30 * 24 * 60 * 60);
					response.addCookie(cookie);
					break;
				}
			}
		}
		// 第一次访问
		if (cookies == null || lastTime == null) {
			response.getWriter().write("你是首次访问，当前时间是:" + curTime);
			Cookie cookie = new Cookie("lastTime", curTime);
			cookie.setMaxAge(1 * 30 * 24 * 60 * 60);
			response.addCookie(cookie);
		}

	}

}
