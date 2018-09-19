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
		// 获取操作方式
		String method = request.getParameter("method");
		// 判断
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
			// 创建工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小
			factory.setSizeThreshold(1024 * 1024);

			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File saveFile = new File(savePath);

			// 上传时生成的临时文件保存目录
			String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
			File tempFile = new File(tempPath);
			// 判断上传文件的保存目录是否存在
			if (!saveFile.exists() && !saveFile.isDirectory()) {
				System.out.println(savePath + "目录不存在，需要创建");
				// 创建目录
				saveFile.mkdir();
			}
			// 判断临时文件目录是否存在
			if (!tempFile.exists() && !tempFile.isDirectory()) {
				System.out.println(tempPath + "目录不存在，需要创建");
				// 创建目录
				tempFile.mkdir();
			}

			// 设置临时文件目录
			factory.setRepository(tempFile);
			// 创建文件上传对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置上传文件的大小限制
			upload.setSizeMax(100 * 1024 * 1024);
			upload.setFileSizeMax(50 * 1024 * 1024);
			// 解决文件名中文乱码
			upload.setHeaderEncoding("utf-8");
			// 设置上传进度
			ProgressListener uploadProgress = new UploadProgress();
			upload.setProgressListener(uploadProgress);

			// 判断MIME是否是multipart/form-data类型
			if (ServletFileUpload.isMultipartContent(request)) {
				// 解析request对象，将FileItem封装到List中
				List<FileItem> fileItems;

				fileItems = upload.parseRequest(request);
				// 遍历
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) {
						// 普通表单
						System.out.println("表单元素名称：" + fileItem.getFieldName());
						System.out.println("表单元素数据：" + fileItem.getString("utf-8")); // //解决普通输入项的数据的中文乱码问题 //value =

					} else {
						System.out.println("表单元素名称：" + fileItem.getFieldName());
						System.out.println("文件类型：" + fileItem.getContentType());
						String fileName = fileItem.getName();
						System.out.println("文件名：" + fileName);
						InputStream in = fileItem.getInputStream();
						System.out.println("上传文件流：" + in);
						/*
						 * 四、文件名重名 对于不同用户readme.txt文件，不希望覆盖！ 后台处理： 给用户添加一个唯一标记
						 */
						// a. 随机生成一个唯一标记
						String uuid = UUID.randomUUID().toString();
						// 拼接成新文件名称 //文件保存的名称
						fileName = uuid + "#" + fileName;
						// 得到文件的保存目录
						String realSavePath = makePath(fileName, savePath);
						/*
						 * // 创建文件输出流 FileOutputStream out = new FileOutputStream(realSavePath + "\\" +
						 * fileName); // 读取上传文件 int length = 0; byte[] b = new byte[1024]; while
						 * ((length = in.read(b)) > 0) { // 写入到目录下 out.write(b, 0, length); } // 关闭输入流
						 * out.close(); // 关闭输出流 in.close();
						 */
						fileItem.write(new File(realSavePath, fileName));
						// 删除临时文件回收资源
						fileItem.delete();
						message = "文件上传成功！";
						request.setAttribute("message", message);

					}
				}
			} else {
				message = "当前表单不是文件上传表单，处理失败！";
			}
			request.getRequestDispatcher("/message.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// hash算法打散存储
	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();

		int dir1 = hashcode & 0xf; // 0--15

		// 构造新的保存目录
		String dir = savePath + "\\" + dir1; // upload\2\3 upload\3\5
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	// 实现思路：先获取upload目录下所有文件的文件名，再保存；跳转到down.jsp列表展示
	private void downList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 初始化map集合Map<包含唯一标记的文件名, 简短文件名>
		Map<String, String> map = new HashMap<String, String>();
		// 2递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
		// 2.1 上传目录路径
		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");

		listfile(new File(uploadFilePath), map); // File既可以代表一个文件也可以代表一个目录

		// 将数据保存到request域
		request.setAttribute("map", map);
		// 转发
		request.getRequestDispatcher("/fileDownload.jsp").forward(request, response);
	}

	private void listfile(File file, Map<String, String> map) {
		// 如果file代表的不是一个文件，而是一个目录
		if (!file.isFile()) {
			// 列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			// 遍历files[]数组
			for (File f : files) {
				// 递归
				listfile(f, map);
			}
		} else {

			String realName = file.getName().substring(file.getName().indexOf('#') + 1);
			// file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			map.put(file.getName(), realName);
		}
	}

	private void down(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 获取用户下载的文件名称(url地址后追加数据,get方法提交)
		String fileName = request.getParameter("fileName");

		// 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		String saveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		// 通过文件名找出文件的所在目录
		String path = findFileSavePathByFileName(fileName, saveRootPath);
		// 得到要下载的文件
		File file = new File(path + "\\" + fileName);
		if (!file.exists()) {
			message = "您要下载的资源已被删除！！";
			return;
		}
		// 处理文件名
		String shortName = fileName.substring(fileName.indexOf('#') + 1);
		// 设置下载的响应头
		response.setContentType("application/x-msdownload");
		// 如果文件名是中文，需要进行url编码
		shortName = new String(shortName.getBytes("UTF-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment;fileName=" + shortName); // 以这个名词下载
		// 读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		// 获取response输出字节流
		ServletOutputStream out = response.getOutputStream();
		// 下载文件
		int leng = 0;
		byte[] b = new byte[1024];
		while ((leng = in.read(b)) != -1) {
			out.write(b, 0, leng);
		}
		// 关闭
		out.close();
		in.close();

	}

	/**
	 * filename 要下载的文件名
	 * 
	 * @param saveRootPath
	 *            上传文件保存的根目录，也就是/WEB-INF/upload目录
	 * @return 要下载的文件的存储目录
	 */

	private String findFileSavePathByFileName(String filename, String saveRootPath) {
		int hashcode = filename.hashCode();

		int dir1 = hashcode & 0xf; // 0--15

		String dir = saveRootPath + "\\" + dir1; // upload\2\3 upload\3\5
		File file = new File(dir);
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
