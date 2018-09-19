package b_attribute;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 *
 */
@WebListener
public class MyContextListener implements ServletContextAttributeListener {

    public MyContextListener() {
   
    }

    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	System.out.println("MyContextListener.attributeAdded");
    	Object value = scae.getServletContext().getAttribute("password");
    	System.out.println("添加的值："+value);
    
    }
    
    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
    	System.out.println("MyContextListener.attributeRemoved");
    	Object value = scae.getServletContext().getAttribute("password");
    	System.out.println("移除后的值："+value);
    }

    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
    	System.out.println("MyContextListener.attributeReplaced");
    	Object value = scae.getServletContext().getAttribute("password");
    	System.out.println("替换的值："+value);
    }
	
}
