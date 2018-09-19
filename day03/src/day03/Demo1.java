package day03;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		//读取document
		SAXReader reader=new SAXReader();
		Document doc=reader.read("./src/contact.xml");
		
		OutputStream output=new FileOutputStream("c:/users/hasee/desktop/contact.xml");
	//格式
		//OutputFormat format=OutputFormat.createCompactFormat();
		OutputFormat format=OutputFormat.createPrettyPrint();
		//设置编码
		format.setEncoding("utf-8");
	//写出xml文档
		XMLWriter writer=new XMLWriter(output,format);
		writer.write(doc);
		writer.close();
		
		
	}
}
