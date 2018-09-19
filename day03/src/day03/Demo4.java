package day03;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Demo4 {

	public static void main(String[] args) throws Exception {
		
		Document doc=new SAXReader().read(new File("./src/person.xml"));
		
		String xpath="";
		//����·��    ��/��
		xpath="/contactList";
		xpath="/contactList/contact";
		//���·��
		xpath="//name";
		xpath="//contact/name";
		//����
	
		xpath="//contact[@id='120']";
		xpath="//contact[@id='110' and @name='����']";
		xpath="//contact[not(@id)]";
		//����
		xpath="//contact[@id]";
		xpath="//contact[2]";
		xpath="//contact[last()]";
		// text()   ��ʾѡ���ı�����
		xpath="//name/text()";
		xpath="//name[text()='����']";
		//ͨ���
		xpath="/contactList/*";
		xpath="/contactList//*";
		
		@SuppressWarnings("unchecked")
		List<Node> nodes=doc.selectNodes(xpath);
		for (Node node : nodes) {
			System.out.println(node);
		}
		
	}
}
