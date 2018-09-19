package c_wrapper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(filterName="/EncodingFilter")
public class EncodingFilter implements Filter {
	private FilterConfig fConfig;

    public EncodingFilter() {

    }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String charset="utf-8";
		
		  //设置请求响应字符编码    
        request.setCharacterEncoding(charset);    
        response.setCharacterEncoding(charset);    
        //新增加的代码            
        HttpServletRequest req = (HttpServletRequest)request;    
            
        if(req.getMethod().equalsIgnoreCase("get"))    
        {    
            req = new MyRequestWrapper(req,charset);    
        }    
            
        System.out.println("----请求被"+fConfig.getFilterName()+"过滤");    
        //传递给目标servlet或jsp的实际上时包装器对象的引用，而不是原始的HttpServletRequest对象    
        chain.doFilter(req, response);    
            
        System.out.println("----响应被"+fConfig.getFilterName()+"过滤");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

}
