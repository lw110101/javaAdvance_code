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
 * ���ʻ�
 * 
 * @author hasee ����5:48:55
 */
public class StaticText {
	// ���ʻ� - ��̬����
	@Test
	public void testI18N() {
		ResourceBundle bundle = ResourceBundle.getBundle("myproperties", Locale.US);

		String name = bundle.getString("userName");

		String pwd = bundle.getString("password");

		System.out.println(name + "  " + pwd);

	}

	// ��̬�ı��Ĺ��ʻ�
	@Test // 0.����
	public void testI18N1() {
		/*
		 * ������صĹ��ʻ�
		 */
		NumberFormat.getInstance();
		// ������;
		NumberFormat.getNumberInstance();
		// ����
		NumberFormat.getCurrencyInstance();
		// ����
		NumberFormat.getIntegerInstance();
		// �ٷֱ�
		NumberFormat.getPercentInstance();
		/*
		 * ���ڵĸ�ʽ��
		 */
		//DateFormat.getTimeInstance(style, aLocale);
		// DateFormat.getDateTimeInstance(dateStyle, timeStyle, aLocale);
	}

	@Test // ��̬����
	public void testI18N2() {
		// ģ�����Ի���
		Locale locale = Locale.US;
		// ׼������
		double num = 1000;
		//// ������
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
		// ���ʻ�����
		String value = currencyInstance.format(num);
		// ����
		System.out.println(value);
	}

	@Test // �����������
	public void testI18N3() {
		// ģ�����Ի���
		Locale locale = Locale.US;
		// ׼������
		double num = 1000.12;
		//// ������
		NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
		String value = numberInstance.format(num);
		// ����
		System.out.println(value);
		// ����
		NumberFormat integerInstance = NumberFormat.getIntegerInstance(locale);
		String value1 = integerInstance.format(num);
		// ����
		System.out.println(value1);

	}

	// �����⣺ ������㣺 $100 * 10
	@Test
	public void testI18N5() {
		// ģ�����Ի���
		Locale locale = Locale.US;
	//// ������
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
		// ׼������
		String str="$100";
		int num=10;
		try {
			//������ת��������
			Number number= currencyInstance.parse(str);
			int intValue = number.intValue();
			//������ת���ɻ��� 
			String result = currencyInstance.format(intValue*=num);
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	//2. ���ʻ���ֵ
	@Test
	public void testI18N6() throws Exception {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
		String str = nf.format(1000000000);
		System.out.println(str);
	} 

	/*
	 * ���� ��ʽ 
	 * 		FULL: 2018��5��31�� ������
	 *  	LONG: 2018��5��31�� 
	 *  	MEDIUM: 2018-5-31 
	 *  	SHORT: 18-5-31
	 * 
	 * ʱ�� ��ʽ 
	 * 		FULL: ����08ʱ41��34�� CST 
	 * 		LONG: ����08ʱ43��16�� 
	 * 		MEDIUM: 20:43:46 
	 * 		SHORT: ����8:44
	 * 
	 */
	@Test
	public void testI18N4() {
		// ģ�����Ի���
		Locale locale = Locale.getDefault();
		// ������
		DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
		// ׼������
		String date = dateTimeInstance.format(new Date());
		// ����
		System.out.println(date);
	}
	// ����2�� �뽫ʱ��ֵ��09-11-28 ����10ʱ25��39�� CST�����������һ��date����
	@Test
	public void testI18N7() throws ParseException {
		String time="09-11-28 ����10ʱ25��39�� CST";
		// ������
		DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.CHINA);
		Date date = dateTimeInstance.parse(time);
		System.out.println(date);
		
	}
	@Test  //MessageFormat(��̬�ı�)
	public void testI18N8() throws Exception  {
		//String pattern="On {0}, a hurricance destroyed {��} houses and caused {��} of damage.";
		
		String pattern = "At {0, time, short} on {0, date}, a hurricance destroyed {1} houses and caused {2, number, currency} of damage.";


		//������
		MessageFormat mf=new MessageFormat(pattern, Locale.CHINA);
		//׼����������
		String datetime = "Jul 3, 1998 12:30 PM";
		Date date=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT,Locale.US).parse(datetime);		
		//ִ�и�ʽ������

		String value = mf.format(new Object[] {date, new Integer(99), new Double(1E7)});
		
		System.out.println(value);
		
	}
}
