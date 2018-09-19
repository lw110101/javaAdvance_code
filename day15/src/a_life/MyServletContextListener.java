package a_life;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("MyServletContextListener is destroyed");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("MyServletContextListener is Initializzed");
    }
	
}
