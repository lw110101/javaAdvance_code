package contactSys_mysql_dao_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import contactSys_mysql_dao.ContactDao;
import contactSys_mysql_entity.Contact;
import contactSys_mysql_util.JdbcUtil;

public class ContactDaoMySQLImpl implements ContactDao {

	@Override
	public void addContact(Contact contact) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ׼��sql
			String sql = "insert into contact values(?,?,?,?,?,?,?,?)";
			// ����preparedstatment
			stmt = conn.prepareStatement(sql);
			//���ò���
			String id=UUID.randomUUID().toString().replace("-", "");
			stmt.setString(1, id);
			stmt.setString(2, contact.getName());
			stmt.setString(3, contact.getGender());
			stmt.setInt(4, contact.getAge());
			stmt.setString(5, contact.getEmail());
			stmt.setString(6, contact.getQq());
			stmt.setString(7, contact.getPhone());
			stmt.setString(8, contact.getAd());
			//���Ͳ�����ִ��sql
			int i=stmt.executeUpdate();
			System.out.println(i+"���ܵ�Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Override
	public void deleteContact(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			//׼��sql
			String sql="DELETE FROM contact WHERE id=?";
			//����statement
			stmt=conn.prepareStatement(sql);
			//���ò���
			stmt.setString(1, id);
			//ִ��sql
			int i=stmt.executeUpdate();
			System.out.println(i+"���ܵ�Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Override
	public void updateContact(Contact contact) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {// ��ȡ����
			conn = JdbcUtil.getConnection();
			// ׼��sql
			String sql = "update contact set name=?,gender=?,age=?,email=?,qq=?,phone=?,ad=? WHERE id=?";
			// ����preparedstatment
			stmt = conn.prepareStatement(sql);
			//���ò���
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setInt(3, contact.getAge());
			stmt.setString(4, contact.getEmail());
			stmt.setString(5, contact.getQq());
			stmt.setString(6, contact.getPhone());
			stmt.setString(7, contact.getAd());
			stmt.setString(8, contact.getId());
			//���Ͳ�����ִ��sql
			int i=stmt.executeUpdate();
			System.out.println(i+"���ܵ�Ӱ��");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Override
	public List<Contact> checkContacts() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			//׼��sql
			String sql="select * from contact";
			//����statement
			stmt=conn.prepareStatement(sql);
			//��ȡ�����
			rs=stmt.executeQuery();
			//���������
			List<Contact> list=new ArrayList<Contact>();
			
			while(rs.next()) {
				Contact contact=new Contact();
				contact.setId(rs.getString("id"));
				contact.setName(rs.getString("name"));
				contact.setGender(rs.getString("gender"));
				contact.setAge(rs.getInt("age"));
				contact.setEmail(rs.getString("email"));
				contact.setQq(rs.getString("qq"));
				contact.setPhone(rs.getString("phone"));
				contact.setAd(rs.getString("ad"));
				contact.setId(rs.getString("id"));
				list.add(contact);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}
	}

	@Override
	public Contact findById(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			//׼��sql
			String sql="select * from contact where id=?";
			//����statement
			stmt=conn.prepareStatement(sql);
			//���ò���
			stmt.setString(1, id);
			//��ȡ�����
			rs=stmt.executeQuery();
			Contact contact=null;
			if(rs.next()) {
				contact=new Contact();
				contact.setId(rs.getString("id"));
				contact.setName(rs.getString("name"));
				contact.setGender(rs.getString("gender"));
				contact.setAge(rs.getInt("age"));
				contact.setEmail(rs.getString("email"));
				contact.setQq(rs.getString("qq"));
				contact.setPhone(rs.getString("phone"));
				contact.setAd(rs.getString("ad"));
			}
			return contact;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}
	}

	@Override
	public boolean checkName(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			// ��ȡ����
			conn = JdbcUtil.getConnection();
			//׼��sql
			String sql="select * from contact where name=?";
			//����statement
			stmt=conn.prepareStatement(sql);
			//���ò���
			stmt.setString(1, name);
			//��ȡ�����
			rs=stmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, stmt,rs);
		}
	}
}
