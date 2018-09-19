package e_path;

import org.junit.Test;

public class TestPath {
	/**
	 * getResourceAsStream ()返回的是inputstream
	 * getResource()返回:URL
	 * Class.getResource("") 返回的是当前Class这个类所在包开始的为置 
	 * Class.getResource("/")返回的是classpath的位置 
	 * Class.getClassLoader().getResource("") 返回的是classpath的位置
	 *  相对路径的获得
	 *  说明:相对路径均可通过以下方式获得（不论是一般的Java项目还是web项目）
	 *  System.getProperty("user.dir");
	 */
	@Test
	public void testPath() {  

		String path=System.getProperty("user.dir");
		System.out.println("相对路径是:"+path);
		System.out.println();
		
		System.out.println("classpath的位置:"+TestPath.class.getClassLoader().getResource(""));
		System.out.println();		
		
		System.out.println("Class这个类所在包开始的位置:"+TestPath.class.getResource(""));
		System.out.println();		  
	
		System.out.println("字节流:"+TestPath.class.getResourceAsStream(""));
		System.out.println();		
		
		System.out.println("classpath的位置:"+TestPath.class.getResource("/"));
		
		System.out.println("当前线程的路径："+Thread.currentThread().getContextClassLoader().getResource(""));
		
		System.out.println("系统路径："+ClassLoader.getSystemResource(""));
	}
}
