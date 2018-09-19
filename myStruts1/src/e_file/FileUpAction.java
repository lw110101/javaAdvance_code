package e_file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUpAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// �ϴ��ļ�
	private File file;
	// �ļ���
	private String fileFileName;
	// �ļ�����
	@SuppressWarnings("unused")
	private String contentType;

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String fileUp() {
		try {
			// ��ȡ�ļ��ϴ�Ŀ¼
			String path = ServletActionContext.getServletContext().getRealPath("/fileUp");
			
			File destFile = new File(path, fileFileName);
			// ʹ��struts�Ĺ�����ķ���
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "fileUp";
	}

}
