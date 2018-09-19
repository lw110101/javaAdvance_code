package c_longtext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import util.JdbcUtil;
/**
 * 1. 二进制数据类型   
 * @author hasee 下午8:54:01
 */
public class Demo_blob {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Test   //( 写longblob)
	public void testSaveBlob() {
			String sql="insert into test(img) values(?)";
		try {
			conn=JdbcUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			//获取图片流
			InputStream in=Demo_blob.class.getResourceAsStream("7.jpg");
			pstmt.setBinaryStream(1, in);
			// 执行保存图片
			pstmt.executeUpdate();
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	@Test    //读longblob
	public void testGetBlob() {
		String sql = "select img from test where id=1";

		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				InputStream in=rs.getBinaryStream("img");
				// 图片输出流
				FileOutputStream out = new FileOutputStream(new File("c://1.jpg"));
				int len = -1;
				byte b[] = new byte[1024];
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				out.close();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	  
}   
