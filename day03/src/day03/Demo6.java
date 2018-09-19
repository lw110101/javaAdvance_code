package day03;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo6 {

	public static void main(String[] args) throws Exception {
		
		Document doc = new SAXReader().read("./src/personList.html");
		
		@SuppressWarnings("unchecked")
		List<Element> list=doc.selectNodes("//tbody/tr");
		
		for (Element elem : list) {
		/*	方式一：
			String id=((Element)elem.elements().get(0)).getText();
			
			String name=((Element)elem.elements().get(1)).getText();
			
			String gender=((Element)elem.elements().get(2)).getText();
			
			String age=((Element)elem.elements().get(3)).getText();
			
			String address=((Element)elem.elements().get(4)).getText();
			
			String phone=((Element)elem.elements().get(5)).getText();
			*/
			//方式二：
			String id=(elem.selectSingleNode("td[1]")).getText();
			
			String name=(elem.selectSingleNode("td[2]")).getText();
			
			String gender=(elem.selectSingleNode("td[3]")).getText();
			
			String age=(elem.selectSingleNode("td[4]")).getText();
			
			String address=(elem.selectSingleNode("td[5]")).getText();
			
			String phone=(elem.selectSingleNode("td[6]")).getText();
			
			System.out.println("编号:"+id+"\t姓名:"+name+"\t性别:"+gender+"\t年龄:"+age+"\t地址:"+address+"\t电话:"+phone);
		}
	}
}
