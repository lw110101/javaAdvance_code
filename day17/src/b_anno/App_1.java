package b_anno;

import org.junit.Test;

public class App_1 {

	//重写方法的注解
	@Override  
	public String toString() {
		return super.toString();
	}
	
	//抑制编译器警告
	@SuppressWarnings(value= {"unused","unchecked"})
	private void save() {
		System.out.println("保存");
	}
	
	//方法过时
	@Deprecated
	private void save1() {
		
	}
	
	@Test
	public void Test() throws Exception {
		
	}
	
}
