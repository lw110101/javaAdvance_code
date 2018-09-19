package d_data;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataAction_bak extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		//��ʽ1�����
	/*	//��ȡrequest
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ȡsession
		HttpSession session = request.getSession();
		//��ȡcontext
		ServletContext application= ServletActionContext.getServletContext();
//		//��ȡresponse
//		HttpServletResponse response = ServletActionContext.getResponse();
		//��ȡpageContext
//		PageContext pageContext = ServletActionContext.getPageContext();
		//��������
		request.setAttribute("request_data", "request_data_ServletActionContext");
		session.setAttribute("session_data", "session_data_ServletActionContext");
		application.setAttribute("application_data", "application_data_ServletActionContext");
		*/
		
		//��ʽ2������
		ActionContext ac = ActionContext.getContext();
		//��ȡ����request��map����
		Map<String, Object> request = ac.getContextMap();
		//��ȡ����application��map����
		Map<String, Object> application = ac.getApplication();
		//��ȡ����session��map����
		Map<String, Object> session = ac.getSession();
		//��������
		request.put("request_data", "request_data_ActionContext");
		session.put("session_data", "session_data_ActionContext");
		application.put("application_data", "application_data_ActionContext");
		
		
		//��ʽ3��
		return SUCCESS;
	}

}
