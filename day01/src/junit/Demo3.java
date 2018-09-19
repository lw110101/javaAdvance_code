package junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Demo3 {

	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream=null;
		
		fileInputStream=new FileInputStream(new File("C:\\Users\\hasee\\Desktop\\a.txt"));
		
		String conent=null;
		
		byte[] b=new byte[1024];
		
		int length=0;
		
		while((length=fileInputStream.read(b))!=-1) {
			conent=new String(b,0,length);
			System.out.println(conent);
		}
		
		fileInputStream.close();
	}
}
