package cookieHistory_entity;

public class Product {

	private String id;
	private String prodName;
	private String prodType;
	private double price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductDemo [id=" + id + ", prodName=" + prodName + ", prodType=" + prodType + ", price=" + price + "]";
	}
	public Product(String id, String prodName, String prodType, double price) {
		super();
		this.id = id;
		this.prodName = prodName;
		this.prodType = prodType;
		this.price = price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
