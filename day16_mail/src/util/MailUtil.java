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
		// // ���������ʼ��������Ĳ���
		prop = new Properties();
		prop.put("mail.transport.protocol", "smtp"); // ָ��Э��
		prop.put("mail.smtp.host", "localhost"); // ���� stmp.qq.com
		prop.put("mail.smtp.port", 25); // �˿�
		prop.put("mail.smtp.auth", "true"); // �û�������֤
		prop.put("mail.debug", "true"); // ����ģʽ
		//����session����
		session=Session.getDefaultInstance(prop);
		message=new MimeMessage(session);
	}

	public static MimeMessage getMessage() {
		return message;
	}
	
	public static void setMail(String subject,String content, InternetAddress address) throws Exception {
		//���ñ���
		message.setSubject(subject);
		// ���÷���ʱ��
		message.setSentDate(new Date());
		//// 3.3 ������
		message.setFrom(new InternetAddress("zhangsan@itcast.com"));
		// �ռ���
		message.setRecipient(Message.RecipientType.TO, address);
		// ���ô��ı�����
		message.setText(content);
	}
	public static void setMail(String subject, InternetAddress address) throws Exception {
		//���ñ���
		message.setSubject(subject);
		// ���÷���ʱ��
		message.setSentDate(new Date());
		//// 3.3 ������
		message.setFrom(new InternetAddress("zhangsan@itcast.com"));
		// �ռ���
		message.setRecipient(Message.RecipientType.TO, address);
	}

	public static void transMail(MimeMessage mess) throws MessagingException, NoSuchProviderException {
//		// ����
//		message.saveChanges();
		// ����
		Transport transport =session.getTransport();
		// �������������
		transport.connect("zhangsan", "123");
		// �����ʼ�
		transport.sendMessage(mess, mess.getAllRecipients());
		// �ر���Դ
		transport.close();
	}
}
