package guess;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 1������ͻ��µ����ֱȲ���������ֵ������ʾ����ϧ�����˵㡱��
		2������ͻ��µ����ֱȲ���������ֵС������ʾ����ϧ��С�˵㡱
		�¶��˽���100���򣬲´�Game Over�����������Ļ��ᡣ
 */
@WebServlet("/GuessServlet")
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * ��������Ϸ
	 */
	int answer;
	public void newGame() {
		Random random=new Random();
		//����һ����������
		answer=random.nextInt(30);
	}
	//��һ�η���
	public GuessServlet() {
		newGame();
	}
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		System.out.println("����������:" + answer);
		// ���մ��������
		String numberStr = request.getParameter("number");
		Integer number = null;
		// 2.1 ���û����������ת������
		if (numberStr != null || !numberStr.equals("")) {
			number = Integer.parseInt(numberStr);
		} 
		// ��Ǽ�¼��ǰ���µĴ���
		Integer times = 1;// ��ʼֵ

		// ���տͻ���ǰ���´���
		String timesStr = request.getParameter("times");
		if (timesStr != null && !timesStr.equals("")) {
			times = Integer.parseInt(timesStr) + 1;
		}
		if (times <= 5) {
			String mesg = "";
			if (number == answer) {
				mesg = "��ϲ�㣬�¶��ˣ�����100��!";
				times = null;
			} else if (number > answer) {
				mesg = "��ϧ�����˵�";
			} else if (number < answer) {
				mesg = "��ϧ��С�˵�";
			}
			// �ѵ�ǰ���µĴ������������
			request.setAttribute("times", times);
			// ����Ϣ�����������
			request.setAttribute("mesg", mesg);
		} else {
			// �����µ���������
			newGame();
			// ��Ϸ����
			response.getWriter().write("��Ϸ������<a href='" + request.getContextPath() + "/guess.jsp'>����һ��</a>");
			return;
		}
		// ת��
		request.getRequestDispatcher("/guess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
