package junit;

import org.junit.Test;
//������
public class ToolTest {

	@Test
	public void TestGetMax() {
		int max=Tool.getMax();
		if(5!=max) {
			throw new RuntimeException();
		}
		System.out.println("���ֵ�ǣ�"+max);
	}
	@Test
	public void TestGetMin() {
		int min=Tool.getMin();
		if(3!=min) {
			throw new RuntimeException();
		}
		System.out.println("��Сֵ�ǣ�"+min);
	}
}
