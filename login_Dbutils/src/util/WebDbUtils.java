package util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebDbUtils {

	public static <T> T copyToBean_old(HttpServletRequest request,Class<T> clazz) {
		//��������
			try {
				T t=clazz.newInstance();
				//��ȡ�������������
				Enumeration<String> parameterNames = request.getParameterNames();
				//������ȡֵ
				while(parameterNames.hasMoreElements()) {
					String name = parameterNames.nextElement();
					String value = request.getParameter(name);
					BeanUtils.setProperty(t, name, value);
				}
				return t;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
	
	/**
	 * �����������ݵķ�װ
	 */
	public static <T> T copyToBean(HttpServletRequest request,Class<T> clazz) {
		//��������
			try {
				T t=clazz.newInstance();
				BeanUtils.populate(t, request.getParameterMap());
				return t;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
}
