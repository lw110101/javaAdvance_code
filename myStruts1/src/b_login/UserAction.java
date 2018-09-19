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
		//���ݱ��浽����
		ActionContext ac = ActionContext.getContext();
		//����request��map����
		Map<String, Object> request = ac.getContextMap();
		//����session��map����
		Map<String, Object> session = ac.getSession();
		//����application��map����
		Map<String, Object> application = ac.getApplication();
		////��������
		request.put("request_data", "request_data");
		session.put("session_data", "session_data");
		application.put("application_data", "application_data");
		
		return "login";
	}

}
