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
// ��ʶServlet֧���ļ��ϴ�
@MultipartConfig(//location = "D:\\upload", // �ļ����·����ָ����Ŀ¼������ڣ���������쳣
		maxFileSize = -1, // ����ϴ��ļ���С,
		fileSizeThreshold = 50*1024*1024*1024// �����������ڸ�ֵʱ�����ݽ���д���ļ�����specification�еĽ��͵Ĵ����˼����֪���ǲ���ָBuffer size������СҲ�����ֽڵ�λ
// maxRequestSize = 8*1024*1024*6 //��Ը� multipart/form-data ��������������Ĭ��ֵΪ -1����ʾû�����ơ����ֽ�Ϊ��λ��
)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ������Ӧ�������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// �洢·��
		String savePath ="D:\\apache-tomcat-9.0.6\\webapps\\fileUpload";
		File saveFile = new File(savePath);
		// �жϴ洢·���Ƿ����
		if (!saveFile.exists()) {
			// �����ڣ��򴴽�
			saveFile.mkdirs();
		}
		// ��ȡ�ϴ����ļ�����
		Collection<Part> parts = request.getParts();
		// �ϴ������ļ�
		if (parts.size() == 1) {
			// Servlet3.0��multipart/form-data��POST�����װ��Part��ͨ��Part���ϴ����ļ����в���
			Part part = request.getPart("file");// ͨ����file�ؼ�(<input type="file" name="file">)������ֱ�ӻ�ȡPart����
			// ��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("content-disposition");
			// ��ȡ�ļ���
			String fileName = getFileName(header);
			// ���ļ�д��ָ��·��
			part.write(savePath + File.separator + fileName);
			part.delete();
		} else {
			// һ�����ϴ�����ļ�
			for (Part part : parts) {// ѭ�������ϴ����ļ�
				// ��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
				String header = part.getHeader("content-disposition");
				// ��ȡ�ļ���
				String fileName = getFileName(header);
				// ���ļ�д��ָ��·��
				part.write(savePath + File.separator + fileName);
				
				//����
				System.out.println("�ļ����֣�"+part.getSubmittedFileName());
			}
		}
		PrintWriter out = response.getWriter();
		out.println("�ϴ��ɹ�");
		out.flush();
		out.close();
	}
	/**
	 * ��������ͷ�������ļ��� ����ͷ�ĸ�ʽ�������google������£�form-data; name="file";filename="snmp4j--api.zip" 
	 * IE������£�form-data; name="file";filename="E:\snmp4j--api.zip"
	 * @param header ����ͷ
	 * @return �ļ���
	 */
	public String getFileName(String header) {
		String[] tempArr1 = header.split(";");
		/**
		 * �������google������£�tempArr2={filename,"snmp4j--api.zip"}
		 * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// ��ȡ�ļ��������ݸ����������д��
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
