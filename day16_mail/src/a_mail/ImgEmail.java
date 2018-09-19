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

		// ��ȡ�ʼ�����
		MimeMessage message = MailUtil.getMessage();
		// ����
		String subject = "���Ǵ�ͼƬ���ʼ�";
		/************** 1. �ʼ�����ͼƬ��Դ ********************/
		// 1.1 �����������ʼ���
		MimeMultipart related = new MimeMultipart("related");
		// �ʼ���related = ���� + ��Դ
		MimeBodyPart content = new MimeBodyPart(); // ����
		MimeBodyPart resource = new MimeBodyPart(); // ��Դ
		// ---- �����ʼ���Դ------
		// ��ȡ��Դ·��
		String path = ImgEmail.class.getResource("/Hydrangeas.jpg").getPath();

		File file = new File(path);
		// ��������Դ
		DataSource ds = new FileDataSource(file);
		// �������ݴ�����
		DataHandler handler = new DataHandler(ds);
		// ����������Դ
		resource.setDataHandler(handler);
		// ����ID��ͼƬ��Դ���ʼ��е�����
		resource.setContentID("Hydrangeas.jpg");
		// ������Դ
		content.setContent("������һ��ͼƬ��<br/><img src='cid:Hydrangeas.jpg' />", "text/html;charset=UTF-8");

		// ��������Դ���õ������ʼ���
		related.addBodyPart(content);
		related.addBodyPart(resource);
		// 1.2 �Ѹ����ʼ������õ��ʼ�������(message)��
		message.setContent(related);
		// �����ʼ�������Ϣ
		MailUtil.setMail(subject, new InternetAddress("lisi@itcast.com"));
		// �����ʼ�
		MailUtil.transMail(message);
	}
}
