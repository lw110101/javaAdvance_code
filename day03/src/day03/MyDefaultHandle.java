package day03;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandle extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		System.out.println("�ĵ�������ʼ--->");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("��ʼ����---->"+qName);
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("���������ǣ�"+new String(ch,start,length));
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("��������---->"+qName);
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("�ĵ���������--->");
	}
}
