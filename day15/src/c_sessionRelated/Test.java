package c_sessionRelated;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Application Lifecycle Listener implementation class Test
 *
 */
@WebListener
public class Test implements HttpSessionBindingListener {

	private int id;
	private String name;

	public Test() {

	}

	public Test(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("Test对象放入sesson域中");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("Test对象解除绑定");
	}

}
