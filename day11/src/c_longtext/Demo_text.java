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
	/*****大文本数据类型******/
	@Test    //写longtext
	public void testSaveText() {
		String sql="insert into test(content) values(?)";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			//获取输入流
			String path=Demo_text.class.getResource("JDBC.txt").getPath();
			System.out.println("路径是:"+path);
			/*
			InputStream in=Demo_text.class.getResourceAsStream("JDBC.txt");
			InputStreamReader reader=new InputStreamReader(in);*/
			FileReader reader=new FileReader(new File(path));
			//设置参数
			pstmt.setCharacterStream(1, reader);
			//执行
			pstmt.executeUpdate();
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	@Test  //读取longtext
	public void testGetText() {
		String sql="select * from test where id=3";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			//获取结果集
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//读取文本方式一
				Reader reader=rs.getCharacterStream("content");
				int length=0;
				char[] buf=new char[1024];
				FileWriter writer=new FileWriter(new File("c:/1.txt"));
				while((length=reader.read(buf))!=-1) {
					writer.write(buf, 0, length);
				}
				writer.close();
				reader.close();
				//读取文本数据方式二
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
