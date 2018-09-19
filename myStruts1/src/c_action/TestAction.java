package c_action;

import com.opensymphony.xwork2.Action;

public class TestAction implements Action {

	@Override
	public String execute() throws Exception {
		System.out.println("实现action接口的方式开发action");
		return "success";
	}

}
