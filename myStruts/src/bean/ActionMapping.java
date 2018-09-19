package bean;

import java.util.Map;
/**
 * 封装action节点
 * @author hasee 上午11:37:29
 */

public class ActionMapping {
	//请求路径
	private String name;
	//处理的action类
	private String className;
	//方法
	private String method;
	//封装的结果视图      key:跳转标记     value:result节点
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
