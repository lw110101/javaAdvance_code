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
		//增加标签
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
		//增加属性
		sElem1.addAttribute("id", "1");
		sElem2.addAttribute("id", "2");
		//增加文本
		nameElem1.addText("张三");
		genderElem1.addText("男");
		gradeElem1.addText("计算机1班");
		adElem1.addText("南昌西湖区");
		nameElem2.addText("李四");
		genderElem2.addText("女");
		gradeElem2.addText("计算机3班");
		adElem2.addText("南昌青云谱区");
		//修改
		/*	nameElem2.setText("小芹");
		//删除
		sElem2.detach();*/
		writer.write(doc);
	}
}
