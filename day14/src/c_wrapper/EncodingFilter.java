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
		
		  //����������Ӧ�ַ�����    
        request.setCharacterEncoding(charset);    
        response.setCharacterEncoding(charset);    
        //�����ӵĴ���            
        HttpServletRequest req = (HttpServletRequest)request;    
            
        if(req.getMethod().equalsIgnoreCase("get"))    
        {    
            req = new MyRequestWrapper(req,charset);    
        }    
            
        System.out.println("----����"+fConfig.getFilterName()+"����");    
        //���ݸ�Ŀ��servlet��jsp��ʵ����ʱ��װ����������ã�������ԭʼ��HttpServletRequest����    
        chain.doFilter(req, response);    
            
        System.out.println("----��Ӧ��"+fConfig.getFilterName()+"����");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

}
