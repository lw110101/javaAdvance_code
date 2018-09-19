package d_data;

import java.util.Date;

public class User {

	private String userName;
	private String password;
	private int age;
	private Date birthday;
	private Date time;
	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

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

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getAge() {
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}
}
