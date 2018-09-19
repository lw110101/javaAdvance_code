package c_action;

public class UserAction {

	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String save() {
		System.out.println("userName:"+userName);
		return "save";
	}
	
	public String register() {
		System.out.println("register");
		return "register";
	}

	public String login() {
		System.out.println("login");
		return "login";
	}
}
