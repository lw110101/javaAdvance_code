package d_data;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	//�������ݵ��Զ���װ
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

	//ҵ����
	public String register() {
		System.out.println("������"+userName);
		System.out.println("���룺"+password);
		System.out.println("���䣺"+age);
		System.out.println("�������ڣ�"+birthday);
		return "register";
	}*/
	//��������
	private User user;

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	} 

	//ҵ����
	public String register() {
		System.out.println("������"+user.getUserName());
		System.out.println("���룺"+user.getPassword());
		System.out.println("���䣺"+user.getAge());
		System.out.println("�������ڣ�"+user.getBirthday());
		System.out.println("��ǰʱ�䣺"+user.getTime());
		return "register";
	}
}
