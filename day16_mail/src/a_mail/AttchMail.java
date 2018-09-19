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
		// ��ȡ�ʼ�����
		MimeMessage message = MailUtil.getMessage();
		// ���ñ���
		String subject = "���Ǵ��������ʼ�";
		/******************* �����ʼ�ͼƬ������ **********************/
		// ����һ���ܵ��ʼ���
		MimeMultipart mixed = new MimeMultipart("mixed");
		// ���ʼ�����뵽�ʼĶ�����
		message.setContent(mixed);
		//// ��ࣺ ���ı�+ͼƬ��Դ��
		MimeBodyPart left = new MimeBodyPart();
		// �Ҳࣺ ����
		MimeBodyPart right = new MimeBodyPart();
		// �����ʼ���ŵ����ʼ�����
		mixed.addBodyPart(left);
		mixed.addBodyPart(right);
		/************** ���� *******************/
		// ����·��
		String attchPath = AttchMail.class.getResource("/readme.docx").getPath();
		// ��������Դ
		DataSource att_ds = new FileDataSource(new File(attchPath));
		// �������ݴ�����
		DataHandler att_handle = new DataHandler(att_ds);
		right.setDataHandler(att_handle);
		// ���ø�������
		right.setFileName("readme.docx");

		/*************** �����ʼ�����: �๦���û��ʼ� (related) *******************/
		MimeMultipart related = new MimeMultipart("related");
		// ----> ���õ����ʼ���������
		left.setContent(related);
		// 4.2 �����๦���ʼ������� = ����ı� + �Ҳ�ͼƬ��Դ
		MimeBodyPart text = new MimeBodyPart();
		MimeBodyPart img = new MimeBodyPart();
		// ���þ�������
		String imgPath = AttchMail.class.getResource("/Hydrangeas.jpg").getPath();
		// ��������Դ
		DataSource img_ds = new FileDataSource(new File(imgPath));
		// �������ݴ�����
		DataHandler img_handle = new DataHandler(img_ds);
		img.setDataHandler(img_handle);
		// ����ID
		img.setContentID("Hydrangeas.jpg");
		// ������Դ
		text.setContent("ͼʾ���£�<br/><img src='cid:Hydrangeas.jpg'/>", "text/html;charset=utf-8");
		// ����Դ��ӵ��๦�ܿ���
		related.addBodyPart(text);
		related.addBodyPart(img);

		// �����ʼ�������Ϣ
		MailUtil.setMail(subject, new InternetAddress("lisi@itcast.com"));
		// �����ʼ�
		MailUtil.transMail(message);

	}
}
