package d_data;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	//请求数据的自动封装
	/*private String userName;
	private String password;
	private int age;
	private Date birthday;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	//业务处理
	public String register() {
		System.out.println("姓名："+userName);
		System.out.println("密码："+password);
		System.out.println("年龄："+age);
		System.out.println("出生日期："+birthday);
		return "register";
	}*/
	//对象类型
	private User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	} 

	//业务处理
	public String register() {
		System.out.println("姓名："+user.getUserName());
		System.out.println("密码："+user.getPassword());
		System.out.println("年龄："+user.getAge());
		System.out.println("出生日期："+user.getBirthday());
		System.out.println("当前时间："+user.getTime());
		return "register";
	}
}
