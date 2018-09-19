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
		//绝对路径    “/”
		xpath="/contactList";
		xpath="/contactList/contact";
		//相对路径
		xpath="//name";
		xpath="//contact/name";
		//属性
	
		xpath="//contact[@id='120']";
		xpath="//contact[@id='110' and @name='狗娃']";
		xpath="//contact[not(@id)]";
		//条件
		xpath="//contact[@id]";
		xpath="//contact[2]";
		xpath="//contact[last()]";
		// text()   表示选择文本内容
		xpath="//name/text()";
		xpath="//name[text()='张三']";
		//通配符
		xpath="/contactList/*";
		xpath="/contactList//*";
		
		@SuppressWarnings("unchecked")
		List<Node> nodes=doc.selectNodes(xpath);
		for (Node node : nodes) {
			System.out.println(node);
		}
		
	}
}
