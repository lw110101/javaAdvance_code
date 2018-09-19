package b_attribute;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
@WebListener
public class MySessionListener implements HttpSessionAttributeListener {

    public MySessionListener() {
    	
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	System.out.println("MySessionListener.attributeAdded"); 
    	Object value = se.getSession().getAttribute("name");
    	System.out.println("添加的值:"+value);

    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println("MySessionListener.attributeRemoved"); 
    	Object value = se.getSession().getAttribute("name");
    	System.out.println("移除后："+value);
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("MySessionListener.attributeReplaced");  
    	Object value = se.getSession().getAttribute("name");
    	System.out.println("替换后："+value);
    }
	
}
