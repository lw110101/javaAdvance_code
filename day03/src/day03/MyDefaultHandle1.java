package day03;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandle1 extends DefaultHandler {
	
	private StringBuffer sb=new StringBuffer();
	
	public String getContent() {
		
		return sb.toString();
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		sb.append("<"+qName);
		if(attributes!=null) {
			for(int i=0;i<attributes.getLength();i++) {
				String attName=attributes.getQName(i);
				String attValue=attributes.getValue(attName);
				sb.append(" "+attName+"=\""+attValue+"\"");
			}
		}
		sb.append(">");
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content=new String(ch,start,length);
		sb.append(content);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		sb.append("</"+qName+">");
	}
}
