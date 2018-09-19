package junit;

import org.junit.Test;
//测试类
public class ToolTest {

	@Test
	public void TestGetMax() {
		int max=Tool.getMax();
		if(5!=max) {
			throw new RuntimeException();
		}
		System.out.println("最大值是："+max);
	}
	@Test
	public void TestGetMin() {
		int min=Tool.getMin();
		if(3!=min) {
			throw new RuntimeException();
		}
		System.out.println("最小值是："+min);
	}
}
