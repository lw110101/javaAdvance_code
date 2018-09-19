package c_exp;

import utils.Column;
import utils.Id;
import utils.Table;

/*
 * 	t_id INT 
	t_userName 
	t_PASSWORD
*/
@Table(tableName="admintest")
public class Admin {

	//Ö÷¼ü
	@Id
	@Column(columnName="t_id")
	private int id;
	//×Ö¶Î
	@Column(columnName="t_userName")
	private String userName;
	
	@Column(columnName="t_PASSWORD")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
}
