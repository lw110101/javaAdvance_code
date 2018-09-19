package listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entity.Admin;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *����Session���ٵĶ�����
 *  ������������session��ʱ�򣬴������б������Ƴ����׵ĵ�½�û�
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
   
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	//��ȡsession��context
    	HttpSession session = se.getSession();
    	ServletContext servletContext = session.getServletContext();
    	
    	//2. ��ȡSession�д洢�ĵ�ǰ��½�û�
    	Object obj = session.getAttribute("info");//
    	//��ȡ�����б���
    	@SuppressWarnings("unchecked")
		List<Admin> onlineList = (List<Admin>)servletContext.getAttribute("onlineList");
    	if(onlineList!=null) {
    		onlineList.remove(obj);
    	}
    	
    }
    public void sessionCreated(HttpSessionEvent se)  { 
    
    }

	
}
