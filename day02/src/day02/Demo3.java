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
		//����������
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read(new File(".\\src\\contact.xml"));
		//��ȡ����ǩ
		Element rootElem=doc.getRootElement();
		//��ȡ��ǰ��ǩ�µ�����λ�õĵ�һ��ָ�����Ʊ�ǩ
		Element elem=rootElem.element("contact");
		//��ȡ��ǰ��ǩ�������ӱ�ǩ
		Iterator<Element> it1=elem.elementIterator();
		while(it1.hasNext()) {
			Element e=it1.next();
			System.out.println(e.getName());	
		}
		/*//�����ӽ��
		Iterator<Node> it=elem.nodeIterator();
		while(it.hasNext()) {
			System.out.println(it.next().getName());
		}*/
		//��ȡ��ǰ��ǩ��ָ�����Ƶ������ӱ�ǩ
		Iterator<Element> it2=rootElem.elementIterator("contact");
		while(it2.hasNext()) {
			System.out.println(it2.next().getName());
		}
		//����������ȡ�����ӱ�ǩ
		List<Element> list=elem.elements();
		for(Element e2 :list) {
			System.out.println(e2.getName());
		}		
	}
	@Test
	public void test2() throws Exception {
		//����������
			SAXReader saxreader=new SAXReader();
			Document doc=saxreader.read(new File(".\\src\\contact.xml"));
			//����ǩ
			Element rootElem=doc.getRootElement();
			//ָ�����Ƶĵ�һ����ǩ
			Element conelem=rootElem.element("contact");
			//���Զ���
			Attribute idAttribute=conelem.attribute("id");
			//����ֵ
			System.out.println(idAttribute.getValue());
			System.out.println("========================");
			String idValue=conelem.attributeValue("id");
			System.out.println(idValue);
			//����ָ����ǩ����������
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
		//����������
		SAXReader saxreader=new SAXReader();
		Document doc=saxreader.read(new File(".\\src\\contact.xml"));
		//����ǩ
		Element nameElem=doc.getRootElement().element("contact").element("name");
		System.out.println(nameElem.getText());
		//��ȡָ�����Ƶ��ı�
		String text=doc.getRootElement().element("contact").elementText("age");
		System.out.println(text);
		
	}
}
