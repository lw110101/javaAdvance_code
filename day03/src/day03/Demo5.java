package day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Demo5 {

	public static void main(String[] args) throws Exception {
		
		Document doc=new SAXReader().read(new File("./src/user.xml"));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("�������¼��:");
		String name=br.readLine();
		
		System.out.println("����������:");
		String password=br.readLine();
		
		String xpath="/users/user[@userName='"+name+"' and @password='"+password+"']";
		Node node=doc.selectSingleNode(xpath);
		
		if(node!=null) {
			System.out.println("��¼�ɹ�");
		}else {
			System.out.println("��¼ʧ��");
		}
		
	}
}
