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
 *监听Session销毁的动作：
 *  当服务器销毁session的时候，从在线列表集合中移除当亲的登陆用户
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
   
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	//获取session与context
    	HttpSession session = se.getSession();
    	ServletContext servletContext = session.getServletContext();
    	
    	//2. 获取Session中存储的当前登陆用户
    	Object obj = session.getAttribute("info");//
    	//获取在线列表集合
    	@SuppressWarnings("unchecked")
		List<Admin> onlineList = (List<Admin>)servletContext.getAttribute("onlineList");
    	if(onlineList!=null) {
    		onlineList.remove(obj);
    	}
    	
    }
    public void sessionCreated(HttpSessionEvent se)  { 
    
    }

	
}
