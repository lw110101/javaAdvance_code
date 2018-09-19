package b_attribute;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyRequestAttributeListener implements ServletRequestAttributeListener {
    public MyRequestAttributeListener() {
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae)  { 
    	System.out.println("MyRequestAttributeListener.attributeRemoved");
    	Object value = srae.getServletRequest().getAttribute("cn");
    	System.out.println("移除后："+value);
    }

    public void attributeAdded(ServletRequestAttributeEvent srae)  { 
    	System.out.println("MyRequestAttributeListener.attributeAdded");
    	Object value = srae.getServletRequest().getAttribute("cn");
    	System.out.println("添加的值："+value);
    	
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
    	System.out.println("MyRequestAttributeListener.attributeReplaced");  	
    	Object value = srae.getServletRequest().getAttribute("cn");
    	System.out.println("替换后的值："+value);
    }
	
}
