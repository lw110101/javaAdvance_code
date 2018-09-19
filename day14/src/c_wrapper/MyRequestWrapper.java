package c_wrapper;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 删除空白字符
 * @author hasee 下午7:40:17
 */
public class MyRequestWrapper extends HttpServletRequestWrapper{
	private String charset = "UTF-8"; 

	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public MyRequestWrapper(HttpServletRequest request, String charset) {
		super(request);
		this.charset = charset;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = super.getParameterValues(name);
		
		if(parameterValues==null) {
			return null;
		}
		
		String[] trimParameterValues=new String[parameterValues.length];
		
		for (int i = 0; i < parameterValues.length; i++) {
			System.out.println("原来："+parameterValues[i]);
			trimParameterValues[i]  = parameterValues[i].trim();
		}
		return trimParameterValues;	
	}
	/**
	 * Example2：

			通过HttpServletRequestWrapper（装饰模式的应用）来解决中文乱码问题
	
			自定义请求包装器包装请求，将字符编码转换的工作添加到getParameter()方法中
	 */
	
	/**  
     * 实际上就是调用被包装的请求对象的getParameter方法获得参数，然后再进行编码转换  
     */  
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return value=value==null?null:convert(value);
	}

	private String convert(String target) {
		System.out.println("转换前："+target);
		try {
			String value=new String(target.getBytes("iso8859-1"),charset);
			return value;
		} catch (UnsupportedEncodingException e) {
			return target;
		}
	}
}
