package junit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtil {

	static Properties properties;
	
	//���·��
	/*@Test
	public  void test1() throws IOException{
		properties=new Properties();
		
		properties.load(new FileReader("db.properties"));
		
		String useNmae=properties.getProperty("useName");
		
		String password=properties.getProperty("password");
		
		System.out.println("��ǰ·��:"+new File("").getAbsolutePath());
		
		System.out.println("�û���:"+useNmae+"  ����:"+password);
	}
	*/
	//���ļ�·��:
		
		static{
			try {
				properties = new Properties();
				//ȥ���������ļ�  /
				Class<DBUtil> clazz = DBUtil.class; 
				InputStream inputStream = clazz.getResourceAsStream("/db.properties"); //  "/"������Classpath��·����           getResourceAsStream �÷�������ʹ�õ�·������ʹ�������ļ�·����
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			System.out.println("��ǰ·����"+ new File(".").getAbsolutePath() );
			System.out.println("�û�����"+ properties.getProperty("useName")+" ���룺"+properties.getProperty("password"));
			
		}	

	}
