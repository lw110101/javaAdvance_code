package cookieHistory_dao;

import java.util.ArrayList;
import java.util.List;

import cookieHistory_entity.Product;

public class ProductDao {
/**
 * �����е����ݷ�װ��list��
 */
	private static List<Product> data=new ArrayList<Product>();
	/**
	 * ��ʼ����Ʒ����
	 */
	static {
		for (int i = 1; i <= 10; i++) {
			data.add(new Product(""+ i, "�ʼǱ�����" + i, "CW70"+i, 3500.0 + i));
		}
	}
	/**
	 * �鿴������Ʒ��Ϣ
	 */
	public List<Product> findAll() {
		return data;
	}
	/**
	 * ����ID��ѯ��Ӧ����Ʒ��Ϣ
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
