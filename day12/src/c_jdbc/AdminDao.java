package c_jdbc;

import java.util.ArrayList;
import java.util.List;

public class AdminDao extends BaseDao {

	/*����*/
	public void insert(Admin admin) {
		String sql="insert into admin(userName,password) values(?,?)";
		Object[] paramValues= {admin.getUserName(),admin.getPassword()};
		super.update(sql,paramValues);
	}
	/*ɾ��*/
	public void delete(int id) {
		String sql="delete from  admin where id=? ";
		Object[] paramValues= {id};
		super.update(sql,paramValues);
	}
	
	/*��ѯȫ��*/
	public List<Admin> findAll(){
		List<Admin> list=new ArrayList<Admin>();
		String sql="select * from admin";
		
		list=super.query(sql,null,Admin.class);
		return list;
	} 
	
	/*����������ѯ*/
	public Admin findById(int id) {
		String sql="select * from admin where id=?";
		Object[] paramValue= {id};
		List<Admin> list = super.query(sql, paramValue, Admin.class);
		return (list!=null && list.size()>0) ? list.get(0):null;
	}
}
