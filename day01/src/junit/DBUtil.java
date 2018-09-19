package junit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtil {

	static Properties properties;
	
	//相对路径
	/*@Test
	public  void test1() throws IOException{
		properties=new Properties();
		
		properties.load(new FileReader("db.properties"));
		
		String useNmae=properties.getProperty("useName");
		
		String password=properties.getProperty("password");
		
		System.out.println("当前路径:"+new File("").getAbsolutePath());
		
		System.out.println("用户名:"+useNmae+"  密码:"+password);
	}
	*/
	//类文件路径:
		
		static{
			try {
				properties = new Properties();
				//去加载配置文件  /
				Class<DBUtil> clazz = DBUtil.class; 
				InputStream inputStream = clazz.getResourceAsStream("/db.properties"); //  "/"代表了Classpath的路径。           getResourceAsStream 该方法里面使用的路径就是使用了类文件路径。
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			System.out.println("当前路径："+ new File(".").getAbsolutePath() );
			System.out.println("用户名："+ properties.getProperty("useName")+" 密码："+properties.getProperty("password"));
			
		}	

	}
