package e_file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUpAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// 上传文件
	private File file;
	// 文件名
	private String fileFileName;
	// 文件类型
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
			// 获取文件上传目录
			String path = ServletActionContext.getServletContext().getRealPath("/fileUp");
			
			File destFile = new File(path, fileFileName);
			// 使用struts的工具类的方法
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "fileUp";
	}

}
