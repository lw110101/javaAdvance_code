package a_life;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MyHttpSessionListener
 *
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    public MyHttpSessionListener() {
    
    }

    public void sessionCreated(HttpSessionEvent se)  { 
    	
    	System.out.println("MyHttpSessionListener is created");
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("MyHttpSessionListener is destroyed");
    
    }
	
}
