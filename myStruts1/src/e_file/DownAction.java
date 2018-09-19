package e_file;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/********** 1.显示所有要下载的列表 ***************/
	public String list() {
		// 获取下载目录的路径
		String path = ServletActionContext.getServletContext().getRealPath("/fileUp");
		// 目录对象
		File file = new File(path);
		// 列出目录所有文件
		String[] fileNames = file.list();
		// 保存
		ActionContext ac = ActionContext.getContext();
		// 获取代表request的map集合
		Map<String, Object> request = ac.getContextMap();
		// 存放数据
		request.put("fileNames", fileNames);
		return "list";
	}

	/******************** 下载文件 ********************************/
	// 1.获取要下载的文件名
	private String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// 2.下载文件的业务逻辑方法，
	public String down() {
		return "down";
	}

	// 3.返回流的方法
	public InputStream getAttrInputStream() {
		return ServletActionContext.getServletContext().getResourceAsStream("/fileUp/" + fileName);
	}

	// 4.下载显示的文件名
	public String getDownFileName() {
		System.out.println(fileName);
		try {
			fileName = new String(fileName.getBytes(), "iso8859-1");
		} catch (Exception e) {
			throw new RuntimeException(e); 
		}
		return fileName;
	}
}
