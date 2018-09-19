package a_upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ������ʽ
		String method = request.getParameter("method");
		// �ж�
		if ("upload".equals(method)) {
			upload(request, response);
		} else if ("downList".equals(method)) {
			downList(request, response);
		} else if ("down".equals(method)) {
			down(request, response);
		}
	}
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest request, HttpServletResponse response) {

		try {
			// ������������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ���û�������С
			factory.setSizeThreshold(1024 * 1024);

			// �õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
			String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File saveFile = new File(savePath);

			// �ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
			String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
			File tempFile = new File(tempPath);
			// �ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
			if (!saveFile.exists() && !saveFile.isDirectory()) {
				System.out.println(savePath + "Ŀ¼�����ڣ���Ҫ����");
				// ����Ŀ¼
				saveFile.mkdir();
			}
			// �ж���ʱ�ļ�Ŀ¼�Ƿ����
			if (!tempFile.exists() && !tempFile.isDirectory()) {
				System.out.println(tempPath + "Ŀ¼�����ڣ���Ҫ����");
				// ����Ŀ¼
				tempFile.mkdir();
			}

			// ������ʱ�ļ�Ŀ¼
			factory.setRepository(tempFile);
			// �����ļ��ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �����ϴ��ļ��Ĵ�С����
			upload.setSizeMax(100 * 1024 * 1024);
			upload.setFileSizeMax(50 * 1024 * 1024);
			// ����ļ�����������
			upload.setHeaderEncoding("utf-8");
			// �����ϴ�����
			ProgressListener uploadProgress = new UploadProgress();
			upload.setProgressListener(uploadProgress);

			// �ж�MIME�Ƿ���multipart/form-data����
			if (ServletFileUpload.isMultipartContent(request)) {
				// ����request���󣬽�FileItem��װ��List��
				List<FileItem> fileItems;

				fileItems = upload.parseRequest(request);
				// ����
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) {
						// ��ͨ��
						System.out.println("��Ԫ�����ƣ�" + fileItem.getFieldName());
						System.out.println("��Ԫ�����ݣ�" + fileItem.getString("utf-8")); // //�����ͨ����������ݵ������������� //value =

					} else {
						System.out.println("��Ԫ�����ƣ�" + fileItem.getFieldName());
						System.out.println("�ļ����ͣ�" + fileItem.getContentType());
						String fileName = fileItem.getName();
						System.out.println("�ļ�����" + fileName);
						InputStream in = fileItem.getInputStream();
						System.out.println("�ϴ��ļ�����" + in);
						/*
						 * �ġ��ļ������� ���ڲ�ͬ�û�readme.txt�ļ�����ϣ�����ǣ� ��̨���� ���û����һ��Ψһ���
						 */
						// a. �������һ��Ψһ���
						String uuid = UUID.randomUUID().toString();
						// ƴ�ӳ����ļ����� //�ļ����������
						fileName = uuid + "#" + fileName;
						// �õ��ļ��ı���Ŀ¼
						String realSavePath = makePath(fileName, savePath);
						/*
						 * // �����ļ������ FileOutputStream out = new FileOutputStream(realSavePath + "\\" +
						 * fileName); // ��ȡ�ϴ��ļ� int length = 0; byte[] b = new byte[1024]; while
						 * ((length = in.read(b)) > 0) { // д�뵽Ŀ¼�� out.write(b, 0, length); } // �ر�������
						 * out.close(); // �ر������ in.close();
						 */
						fileItem.write(new File(realSavePath, fileName));
						// ɾ����ʱ�ļ�������Դ
						fileItem.delete();
						message = "�ļ��ϴ��ɹ���";
						request.setAttribute("message", message);

					}
				}
			} else {
				message = "��ǰ�������ļ��ϴ���������ʧ�ܣ�";
			}
			request.getRequestDispatcher("/message.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// hash�㷨��ɢ�洢
	private String makePath(String filename, String savePath) {
		// �õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
		int hashcode = filename.hashCode();

		int dir1 = hashcode & 0xf; // 0--15

		// �����µı���Ŀ¼
		String dir = savePath + "\\" + dir1; // upload\2\3 upload\3\5
		// File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
		File file = new File(dir);
		// ���Ŀ¼������
		if (!file.exists()) {
			// ����Ŀ¼
			file.mkdirs();
		}
		return dir;
	}

	// ʵ��˼·���Ȼ�ȡuploadĿ¼�������ļ����ļ������ٱ��棻��ת��down.jsp�б�չʾ
	private void downList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. ��ʼ��map����Map<����Ψһ��ǵ��ļ���, ����ļ���>
		Map<String, String> map = new HashMap<String, String>();
		// 2�ݹ����filepathĿ¼�µ������ļ���Ŀ¼�����ļ����ļ����洢��map������
		// 2.1 �ϴ�Ŀ¼·��
		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");

		listfile(new File(uploadFilePath), map); // File�ȿ��Դ���һ���ļ�Ҳ���Դ���һ��Ŀ¼

		// �����ݱ��浽request��
		request.setAttribute("map", map);
		// ת��
		request.getRequestDispatcher("/fileDownload.jsp").forward(request, response);
	}

	private void listfile(File file, Map<String, String> map) {
		// ���file����Ĳ���һ���ļ�������һ��Ŀ¼
		if (!file.isFile()) {
			// �г���Ŀ¼�µ������ļ���Ŀ¼
			File files[] = file.listFiles();
			// ����files[]����
			for (File f : files) {
				// �ݹ�
				listfile(f, map);
			}
		} else {

			String realName = file.getName().substring(file.getName().indexOf('#') + 1);
			// file.getName()�õ������ļ���ԭʼ���ƣ����������Ψһ�ģ���˿�����Ϊkey��realName�Ǵ����������ƣ��п��ܻ��ظ�
			map.put(file.getName(), realName);
		}
	}

	private void down(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// ��ȡ�û����ص��ļ�����(url��ַ��׷������,get�����ύ)
		String fileName = request.getParameter("fileName");

		// �ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
		String saveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		// ͨ���ļ����ҳ��ļ�������Ŀ¼
		String path = findFileSavePathByFileName(fileName, saveRootPath);
		// �õ�Ҫ���ص��ļ�
		File file = new File(path + "\\" + fileName);
		if (!file.exists()) {
			message = "��Ҫ���ص���Դ�ѱ�ɾ������";
			return;
		}
		// �����ļ���
		String shortName = fileName.substring(fileName.indexOf('#') + 1);
		// �������ص���Ӧͷ
		response.setContentType("application/x-msdownload");
		// ����ļ��������ģ���Ҫ����url����
		shortName = new String(shortName.getBytes("UTF-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment;fileName=" + shortName); // �������������
		// ��ȡҪ���ص��ļ������浽�ļ�������
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		// ��ȡresponse����ֽ���
		ServletOutputStream out = response.getOutputStream();
		// �����ļ�
		int leng = 0;
		byte[] b = new byte[1024];
		while ((leng = in.read(b)) != -1) {
			out.write(b, 0, leng);
		}
		// �ر�
		out.close();
		in.close();

	}

	/**
	 * filename Ҫ���ص��ļ���
	 * 
	 * @param saveRootPath
	 *            �ϴ��ļ�����ĸ�Ŀ¼��Ҳ����/WEB-INF/uploadĿ¼
	 * @return Ҫ���ص��ļ��Ĵ洢Ŀ¼
	 */

	private String findFileSavePathByFileName(String filename, String saveRootPath) {
		int hashcode = filename.hashCode();

		int dir1 = hashcode & 0xf; // 0--15

		String dir = saveRootPath + "\\" + dir1; // upload\2\3 upload\3\5
		File file = new File(dir);
		if (!file.exists()) {
			// ����Ŀ¼
			file.mkdirs();
		}
		return dir;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
