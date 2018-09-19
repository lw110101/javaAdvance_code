package d_dbutils;

import org.junit.Test;

import c_jdbc.Admin;

public class Demo1<T> {
	

	public Demo1(Class<T> clazz) {
		
	}
	public int total(int... num) {
		int result=0;
		for(int i=0;i<num.length;i++) {
			result+=num[i];
		}
		return result;
	}
	
	@Test
	public void test() {
		int[] num= {1,2,3,4,5};
		int total = total(num);
		System.out.println("ºÍÊÇ:"+total);
		
	}
	public static void main(String[] args) {
		Demo1<Admin> d=new Demo1<Admin>(Admin.class);
		System.out.println(d);
	}
}
