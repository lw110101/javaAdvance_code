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
 * BeanUtils的使用
 * 
 * @author hasee 下午8:39:14
 */
public class App {

	/** 1.封装数据 ***/
	@Test
	public void test1() throws Exception {
		Person p = new Person();
		// 方式一：copyProperty
		BeanUtils.copyProperty(p, "id", "001");
		BeanUtils.copyProperty(p, "userName", "张三");
		BeanUtils.copyProperty(p, "password", "123456");
		BeanUtils.copyProperty(p, "age", 20);

		/*
		 * //方式二： BeanUtils.setProperty(p, "id", 002); BeanUtils.setProperty(p,
		 * "userName", "李四"); BeanUtils.setProperty(p, "password", 654321);
		 * BeanUtils.setProperty(p, "age", "18");
		 */
		System.out.println(p);
	}

	/** 2.对象的拷贝 ***/
	@Test
	public void test2() throws Exception {
		Person p = new Person();

//		BeanUtils.copyProperty(p, "id", "001");
//		BeanUtils.copyProperty(p, "userName", "张三");
//		BeanUtils.setProperty(p, "password", 654321);
		BeanUtils.setProperty(p, "birthday", new Date());
		
		
		
		Person p1 = new Person();
		BeanUtils.copyProperties(p1, p);

		System.out.println(p);
		System.out.println(p1);
	}

	/** 3.map数据，拷贝到对象中 ***/
	@Test
	public void test3() throws Exception {
		Person p = new Person();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "王五");
		map.put("age", 25);

		BeanUtils.populate(p, map);

		System.out.println(p);
	}

	/** 4.自定义日期转换器 ***/
	@Test
	public void test4() throws Exception {
		// 模拟表单提交数据
		String userName = "张三";
		String password = "123456";
		String birthday = "2010-10-10";
		// 注册日期类型转换器
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
	
	/***使用Beanutils的工具转换日期****/
	@Test
	public void test5() throws Exception {
		// 模拟表单提交数据
		String userName = "张三";
		String password = "123456";
		String birthday = null;
		//使用工具实现转换
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		Person p = new Person();
		BeanUtils.copyProperty(p, "id", "001");
		BeanUtils.copyProperty(p, "userName",userName);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "birthday", birthday);
		
		System.out.println(p);
	}
}
