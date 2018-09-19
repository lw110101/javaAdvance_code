package a_mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import util.MailUtil;

public class SimpleMail {
	@Test
	public void testMail1() throws Exception {
		// 0. 邮件参数
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp"); // 指定协议
		prop.put("mail.smtp.host", "localhost"); // 主机 stmp.qq.com
		prop.put("mail.smtp.port", 25); // 端口
		prop.put("mail.smtp.auth", "true"); // 用户密码认证
		prop.put("mail.debug", "true"); // 调试模式

		// 创建Session对象
		Session session = Session.getDefaultInstance(prop);
		// 创建message整封邮件对象
		MimeMessage message = new MimeMessage(session);
		// 设置标题
		message.setSubject("这是一封简单邮件");
		// 设置发送时间 
		message.setSentDate(new Date());
		//// 3.3 发件人
		message.setFrom(new InternetAddress("zhangsan@itcast.com"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("lisi@itcast.com"));
		// 设置发送内容
		message.setText("你好！今天天气怎样");
		// 保存
		message.saveChanges();
		// 发送
		Transport transport = session.getTransport();
		// 连接邮箱服务器
		transport.connect("zhangsan", "123");
		// 发送邮件
		transport.sendMessage(message, message.getAllRecipients());
		// 关闭资源
		transport.close();
	}
	
	@Test
	public void testMail() throws Exception {
		//主题
		String subject="第二封简单邮件";
		//准备内容
		String content="哈喽，你怎么样了？";
		//准备接收人
		InternetAddress address=new InternetAddress("lisi@itcast.com");
		//设置邮件信息
		MailUtil.setMail(subject,content, address);
		MimeMessage message = MailUtil.getMessage();
		//发送邮件
		MailUtil.transMail(message);
	}
}
