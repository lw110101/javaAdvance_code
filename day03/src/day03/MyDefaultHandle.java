package day03;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandle extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		System.out.println("文档解析开始--->");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("开始解析---->"+qName);
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("解析内容是："+new String(ch,start,length));
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("结束解析---->"+qName);
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("文档解析结束--->");
	}
}
