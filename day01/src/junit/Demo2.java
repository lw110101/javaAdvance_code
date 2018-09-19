package junit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Demo2 {
	
	File file=new File("C:\\Users\\hasee\\Desktop\\65.txt");
	 @Before                 //׼�����Ի���
	public void beforeRead() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}else {
			System.out.println("���Ի���׼�����!");
		}
	}
	@Test
	public void readFile() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file)));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		bufferedReader.close();
	}
	
	  @After          //������Ի���
	public void afterRead() {
		if(file.exists()) {
			file.delete();
		}else {
			System.out.println("������Ի������!");
		}
	}

}
