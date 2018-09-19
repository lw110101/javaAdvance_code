package day02;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo1 {

	public static void main(String[] args) {
		
		File file=new File("C:\\Users\\hasee\\Desktop\\Web日志");
		System.out.println("文件名"+"\t"+"\t"+"\t"+"\t"+"修改日期"+"\t"+"\t"+"\t"+"大小");

		test(file,"|--");
	}
	
	public static void test(File file,String space){
		File[] files=file.listFiles(); 
		for(File dir:files){
			if(dir.isDirectory()){
				System.out.println(space+dir.getName());
				test(dir,"|  " + space);
			}else if(dir.isFile()){
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
				long l=dir.lastModified();
				String date=simpleDateFormat.format(new Date(l));
				System.out.println(space+dir.getName()+"\t"+date+"\t"+dir.length());
			}
		}
		
	}
	
}
