package d_locale;

import java.util.Locale;

public class Demo1 {

	public static void main(String[] args) {
		//���ػ�����
		Locale locale=Locale.CHINA;
		System.out.println("����ISO���룺"+locale.getCountry());
		System.out.println("�������ƣ�"+locale.getDisplayCountry());
		System.out.println("����ISO���룺"+locale.getLanguage());
		
		System.out.println(locale.getDisplayName());
		/*Locale[] availableLocales = Locale.getAvailableLocales();
		for (Locale locale2 : availableLocales) {
			System.out.print(locale2.getDisplayCountry()+"  ");
		}
		System.out.println();*/
		System.out.println("Ĭ�����ԣ�"+Locale.getDefault().getLanguage());
		
		System.out.println("�������ƣ�"+locale.getDisplayLanguage());
		
		System.out.println(locale.getDisplayCountry(Locale.TAIWAN));
		
		System.out.println(locale.getDisplayLanguage(Locale.US));
		
		
	}
	
}
