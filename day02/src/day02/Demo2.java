package day02;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Demo2 {

	// 创建第一个dom4j的例子
	public static void main(String[] args) throws Exception {
		// 创建解析器对象
		SAXReader saxreader = new SAXReader();
		Document doc = saxreader.read(new File(".\\src\\contact.xml"));
		System.out.println(doc);
		Iterator<Node> it = doc.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			System.out.println(node.getName());
			if (node instanceof Element) {
				Element elem = (Element) node;
				Iterator<Node> it2 = elem.nodeIterator();
				while (it2.hasNext()) {
					Node node1 = it2.next();
					System.out.println(node1.getName());
				}
			}
		}
	}
}
