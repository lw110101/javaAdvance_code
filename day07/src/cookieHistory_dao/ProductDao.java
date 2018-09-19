package cookieHistory_dao;

import java.util.ArrayList;
import java.util.List;

import cookieHistory_entity.Product;

public class ProductDao {
/**
 * 将所有的数据封装到list中
 */
	private static List<Product> data=new ArrayList<Product>();
	/**
	 * 初始化商品数据
	 */
	static {
		for (int i = 1; i <= 10; i++) {
			data.add(new Product(""+ i, "笔记本电脑" + i, "CW70"+i, 3500.0 + i));
		}
	}
	/**
	 * 查看所有商品信息
	 */
	public List<Product> findAll() {
		return data;
	}
	/**
	 * 根据ID查询对应的商品信息
	 */
	public Product findById(String id) {
		for(Product p:data) {
			if(p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
}
