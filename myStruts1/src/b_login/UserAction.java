package b_login;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private User user=new User();
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	public String login() throws Exception {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		//数据保存到域中
		ActionContext ac = ActionContext.getContext();
		//代表request的map集合
		Map<String, Object> request = ac.getContextMap();
		//代表session的map集合
		Map<String, Object> session = ac.getSession();
		//代表application的map集合
		Map<String, Object> application = ac.getApplication();
		////保存数据
		request.put("request_data", "request_data");
		session.put("session_data", "session_data");
		application.put("application_data", "application_data");
		
		return "login";
	}

}
