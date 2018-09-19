package day02;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo1 {

	public static void main(String[] args) {
		
		File file=new File("C:\\Users\\hasee\\Desktop\\Web��־");
		System.out.println("�ļ���"+"\t"+"\t"+"\t"+"\t"+"�޸�����"+"\t"+"\t"+"\t"+"��С");

		test(file,"|--");
	}
	
	public static void test(File file,String space){
		File[] files=file.listFiles(); 
		for(File dir:files){
			if(dir.isDirectory()){
				System.out.println(space+dir.getName());
				test(dir,"|  " + space);
			}else if(dir.isFile()){
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
				long l=dir.lastModified();
				String date=simpleDateFormat.format(new Date(l));
				System.out.println(space+dir.getName()+"\t"+date+"\t"+dir.length());
			}
		}
		
	}
	
}
