package b_anno;

import org.junit.Test;

public class App_1 {

	//��д������ע��
	@Override  
	public String toString() {
		return super.toString();
	}
	
	//���Ʊ���������
	@SuppressWarnings(value= {"unused","unchecked"})
	private void save() {
		System.out.println("����");
	}
	
	//������ʱ
	@Deprecated
	private void save1() {
		
	}
	
	@Test
	public void Test() throws Exception {
		
	}
	
}
