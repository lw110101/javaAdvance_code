package c_longtext;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;

public class Demo_text {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	/*****���ı���������******/
	@Test    //дlongtext
	public void testSaveText() {
		String sql="insert into test(content) values(?)";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			//��ȡ������
			String path=Demo_text.class.getResource("JDBC.txt").getPath();
			System.out.println("·����:"+path);
			/*
			InputStream in=Demo_text.class.getResourceAsStream("JDBC.txt");
			InputStreamReader reader=new InputStreamReader(in);*/
			FileReader reader=new FileReader(new File(path));
			//���ò���
			pstmt.setCharacterStream(1, reader);
			//ִ��
			pstmt.executeUpdate();
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	@Test  //��ȡlongtext
	public void testGetText() {
		String sql="select * from test where id=3";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			//��ȡ�����
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//��ȡ�ı���ʽһ
				Reader reader=rs.getCharacterStream("content");
				int length=0;
				char[] buf=new char[1024];
				FileWriter writer=new FileWriter(new File("c:/1.txt"));
				while((length=reader.read(buf))!=-1) {
					writer.write(buf, 0, length);
				}
				writer.close();
				reader.close();
				//��ȡ�ı����ݷ�ʽ��
				System.out.print(rs.getString("content"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
}
