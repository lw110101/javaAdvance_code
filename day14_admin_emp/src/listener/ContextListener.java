package listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import entity.Admin;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    public ContextListener() {
   
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	 ServletContext servletContext = sce.getServletContext();
    	 Object onlineList =servletContext.getAttribute("onlineList");
    	
    	if(onlineList!=null) {
    		servletContext.removeAttribute("onlineList");
    	}
   
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    //将登陆在线用户存储在list集合中
    	List<Admin> onlineList=new ArrayList<Admin>();
    	//放到域对象中
    	sce.getServletContext().setAttribute("onlineList", onlineList);
    	
    }
	
}
