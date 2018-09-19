package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private static Properties prop;
	public static Session session;
	
	public static MimeMessage message;

	static {
		// // 设置连接邮件服务器的参数
		prop = new Properties();
		prop.put("mail.transport.protocol", "smtp"); // 指定协议
		prop.put("mail.smtp.host", "localhost"); // 主机 stmp.qq.com
		prop.put("mail.smtp.port", 25); // 端口
		prop.put("mail.smtp.auth", "true"); // 用户密码认证
		prop.put("mail.debug", "true"); // 调试模式
		//创建session对象
		session=Session.getDefaultInstance(prop);
		message=new MimeMessage(session);
	}

	public static MimeMessage getMessage() {
		return message;
	}
	
	public static void setMail(String subject,String content, InternetAddress address) throws Exception {
		//设置标题
		message.setSubject(subject);
		// 设置发送时间
		message.setSentDate(new Date());
		//// 3.3 发件人
		message.setFrom(new InternetAddress("zhangsan@itcast.com"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, address);
		// 设置纯文本内容
		message.setText(content);
	}
	public static void setMail(String subject, InternetAddress address) throws Exception {
		//设置标题
		message.setSubject(subject);
		// 设置发送时间
		message.setSentDate(new Date());
		//// 3.3 发件人
		message.setFrom(new InternetAddress("zhangsan@itcast.com"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, address);
	}

	public static void transMail(MimeMessage mess) throws MessagingException, NoSuchProviderException {
//		// 保存
//		message.saveChanges();
		// 发送
		Transport transport =session.getTransport();
		// 连接邮箱服务器
		transport.connect("zhangsan", "123");
		// 发送邮件
		transport.sendMessage(mess, mess.getAllRecipients());
		// 关闭资源
		transport.close();
	}
}
