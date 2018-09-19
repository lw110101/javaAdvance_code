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

public class AttchMail {

	public static void main(String[] args) throws Exception {
		// 获取邮件对象
		MimeMessage message = MailUtil.getMessage();
		// 设置标题
		String subject = "这是带附件的邮件";
		/******************* 复杂邮件图片带附件 **********************/
		// 构建一个总的邮件块
		MimeMultipart mixed = new MimeMultipart("mixed");
		// 将邮件体放入到邮寄对象中
		message.setContent(mixed);
		//// 左侧： （文本+图片资源）
		MimeBodyPart left = new MimeBodyPart();
		// 右侧： 附件
		MimeBodyPart right = new MimeBodyPart();
		// 将次邮件体放到主邮件体中
		mixed.addBodyPart(left);
		mixed.addBodyPart(right);
		/************** 附件 *******************/
		// 附件路径
		String attchPath = AttchMail.class.getResource("/readme.docx").getPath();
		// 创建数据源
		DataSource att_ds = new FileDataSource(new File(attchPath));
		// 创建数据处理类
		DataHandler att_handle = new DataHandler(att_ds);
		right.setDataHandler(att_handle);
		// 设置附件名称
		right.setFileName("readme.docx");

		/*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
		MimeMultipart related = new MimeMultipart("related");
		// ----> 设置到总邮件快的左侧中
		left.setContent(related);
		// 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
		MimeBodyPart text = new MimeBodyPart();
		MimeBodyPart img = new MimeBodyPart();
		// 设置具体内容
		String imgPath = AttchMail.class.getResource("/Hydrangeas.jpg").getPath();
		// 创建数据源
		DataSource img_ds = new FileDataSource(new File(imgPath));
		// 创建数据处理类
		DataHandler img_handle = new DataHandler(img_ds);
		img.setDataHandler(img_handle);
		// 设置ID
		img.setContentID("Hydrangeas.jpg");
		// 引用资源
		text.setContent("图示如下：<br/><img src='cid:Hydrangeas.jpg'/>", "text/html;charset=utf-8");
		// 将资源添加到多功能块中
		related.addBodyPart(text);
		related.addBodyPart(img);

		// 设置邮件基本信息
		MailUtil.setMail(subject, new InternetAddress("lisi@itcast.com"));
		// 发送邮件
		MailUtil.transMail(message);

	}
}
