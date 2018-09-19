package junit;

import java.util.Date;

public class Person {

	private int id;
	
	private String name;
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	private Date birthday;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Person() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	
		return "±àºÅ:"+this.id+" ĞÕÃû:"+this.name+"  ÉúÈÕ:"+this.birthday;
	}
	
}
