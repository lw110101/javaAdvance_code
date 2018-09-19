package day02;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

public class Demo3 {

	@Test
	public void test1() throws Exception {
		//创建解析器
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read(new File(".\\src\\contact.xml"));
		//获取根标签
		Element rootElem=doc.getRootElement();
		//获取当前标签下的任意位置的第一个指定名称标签
		Element elem=rootElem.element("contact");
		//获取当前标签下所有子标签
		Iterator<Element> it1=elem.elementIterator();
		while(it1.hasNext()) {
			Element e=it1.next();
			System.out.println(e.getName());	
		}
		/*//所有子结点
		Iterator<Node> it=elem.nodeIterator();
		while(it.hasNext()) {
			System.out.println(it.next().getName());
		}*/
		//获取当前标签下指定名称的所有子标签
		Iterator<Element> it2=rootElem.elementIterator("contact");
		while(it2.hasNext()) {
			System.out.println(it2.next().getName());
		}
		//方法二，获取所有子标签
		List<Element> list=elem.elements();
		for(Element e2 :list) {
			System.out.println(e2.getName());
		}		
	}
	@Test
	public void test2() throws Exception {
		//创建解析器
			SAXReader saxreader=new SAXReader();
			Document doc=saxreader.read(new File(".\\src\\contact.xml"));
			//根标签
			Element rootElem=doc.getRootElement();
			//指定名称的第一个标签
			Element conelem=rootElem.element("contact");
			//属性对象
			Attribute idAttribute=conelem.attribute("id");
			//属性值
			System.out.println(idAttribute.getValue());
			System.out.println("========================");
			String idValue=conelem.attributeValue("id");
			System.out.println(idValue);
			//遍历指定标签的所有属性
			/*Iterator<Attribute> it=conelem.attributeIterator();
			while(it.hasNext()) {
				System.out.println(it.next().getValue());
			}
			
			List<Attribute> list=conelem.attributes();
			for(Attribute att :list) {
				System.out.println(att.getValue());
			}*/
	}
	@Test
	public void test3() throws Exception{
		//创建解析器
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read(new File(".\\src\\contact.xml"));
		//根标签
		Element nameElem=doc.getRootElement().element("contact").element("name");
		System.out.println(nameElem.getText());
		//获取指定名称的文本
		String text=doc.getRootElement().element("contact").elementText("age");
		System.out.println(text);
		
	}
}
