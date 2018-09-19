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
	增加：文档、标签、属性、
	删除：标签、属性
	修改：属性、文本
*/
public class Demo2 {

	@Test
	public void addTest() throws Exception {
		//增加文档
		Document doc=DocumentHelper.createDocument();
		
		OutputFormat format = OutputFormat.createPrettyPrint();

		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("c:/users/hasee/desktop/person.xml"), format);
		//增加标签
		Element rootElem=doc.addElement("contactList");
		Element contactElem1=rootElem.addElement("contact");
		Element contactElem2=rootElem.addElement("contact");
		Element nameElem1=contactElem1.addElement("name");
		Element genderElem1=contactElem1.addElement("gender");
		Element gradeElem1=contactElem1.addElement("grade");
		Element nameElem2=contactElem2.addElement("name");
		Element genderElem2=contactElem2.addElement("gender");
		Element gradeElem2=contactElem2.addElement("grade");
		//增加属性
		contactElem1.addAttribute("id", "110");
		contactElem2.addAttribute("id", "120");
		//增加文本
		nameElem1.add(new FlyweightText("张三"));
		genderElem1.setText("男");
		gradeElem1.setText("2班");
		nameElem2.setText("李四");
		genderElem2.setText("女");
		gradeElem2.setText("3班");
		
		writer.write(doc);
	}
	@Test
	public void modiTest() throws Exception {
		
		Document doc=new SAXReader().read("c:/users/hasee/desktop/person.xml");
		//写出到xml文档
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("c:/users/hasee/desktop/person.xml"), format);
		
		Element contactElem=doc.getRootElement().element("contact");
		//修改属性值方式1：
		contactElem.addAttribute("id", "101");
		//修改属性值方式2：
		contactElem.attribute("id").setValue("110");
		//修改文本
		contactElem.element("name").setText("狗娃");
		writer.write(doc);
	}
	@Test
	public void delTest() throws Exception {
		Document doc=new SAXReader().read("c:/users/hasee/desktop/person.xml");
		
		//写出到xml文档
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("c:/users/hasee/desktop/person.xml"), format);
		
		Element rootElem=doc.getRootElement();
		Element contactElem=(Element)rootElem.elements().get(1);
		//删除属性
		contactElem.attribute("id").detach();
		//删除标签
		contactElem.element("grade").detach();
		
		writer.write(doc);
	}
}
