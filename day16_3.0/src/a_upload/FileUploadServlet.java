package a_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUploadServlet")
// 标识Servlet支持文件上传
@MultipartConfig(//location = "D:\\upload", // 文件存放路径，指定的目录必须存在，否则会抛异常
		maxFileSize = -1, // 最大上传文件大小,
		fileSizeThreshold = 50*1024*1024*1024// 当数据量大于该值时，内容将被写入文件。（specification中的解释的大概意思，不知道是不是指Buffer size），大小也是已字节单位
// maxRequestSize = 8*1024*1024*6 //针对该 multipart/form-data 请求的最大数量，默认值为 -1，表示没有限制。以字节为单位。
)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应请求编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 存储路径
		String savePath ="D:\\apache-tomcat-9.0.6\\webapps\\fileUpload";
		File saveFile = new File(savePath);
		// 判断存储路径是否存在
		if (!saveFile.exists()) {
			// 不存在，则创建
			saveFile.mkdirs();
		}
		// 获取上传的文件集合
		Collection<Part> parts = request.getParts();
		// 上传单个文件
		if (parts.size() == 1) {
			// Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作
			Part part = request.getPart("file");// 通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
			// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("content-disposition");
			// 获取文件名
			String fileName = getFileName(header);
			// 把文件写到指定路径
			part.write(savePath + File.separator + fileName);
			part.delete();
		} else {
			// 一次性上传多个文件
			for (Part part : parts) {// 循环处理上传的文件
				// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
				String header = part.getHeader("content-disposition");
				// 获取文件名
				String fileName = getFileName(header);
				// 把文件写到指定路径
				part.write(savePath + File.separator + fileName);
				
				//测试
				System.out.println("文件名字："+part.getSubmittedFileName());
			}
		}
		PrintWriter out = response.getWriter();
		out.println("上传成功");
		out.flush();
		out.close();
	}
	/**
	 * 根据请求头解析出文件名 请求头的格式：火狐和google浏览器下：form-data; name="file";filename="snmp4j--api.zip" 
	 * IE浏览器下：form-data; name="file";filename="E:\snmp4j--api.zip"
	 * @param header 请求头
	 * @return 文件名
	 */
	public String getFileName(String header) {
		String[] tempArr1 = header.split(";");
		/**
		 * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// 获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
