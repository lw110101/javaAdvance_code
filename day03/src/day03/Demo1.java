package day03;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		//��ȡdocument
		SAXReader reader=new SAXReader();
		Document doc=reader.read("./src/contact.xml");
		
		OutputStream output=new FileOutputStream("c:/users/hasee/desktop/contact.xml");
	//��ʽ
		//OutputFormat format=OutputFormat.createCompactFormat();
		OutputFormat format=OutputFormat.createPrettyPrint();
		//���ñ���
		format.setEncoding("utf-8");
	//д��xml�ĵ�
		XMLWriter writer=new XMLWriter(output,format);
		writer.write(doc);
		writer.close();
		
		
	}
}
