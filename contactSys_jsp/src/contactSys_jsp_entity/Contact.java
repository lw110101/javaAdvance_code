package contactSys_jsp_entity;

public class Contact {

	private String name;
	private String gender;
	private String id;
	private int age;
	private String email;
	private String qq;
	private String phone;
	private String ad;
	
	public Contact(String name, String gender, String id, int age, String email, String qq, String phone, String ad) {
		super();
		this.name = name;
		this.gender = gender;
		this.id = id;
		this.age = age;
		this.email = email;
		this.qq = qq;
		this.phone = phone;
		this.ad = ad;
	}
	
	public Contact() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	@Override
	public String toString() {
		return "编号:"+this.id+" 姓名:"+this.name+" 性别:"+this.gender+" 年龄:"+this.age
				+" 电话:"+this.phone+" qq:"+this.qq+" 邮箱:"+this.email+" 地址:"+this.ad;
	}
	
}
