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
 * Servlet Filter implementation class MyFilter
 */
@WebFilter(filterName="/MyFilter",value="/MyWrapperServlet")
public class MyFilter implements Filter {

    public MyFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(new MyRequestWrapper((HttpServletRequest) request), response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
