package day03;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Demo9 {

	public static void main(String[] args) throws Exception {
		SAXParser parsers=SAXParserFactory.newInstance().newSAXParser();
		
		MyDefaultHandle3 handle=new MyDefaultHandle3();
		
		parsers.parse(new File("./src/contact.xml"), handle);
		
		List<Contact> list=handle.getList();
		
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
