package day03;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Demo8 {

	public static void main(String[] args) throws Exception {
		
		SAXParser parsers=SAXParserFactory.newInstance().newSAXParser();
		
		MyDefaultHandle1 handle=new MyDefaultHandle1();
		
		parsers.parse(new File("./src/contact.xml"),handle );
		
		String content=handle.getContent();
		
		System.out.println(content);
	}
}
