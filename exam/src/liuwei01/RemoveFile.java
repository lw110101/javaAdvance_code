package liuwei01;

import java.io.File;
import java.io.IOException;

public class RemoveFile {

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\test.txt");

		File destFile = new File("D:\\"+file.getName());
		renameFile(file, destFile);

	}

	public static void renameFile(File dir, File dest) throws IOException {

		if (!dir.exists()) {
			System.out.println("源文件不存在！");
			return;
		} else {
			boolean flag = dir.renameTo(dest);	
			System.out.println("文件是否移动成功："+flag);
		
			/*boolean delete_flag = dir.delete();
			System.out.println("文件是否删除成功："+delete_flag);*/
			}
		}
	}
