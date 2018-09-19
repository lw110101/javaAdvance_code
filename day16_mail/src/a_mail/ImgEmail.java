package a_mail;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import util.MailUtil;

public class ImgEmail {

	public static void main(String[] args) throws Exception {

		// 获取邮件对象
		MimeMessage message = MailUtil.getMessage();
		// 主题
		String subject = "这是带图片的邮件";
		/************** 1. 邮件设置图片资源 ********************/
		// 1.1 创建负责复杂邮件体
		MimeMultipart related = new MimeMultipart("related");
		// 邮件体related = 内容 + 资源
		MimeBodyPart content = new MimeBodyPart(); // 内容
		MimeBodyPart resource = new MimeBodyPart(); // 资源
		// ---- 设置邮件资源------
		// 获取资源路径
		String path = ImgEmail.class.getResource("/Hydrangeas.jpg").getPath();

		File file = new File(path);
		// 创建数据源
		DataSource ds = new FileDataSource(file);
		// 创建数据处理类
		DataHandler handler = new DataHandler(ds);
		// 设置数据资源
		resource.setDataHandler(handler);
		// 设置ID；图片资源在邮件中的名称
		resource.setContentID("Hydrangeas.jpg");
		// 引用资源
		content.setContent("如下是一张图片：<br/><img src='cid:Hydrangeas.jpg' />", "text/html;charset=UTF-8");

		// 把内容资源设置到复杂邮件中
		related.addBodyPart(content);
		related.addBodyPart(resource);
		// 1.2 把复杂邮件，设置到邮件对象中(message)！
		message.setContent(related);
		// 设置邮件基本信息
		MailUtil.setMail(subject, new InternetAddress("lisi@itcast.com"));
		// 发送邮件
		MailUtil.transMail(message);
	}
}
