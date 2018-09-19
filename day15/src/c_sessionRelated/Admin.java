package c_sessionRelated;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Admin implements HttpSessionBindingListener{

	private String name;
	private String password;
	
	public Admin() {
		super();
	}
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void valueBound(HttpSessionBindingEvent event)  { 
    	System.out.println("对象放入sesson域中");
    
    }
	
    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	System.out.println("对象解除绑定");
    	
    }
	
}
