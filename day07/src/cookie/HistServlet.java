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
		// ��ȡ��ǰʱ��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd__hh:mm:ss");
		String curTime = format.format(new Date());
		// ��ȡcookie
		Cookie[] cookies = request.getCookies();
		String lastTime = null;
		// ��n�η���
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lastTime")) {
					lastTime = cookie.getValue();
					response.getWriter().write("��ӭ�������ϴη���ʱ����:" + lastTime + "��ǰʱ����:" + curTime);
					cookie.setValue(curTime);
					cookie.setMaxAge(1 * 30 * 24 * 60 * 60);
					response.addCookie(cookie);
					break;
				}
			}
		}
		// ��һ�η���
		if (cookies == null || lastTime == null) {
			response.getWriter().write("�����״η��ʣ���ǰʱ����:" + curTime);
			Cookie cookie = new Cookie("lastTime", curTime);
			cookie.setMaxAge(1 * 30 * 24 * 60 * 60);
			response.addCookie(cookie);
		}

	}

}
