package guess;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 1）如果客户猜的数字比产生的数字值大，则提示“可惜，大了点”。
		2）如果客户猜的数字比产生的数字值小，则提示“可惜，小了点”
		猜对了奖励100百万，猜错Game Over，给玩家重玩的机会。
 */
@WebServlet("/GuessServlet")
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 产生新游戏
	 */
	int answer;
	public void newGame() {
		Random random=new Random();
		//产生一个幸运数字
		answer=random.nextInt(30);
	}
	//第一次访问
	public GuessServlet() {
		newGame();
	}
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		System.out.println("幸运数字是:" + answer);
		// 接收传入的数据
		String numberStr = request.getParameter("number");
		Integer number = null;
		// 2.1 把用户输入的数字转成整数
		if (numberStr != null || !numberStr.equals("")) {
			number = Integer.parseInt(numberStr);
		} 
		// 标记记录当前竞猜的次数
		Integer times = 1;// 初始值

		// 接收客户当前竞猜次数
		String timesStr = request.getParameter("times");
		if (timesStr != null && !timesStr.equals("")) {
			times = Integer.parseInt(timesStr) + 1;
		}
		if (times <= 5) {
			String mesg = "";
			if (number == answer) {
				mesg = "恭喜你，猜对了，奖励100万!";
				times = null;
			} else if (number > answer) {
				mesg = "可惜，大了点";
			} else if (number < answer) {
				mesg = "可惜，小了点";
			}
			// 把当前竞猜的次数放入域对象
			request.setAttribute("times", times);
			// 把信息放入域对象中
			request.setAttribute("mesg", mesg);
		} else {
			// 产生新的幸运数字
			newGame();
			// 游戏结束
			response.getWriter().write("游戏结束。<a href='" + request.getContextPath() + "/guess.jsp'>再来一盘</a>");
			return;
		}
		// 转发
		request.getRequestDispatcher("/guess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
