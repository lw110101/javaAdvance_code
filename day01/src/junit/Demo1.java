package junit;

import org.junit.Test;

public class Demo1 {

	@Test
	public void getMax() {
		int a=3;
		int b=6;
		int max=a>b?a:b;
		System.out.println("���ֵ��:"+max);
		//throw new RuntimeException();
	}
}
