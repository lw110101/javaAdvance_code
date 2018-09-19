package d_data;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class DataAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

	private static final long serialVersionUID = 1L;
	// struts����ʱ����ɴ���request��mapע��
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public String execute() throws Exception {
		// ��ʽ3��ʵ�ֽӿ�
		// ��������
		request.put("request_data", "request_data_RequestAware");
		session.put("session_data", "session_data_SessionAware");
		application.put("application_data", "application_data_ApplicationAware");
		return SUCCESS;
	}

}
