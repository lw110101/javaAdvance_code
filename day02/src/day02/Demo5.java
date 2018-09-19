package day02;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo5 {

	public static void main(String[] args) throws Exception {
		
		List<Contact> list=new ArrayList();	
		
		SAXReader reader=new SAXReader();
		
		Document doc=reader.read(new File("./src/contact.xml"));
		
		Element rootElem=doc.getRootElement();
		
		Iterator<Element> it=rootElem.elementIterator("contact");
		
		while(it.hasNext()) {
			Contact contact=new Contact();
			Element elem=it.next();
			contact.setId(elem.attributeValue("id"));
			contact.setName(elem.elementText("name"));
			contact.setAge(elem.elementText("age"));
			contact.setTel(elem.elementText("tel"));
			contact.setAd(elem.elementText("ad"));
			contact.setQq(elem.elementText("qq"));
			contact.setEmail(elem.elementText("email"));
			list.add(contact);
		}
		for(Contact obj:list) {
			System.out.println(obj);
		}
	}
}
