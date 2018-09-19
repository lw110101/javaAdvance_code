package c_wrapper;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * ɾ���հ��ַ�
 * @author hasee ����7:40:17
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
			System.out.println("ԭ����"+parameterValues[i]);
			trimParameterValues[i]  = parameterValues[i].trim();
		}
		return trimParameterValues;	
	}
	/**
	 * Example2��

			ͨ��HttpServletRequestWrapper��װ��ģʽ��Ӧ�ã������������������
	
			�Զ��������װ����װ���󣬽��ַ�����ת���Ĺ�����ӵ�getParameter()������
	 */
	
	/**  
     * ʵ���Ͼ��ǵ��ñ���װ����������getParameter������ò�����Ȼ���ٽ��б���ת��  
     */  
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return value=value==null?null:convert(value);
	}

	private String convert(String target) {
		System.out.println("ת��ǰ��"+target);
		try {
			String value=new String(target.getBytes("iso8859-1"),charset);
			return value;
		} catch (UnsupportedEncodingException e) {
			return target;
		}
	}
}
