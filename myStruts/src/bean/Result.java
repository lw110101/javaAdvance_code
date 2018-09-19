package bean;

/**
 * 封装结果视图
 * @author hasee 上午11:29:40
 */
public class Result {
	//返回的结果标记
	private String name;
	//跳转的类型
	private String type;
	//跳转的页面
	private String uri;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	

}
