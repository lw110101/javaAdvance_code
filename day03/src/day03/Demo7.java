package day03;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Demo7 {

	public static void main(String[] args) throws Exception {
		
		SAXParser parsers=SAXParserFactory.newInstance().newSAXParser();
		parsers.parse(new File("./src/contact.xml"), new MyDefaultHandle());
	}
}
