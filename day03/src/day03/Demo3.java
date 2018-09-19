package day03;

import java.io.FileOutputStream;
import java.io.OutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Demo3 {

	public static void main(String[] args) throws Exception {
		
		Document doc=DocumentHelper.createDocument();
		//���ӱ�ǩ
		Element rootElem=doc.addElement("Students");
		Element sElem1=rootElem.addElement("Student");
		Element sElem2=rootElem.addElement("Student");
		Element nameElem1=sElem1.addElement("name");
		Element genderElem1=sElem1.addElement("gender");
		Element gradeElem1=sElem1.addElement("grade");
		Element adElem1=sElem1.addElement("address");
		Element nameElem2=sElem2.addElement("name");
		Element genderElem2=sElem2.addElement("gender");
		Element gradeElem2=sElem2.addElement("grade");
		Element adElem2=sElem2.addElement("address");
		
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		OutputStream outStream=new FileOutputStream("c:/users/hasee/desktop/student.xml");
		XMLWriter writer= new XMLWriter(outStream,format);
		//��������
		sElem1.addAttribute("id", "1");
		sElem2.addAttribute("id", "2");
		//�����ı�
		nameElem1.addText("����");
		genderElem1.addText("��");
		gradeElem1.addText("�����1��");
		adElem1.addText("�ϲ�������");
		nameElem2.addText("����");
		genderElem2.addText("Ů");
		gradeElem2.addText("�����3��");
		adElem2.addText("�ϲ���������");
		//�޸�
		/*	nameElem2.setText("С��");
		//ɾ��
		sElem2.detach();*/
		writer.write(doc);
	}
}
