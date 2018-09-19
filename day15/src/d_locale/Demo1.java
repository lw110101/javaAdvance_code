package d_locale;

import java.util.Locale;

public class Demo1 {

	public static void main(String[] args) {
		//本地化对象
		Locale locale=Locale.CHINA;
		System.out.println("国家ISO代码："+locale.getCountry());
		System.out.println("国家名称："+locale.getDisplayCountry());
		System.out.println("语言ISO代码："+locale.getLanguage());
		
		System.out.println(locale.getDisplayName());
		/*Locale[] availableLocales = Locale.getAvailableLocales();
		for (Locale locale2 : availableLocales) {
			System.out.print(locale2.getDisplayCountry()+"  ");
		}
		System.out.println();*/
		System.out.println("默认语言："+Locale.getDefault().getLanguage());
		
		System.out.println("语言名称："+locale.getDisplayLanguage());
		
		System.out.println(locale.getDisplayCountry(Locale.TAIWAN));
		
		System.out.println(locale.getDisplayLanguage(Locale.US));
		
		
	}
	
}
