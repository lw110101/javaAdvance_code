package util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebDbUtils {

	public static <T> T copyToBean_old(HttpServletRequest request,Class<T> clazz) {
		//创建对象
			try {
				T t=clazz.newInstance();
				//获取请求参数的名称
				Enumeration<String> parameterNames = request.getParameterNames();
				//遍历获取值
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
	 * 处理请求数据的封装
	 */
	public static <T> T copyToBean(HttpServletRequest request,Class<T> clazz) {
		//创建对象
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
