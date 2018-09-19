package day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/*		设计一个通讯录程序
 * 			联系人： 编号  姓名  性别  年龄  电话  QQ 邮箱
				功能要求：
					添加联系人
					修改联系人
					删除联系人
					查询所有联系人	
			要求： 
			1）交互使用console（控制台）	
			2）数据存储到xml文件（作为“数据库”）（使用dom4j）
 */
public class Demo10 {
	
	static Document doc;
	static Element rootElem;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static File file=new File("./src/person.xml");
	public static void main(String[] args) throws Exception {
		doc = new SAXReader().read(file);
		rootElem = doc.getRootElement();
		OutputFormat format = OutputFormat.createPrettyPrint();
		FileOutputStream out=new FileOutputStream(file);
		
				// 业务逻辑
		System.out.println("==================  通讯录    ==================================");
		while(true) {
			System.out.println("请选择功能: 添加联系人(A)  修改联系人(B)  删除联系人(C)   查询所有联系人(D)  ");

			String option = br.readLine();

			if ("a".equalsIgnoreCase(option)) {
				addPerson();
			} else if ("b".equalsIgnoreCase(option)) {
				modifyElem();
			} else if ("c".equalsIgnoreCase(option)) {
				delPerson();
			} else if ("d".equalsIgnoreCase(option)) {
				checkAll();
			} else {
				break;
				
			}
			// 写出到xml文档
			new XMLWriter(out, format).write(doc);
			
		}
	
	}

// 查询
	public static void checkAll() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Element> personElems =doc.selectNodes("/personList/person");
		if (personElems.size() == 0) {
			System.out.println("没有一个联系人");
		} else {
			for (Element element : personElems) {
				String id = element.attributeValue("id");
				String name = element.selectSingleNode("name").getText();
				String gender = element.selectSingleNode("gender").getText();
				String age = element.selectSingleNode("age").getText();
				String phone = element.selectSingleNode("tel").getText();
				String qq = element.selectSingleNode("qq").getText();
				String email = element.selectSingleNode("email").getText();
				System.out.println("编号:" + id + " 姓名:" + name + " 性别:" + gender + " 年龄:" + age + " 电话:" + phone + " qq:"
						+ qq + " 邮箱:" + email);
			}
		}
	}
// 删除
	public static void delPerson() throws Exception {
		// 删除联系人
		System.out.println("请输入要删除的联系人Id:");
		String id = br.readLine();
		Element personElem = (Element) rootElem.selectSingleNode("//person[@id='" + id + "']");
		if (personElem != null) {
			personElem.detach();
			System.out.println("删除操作完毕");
		} else {
			System.out.println("不存在此id的联系人");
		}
	}

	// 修改
	public static void modifyElem() throws IOException {
		System.out.println("请选择修改标签:  属性(A)  文本(B)");
		String cases = br.readLine();
		if ("a".equalsIgnoreCase(cases)) {
			System.out.println("请选择要修改的属性对象:");
			String modifObj = br.readLine();
			Element modifPerson = (Element) rootElem.selectSingleNode("//person[@id='" + modifObj + "']");
			if (modifPerson != null) {
				System.out.println("请输入修改的属性值:");
				String content = br.readLine();
				modifPerson.addAttribute("id", content);
			} else {
				System.out.println("不存在该属性对象的联系人");
			}
		} else if ("b".equalsIgnoreCase(cases)) {
			System.out.println("请输入要修改的联系人对象:");
			String modifObj = br.readLine();
			Element modifPerson = (Element) rootElem.selectSingleNode("//person[@id='" + modifObj + "']");
			if (modifPerson != null) {
				System.out.println("请输入要修改的文本标签:");
				String modifElem = br.readLine();
				Element selElem = (Element) modifPerson.selectSingleNode(modifElem);
				System.out.println("请输入修改的文本内容:");
				String content = br.readLine();
				selElem.setText(content);
			} else {
				System.out.println("该文本标签不存在！");
			}
		}
		System.out.println("修改完毕!");
	}

	// 增加
	public static void addPerson() throws IOException {

		Element personElem = rootElem.addElement("person");
		System.out.println("请输入要添加的编号:");
		personElem.addAttribute("id", br.readLine());
		System.out.println("请输入要添加的姓名:");
		Element nameElem = personElem.addElement("姓名");
		nameElem.setText(br.readLine());
		System.out.println("请输入要添加的性别:");
		Element genderElem = personElem.addElement("性别");
		genderElem.setText(br.readLine());
		System.out.println("请输入要添加的年龄:");
		Element ageElem = personElem.addElement("年龄");
		ageElem.setText(br.readLine());
		System.out.println("请输入要添加的电话:");
		Element phoneElem = personElem.addElement("电话");
		phoneElem.setText(br.readLine());
		System.out.println("请输入要添加的qq:");
		Element qqElem = personElem.addElement("qq");
		qqElem.setText(br.readLine());
		System.out.println("请输入要添加的email:");
		Element emailElem = personElem.addElement("email");
		emailElem.setText(br.readLine());
		System.out.println("添加完毕!");
	}
}
