package a_hello;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		System.out.println("��ð���");
		return SUCCESS;
	}

}
