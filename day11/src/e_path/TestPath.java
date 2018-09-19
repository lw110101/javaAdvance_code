package e_path;

import org.junit.Test;

public class TestPath {
	/**
	 * getResourceAsStream ()���ص���inputstream
	 * getResource()����:URL
	 * Class.getResource("") ���ص��ǵ�ǰClass��������ڰ���ʼ��Ϊ�� 
	 * Class.getResource("/")���ص���classpath��λ�� 
	 * Class.getClassLoader().getResource("") ���ص���classpath��λ��
	 *  ���·���Ļ��
	 *  ˵��:���·������ͨ�����·�ʽ��ã�������һ���Java��Ŀ����web��Ŀ��
	 *  System.getProperty("user.dir");
	 */
	@Test
	public void testPath() {  

		String path=System.getProperty("user.dir");
		System.out.println("���·����:"+path);
		System.out.println();
		
		System.out.println("classpath��λ��:"+TestPath.class.getClassLoader().getResource(""));
		System.out.println();		
		
		System.out.println("Class��������ڰ���ʼ��λ��:"+TestPath.class.getResource(""));
		System.out.println();		  
	
		System.out.println("�ֽ���:"+TestPath.class.getResourceAsStream(""));
		System.out.println();		
		
		System.out.println("classpath��λ��:"+TestPath.class.getResource("/"));
		
		System.out.println("��ǰ�̵߳�·����"+Thread.currentThread().getContextClassLoader().getResource(""));
		
		System.out.println("ϵͳ·����"+ClassLoader.getSystemResource(""));
	}
}
