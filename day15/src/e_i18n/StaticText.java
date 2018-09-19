package e_i18n;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * 国际化
 * 
 * @author hasee 下午5:48:55
 */
public class StaticText {
	// 国际化 - 静态数据
	@Test
	public void testI18N() {
		ResourceBundle bundle = ResourceBundle.getBundle("myproperties", Locale.US);

		String name = bundle.getString("userName");

		String pwd = bundle.getString("password");

		System.out.println(name + "  " + pwd);

	}

	// 动态文本的国际化
	@Test // 0.概述
	public void testI18N1() {
		/*
		 * 数字相关的国际化
		 */
		NumberFormat.getInstance();
		// 多种用途
		NumberFormat.getNumberInstance();
		// 货币
		NumberFormat.getCurrencyInstance();
		// 整数
		NumberFormat.getIntegerInstance();
		// 百分比
		NumberFormat.getPercentInstance();
		/*
		 * 日期的格式化
		 */
		//DateFormat.getTimeInstance(style, aLocale);
		// DateFormat.getDateTimeInstance(dateStyle, timeStyle, aLocale);
	}

	@Test // 动态货币
	public void testI18N2() {
		// 模拟语言环境
		Locale locale = Locale.US;
		// 准备数据
		double num = 1000;
		//// 工具类
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
		// 国际化货币
		String value = currencyInstance.format(num);
		// 测试
		System.out.println(value);
	}

	@Test // 其他数字相关
	public void testI18N3() {
		// 模拟语言环境
		Locale locale = Locale.US;
		// 准备数据
		double num = 1000.12;
		//// 工具类
		NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
		String value = numberInstance.format(num);
		// 测试
		System.out.println(value);
		// 整数
		NumberFormat integerInstance = NumberFormat.getIntegerInstance(locale);
		String value1 = integerInstance.format(num);
		// 测试
		System.out.println(value1);

	}

	// 面试题： 代码计算： $100 * 10
	@Test
	public void testI18N5() {
		// 模拟语言环境
		Locale locale = Locale.US;
	//// 工具类
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
		// 准备数据
		String str="$100";
		int num=10;
		try {
			//将货币转换成数字
			Number number= currencyInstance.parse(str);
			int intValue = number.intValue();
			//将数字转换成货币 
			String result = currencyInstance.format(intValue*=num);
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	//2. 国际化数值
	@Test
	public void testI18N6() throws Exception {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
		String str = nf.format(1000000000);
		System.out.println(str);
	} 

	/*
	 * 日期 格式 
	 * 		FULL: 2018年5月31日 星期四
	 *  	LONG: 2018年5月31日 
	 *  	MEDIUM: 2018-5-31 
	 *  	SHORT: 18-5-31
	 * 
	 * 时间 格式 
	 * 		FULL: 下午08时41分34秒 CST 
	 * 		LONG: 下午08时43分16秒 
	 * 		MEDIUM: 20:43:46 
	 * 		SHORT: 下午8:44
	 * 
	 */
	@Test
	public void testI18N4() {
		// 模拟语言环境
		Locale locale = Locale.getDefault();
		// 工具类
		DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
		// 准备数据
		String date = dateTimeInstance.format(new Date());
		// 测试
		System.out.println(date);
	}
	// 面试2： 请将时间值：09-11-28 上午10时25分39秒 CST，反向解析成一个date对象。
	@Test
	public void testI18N7() throws ParseException {
		String time="09-11-28 上午10时25分39秒 CST";
		// 工具类
		DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.CHINA);
		Date date = dateTimeInstance.parse(time);
		System.out.println(date);
		
	}
	@Test  //MessageFormat(动态文本)
	public void testI18N8() throws Exception  {
		//String pattern="On {0}, a hurricance destroyed {１} houses and caused {２} of damage.";
		
		String pattern = "At {0, time, short} on {0, date}, a hurricance destroyed {1} houses and caused {2, number, currency} of damage.";


		//工具类
		MessageFormat mf=new MessageFormat(pattern, Locale.CHINA);
		//准备参数数组
		String datetime = "Jul 3, 1998 12:30 PM";
		Date date=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT,Locale.US).parse(datetime);		
		//执行格式化操作

		String value = mf.format(new Object[] {date, new Integer(99), new Double(1E7)});
		
		System.out.println(value);
		
	}
}
