package a_beanutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

/**
 * BeanUtils��ʹ��
 * 
 * @author hasee ����8:39:14
 */
public class App {

	/** 1.��װ���� ***/
	@Test
	public void test1() throws Exception {
		Person p = new Person();
		// ��ʽһ��copyProperty
		BeanUtils.copyProperty(p, "id", "001");
		BeanUtils.copyProperty(p, "userName", "����");
		BeanUtils.copyProperty(p, "password", "123456");
		BeanUtils.copyProperty(p, "age", 20);

		/*
		 * //��ʽ���� BeanUtils.setProperty(p, "id", 002); BeanUtils.setProperty(p,
		 * "userName", "����"); BeanUtils.setProperty(p, "password", 654321);
		 * BeanUtils.setProperty(p, "age", "18");
		 */
		System.out.println(p);
	}

	/** 2.����Ŀ��� ***/
	@Test
	public void test2() throws Exception {
		Person p = new Person();

//		BeanUtils.copyProperty(p, "id", "001");
//		BeanUtils.copyProperty(p, "userName", "����");
//		BeanUtils.setProperty(p, "password", 654321);
		BeanUtils.setProperty(p, "birthday", new Date());
		
		
		
		Person p1 = new Person();
		BeanUtils.copyProperties(p1, p);

		System.out.println(p);
		System.out.println(p1);
	}

	/** 3.map���ݣ������������� ***/
	@Test
	public void test3() throws Exception {
		Person p = new Person();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "����");
		map.put("age", 25);

		BeanUtils.populate(p, map);

		System.out.println(p);
	}

	/** 4.�Զ�������ת���� ***/
	@Test
	public void test4() throws Exception {
		// ģ����ύ����
		String userName = "����";
		String password = "123456";
		String birthday = "2010-10-10";
		// ע����������ת����
		ConvertUtils.register(new Converter() {

			@Override  @SuppressWarnings("rawtypes") 
			public Object convert(Class type, Object value) {
				if (Date.class != type) {
					return null;
				}
				if (value == null || value.toString().trim().length() == 0) {
					return null;
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					Date date = format.parse(value.toString());
					return date;
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}	
			}
		}, Date.class);
		Person p = new Person();
		BeanUtils.copyProperty(p, "id", "001");
		BeanUtils.copyProperty(p, "userName",userName);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "birthday", birthday);
		
		System.out.println(p);
	}
	
	/***ʹ��Beanutils�Ĺ���ת������****/
	@Test
	public void test5() throws Exception {
		// ģ����ύ����
		String userName = "����";
		String password = "123456";
		String birthday = null;
		//ʹ�ù���ʵ��ת��
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		Person p = new Person();
		BeanUtils.copyProperty(p, "id", "001");
		BeanUtils.copyProperty(p, "userName",userName);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "birthday", birthday);
		
		System.out.println(p);
	}
}
