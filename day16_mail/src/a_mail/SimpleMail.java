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
		// 0. �ʼ�����
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp"); // ָ��Э��
		prop.put("mail.smtp.host", "localhost"); // ���� stmp.qq.com
		prop.put("mail.smtp.port", 25); // �˿�
		prop.put("mail.smtp.auth", "true"); // �û�������֤
		prop.put("mail.debug", "true"); // ����ģʽ

		// ����Session����
		Session session = Session.getDefaultInstance(prop);
		// ����message�����ʼ�����
		MimeMessage message = new MimeMessage(session);
		// ���ñ���
		message.setSubject("����һ����ʼ�");
		// ���÷���ʱ�� 
		message.setSentDate(new Date());
		//// 3.3 ������
		message.setFrom(new InternetAddress("zhangsan@itcast.com"));
		// �ռ���
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("lisi@itcast.com"));
		// ���÷�������
		message.setText("��ã�������������");
		// ����
		message.saveChanges();
		// ����
		Transport transport = session.getTransport();
		// �������������
		transport.connect("zhangsan", "123");
		// �����ʼ�
		transport.sendMessage(message, message.getAllRecipients());
		// �ر���Դ
		transport.close();
	}
	
	@Test
	public void testMail() throws Exception {
		//����
		String subject="�ڶ�����ʼ�";
		//׼������
		String content="��ඣ�����ô���ˣ�";
		//׼��������
		InternetAddress address=new InternetAddress("lisi@itcast.com");
		//�����ʼ���Ϣ
		MailUtil.setMail(subject,content, address);
		MimeMessage message = MailUtil.getMessage();
		//�����ʼ�
		MailUtil.transMail(message);
	}
}
