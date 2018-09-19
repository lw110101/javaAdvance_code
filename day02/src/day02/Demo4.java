package day02;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;

public class Demo4 {

	public static void main(String[] args) throws Exception {
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File(".\\src\\contact.xml"));
		Element rootElem=doc.getRootElement();
		StringBuffer sb=new StringBuffer();
		test(rootElem,sb);
		System.out.println(sb.toString());
	}
	public static void  test(Element elem,StringBuffer sb) {
		sb.append("<"+elem.getName());
		//±êÇ©ÊôÐÔ
		List<Attribute> atts=elem.attributes();
		if(atts!=null) {
			for(Attribute att:atts) {
				sb.append(" "+att.getName()+"="+"\""+att.getValue()+"\"");
			}
		}
		sb.append(">");
		Iterator<Node> it=elem.nodeIterator();
		while(it.hasNext()) {
			Node node=it.next();
			if(node instanceof Element) {
				Element e=(Element)node;
				test(e,sb);
			}
			if(node instanceof Text) {
				Text text=(Text)node;
				sb.append(text.getText());
			}
			
		}
		sb.append("</"+elem.getName()+">");
			}
	}

