package bean;

import java.util.Map;
/**
 * ��װaction�ڵ�
 * @author hasee ����11:37:29
 */

public class ActionMapping {
	//����·��
	private String name;
	//�����action��
	private String className;
	//����
	private String method;
	//��װ�Ľ����ͼ      key:��ת���     value:result�ڵ�
	private Map<String,Result> result;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, Result> getResult() {
		return result;
	}
	public void setResult(Map<String, Result> result) {
		this.result = result;
	}
	

}
