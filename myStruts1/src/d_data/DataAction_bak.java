package d_data;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataAction_bak extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		//方式1：耦合
	/*	//获取request
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取session
		HttpSession session = request.getSession();
		//获取context
		ServletContext application= ServletActionContext.getServletContext();
//		//获取response
//		HttpServletResponse response = ServletActionContext.getResponse();
		//获取pageContext
//		PageContext pageContext = ServletActionContext.getPageContext();
		//保存数据
		request.setAttribute("request_data", "request_data_ServletActionContext");
		session.setAttribute("session_data", "session_data_ServletActionContext");
		application.setAttribute("application_data", "application_data_ServletActionContext");
		*/
		
		//方式2：解耦
		ActionContext ac = ActionContext.getContext();
		//获取代表request的map集合
		Map<String, Object> request = ac.getContextMap();
		//获取代表application的map集合
		Map<String, Object> application = ac.getApplication();
		//获取代表session的map集合
		Map<String, Object> session = ac.getSession();
		//保存数据
		request.put("request_data", "request_data_ActionContext");
		session.put("session_data", "session_data_ActionContext");
		application.put("application_data", "application_data_ActionContext");
		
		
		//方式3：
		return SUCCESS;
	}

}
