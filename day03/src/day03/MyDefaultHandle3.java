package day03;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDefaultHandle3 extends DefaultHandler {

	private List<Contact> list=new ArrayList<Contact>();
	
	private Contact contact;
	
	private String CurrentTarget;
	
	public List<Contact> getList(){
		return list;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		CurrentTarget=qName;
		if("contact".equals(qName)) {
			contact=new Contact();
			contact.setId(attributes.getValue("id"));
		}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String content=new String(ch,start,length);
		
		if("id".equals(CurrentTarget)) {
			contact.setId(content);
		}else if("name".equals(CurrentTarget)) {
			contact.setName(content);
		}else if("age".equals(CurrentTarget)) {
			contact.setAge(content);
		}else if("tel".equals(CurrentTarget)) {
			contact.setTel(content);
		}else if("ad".equals(CurrentTarget)) {
			contact.setAd(content);
		}else if("qq".equals(CurrentTarget)) {
			contact.setQq(content);
		}else if("email".equals(CurrentTarget)) {
			contact.setEmail(content);
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		CurrentTarget=null;
		if("contact".equals(qName)) {
			list.add(contact);
		}
	}
}
