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
			System.out.println("Դ�ļ������ڣ�");
			return;
		} else {
			boolean flag = dir.renameTo(dest);	
			System.out.println("�ļ��Ƿ��ƶ��ɹ���"+flag);
		
			/*boolean delete_flag = dir.delete();
			System.out.println("�ļ��Ƿ�ɾ���ɹ���"+delete_flag);*/
			}
		}
	}
