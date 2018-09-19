package e_file;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/********** 1.��ʾ����Ҫ���ص��б� ***************/
	public String list() {
		// ��ȡ����Ŀ¼��·��
		String path = ServletActionContext.getServletContext().getRealPath("/fileUp");
		// Ŀ¼����
		File file = new File(path);
		// �г�Ŀ¼�����ļ�
		String[] fileNames = file.list();
		// ����
		ActionContext ac = ActionContext.getContext();
		// ��ȡ����request��map����
		Map<String, Object> request = ac.getContextMap();
		// �������
		request.put("fileNames", fileNames);
		return "list";
	}

	/******************** �����ļ� ********************************/
	// 1.��ȡҪ���ص��ļ���
	private String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// 2.�����ļ���ҵ���߼�������
	public String down() {
		return "down";
	}

	// 3.�������ķ���
	public InputStream getAttrInputStream() {
		return ServletActionContext.getServletContext().getResourceAsStream("/fileUp/" + fileName);
	}

	// 4.������ʾ���ļ���
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
