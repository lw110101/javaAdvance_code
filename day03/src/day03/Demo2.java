package day03;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.FlyweightText;
import org.junit.jupiter.api.Test;

/*
	���ӣ��ĵ�����ǩ�����ԡ�
	ɾ������ǩ������
	�޸ģ����ԡ��ı�
*/
public class Demo2 {

	@Test
	public void addTest() throws Exception {
		//�����ĵ�
		Document doc=DocumentHelper.createDocument();
		
		OutputFormat format = OutputFormat.createPrettyPrint();

		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("c:/users/hasee/desktop/person.xml"), format);
		//���ӱ�ǩ
		Element rootElem=doc.addElement("contactList");
		Element contactElem1=rootElem.addElement("contact");
		Element contactElem2=rootElem.addElement("contact");
		Element nameElem1=contactElem1.addElement("name");
		Element genderElem1=contactElem1.addElement("gender");
		Element gradeElem1=contactElem1.addElement("grade");
		Element nameElem2=contactElem2.addElement("name");
		Element genderElem2=contactElem2.addElement("gender");
		Element gradeElem2=contactElem2.addElement("grade");
		//��������
		contactElem1.addAttribute("id", "110");
		contactElem2.addAttribute("id", "120");
		//�����ı�
		nameElem1.add(new FlyweightText("����"));
		genderElem1.setText("��");
		gradeElem1.setText("2��");
		nameElem2.setText("����");
		genderElem2.setText("Ů");
		gradeElem2.setText("3��");
		
		writer.write(doc);
	}
	@Test
	public void modiTest() throws Exception {
		
		Document doc=new SAXReader().read("c:/users/hasee/desktop/person.xml");
		//д����xml�ĵ�
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("c:/users/hasee/desktop/person.xml"), format);
		
		Element contactElem=doc.getRootElement().element("contact");
		//�޸�����ֵ��ʽ1��
		contactElem.addAttribute("id", "101");
		//�޸�����ֵ��ʽ2��
		contactElem.attribute("id").setValue("110");
		//�޸��ı�
		contactElem.element("name").setText("����");
		writer.write(doc);
	}
	@Test
	public void delTest() throws Exception {
		Document doc=new SAXReader().read("c:/users/hasee/desktop/person.xml");
		
		//д����xml�ĵ�
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("c:/users/hasee/desktop/person.xml"), format);
		
		Element rootElem=doc.getRootElement();
		Element contactElem=(Element)rootElem.elements().get(1);
		//ɾ������
		contactElem.attribute("id").detach();
		//ɾ����ǩ
		contactElem.element("grade").detach();
		
		writer.write(doc);
	}
}
