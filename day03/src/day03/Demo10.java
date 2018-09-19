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

/*		���һ��ͨѶ¼����
 * 			��ϵ�ˣ� ���  ����  �Ա�  ����  �绰  QQ ����
				����Ҫ��
					�����ϵ��
					�޸���ϵ��
					ɾ����ϵ��
					��ѯ������ϵ��	
			Ҫ�� 
			1������ʹ��console������̨��	
			2�����ݴ洢��xml�ļ�����Ϊ�����ݿ⡱����ʹ��dom4j��
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
		
				// ҵ���߼�
		System.out.println("==================  ͨѶ¼    ==================================");
		while(true) {
			System.out.println("��ѡ����: �����ϵ��(A)  �޸���ϵ��(B)  ɾ����ϵ��(C)   ��ѯ������ϵ��(D)  ");

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
			// д����xml�ĵ�
			new XMLWriter(out, format).write(doc);
			
		}
	
	}

// ��ѯ
	public static void checkAll() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Element> personElems =doc.selectNodes("/personList/person");
		if (personElems.size() == 0) {
			System.out.println("û��һ����ϵ��");
		} else {
			for (Element element : personElems) {
				String id = element.attributeValue("id");
				String name = element.selectSingleNode("name").getText();
				String gender = element.selectSingleNode("gender").getText();
				String age = element.selectSingleNode("age").getText();
				String phone = element.selectSingleNode("tel").getText();
				String qq = element.selectSingleNode("qq").getText();
				String email = element.selectSingleNode("email").getText();
				System.out.println("���:" + id + " ����:" + name + " �Ա�:" + gender + " ����:" + age + " �绰:" + phone + " qq:"
						+ qq + " ����:" + email);
			}
		}
	}
// ɾ��
	public static void delPerson() throws Exception {
		// ɾ����ϵ��
		System.out.println("������Ҫɾ������ϵ��Id:");
		String id = br.readLine();
		Element personElem = (Element) rootElem.selectSingleNode("//person[@id='" + id + "']");
		if (personElem != null) {
			personElem.detach();
			System.out.println("ɾ���������");
		} else {
			System.out.println("�����ڴ�id����ϵ��");
		}
	}

	// �޸�
	public static void modifyElem() throws IOException {
		System.out.println("��ѡ���޸ı�ǩ:  ����(A)  �ı�(B)");
		String cases = br.readLine();
		if ("a".equalsIgnoreCase(cases)) {
			System.out.println("��ѡ��Ҫ�޸ĵ����Զ���:");
			String modifObj = br.readLine();
			Element modifPerson = (Element) rootElem.selectSingleNode("//person[@id='" + modifObj + "']");
			if (modifPerson != null) {
				System.out.println("�������޸ĵ�����ֵ:");
				String content = br.readLine();
				modifPerson.addAttribute("id", content);
			} else {
				System.out.println("�����ڸ����Զ������ϵ��");
			}
		} else if ("b".equalsIgnoreCase(cases)) {
			System.out.println("������Ҫ�޸ĵ���ϵ�˶���:");
			String modifObj = br.readLine();
			Element modifPerson = (Element) rootElem.selectSingleNode("//person[@id='" + modifObj + "']");
			if (modifPerson != null) {
				System.out.println("������Ҫ�޸ĵ��ı���ǩ:");
				String modifElem = br.readLine();
				Element selElem = (Element) modifPerson.selectSingleNode(modifElem);
				System.out.println("�������޸ĵ��ı�����:");
				String content = br.readLine();
				selElem.setText(content);
			} else {
				System.out.println("���ı���ǩ�����ڣ�");
			}
		}
		System.out.println("�޸����!");
	}

	// ����
	public static void addPerson() throws IOException {

		Element personElem = rootElem.addElement("person");
		System.out.println("������Ҫ��ӵı��:");
		personElem.addAttribute("id", br.readLine());
		System.out.println("������Ҫ��ӵ�����:");
		Element nameElem = personElem.addElement("����");
		nameElem.setText(br.readLine());
		System.out.println("������Ҫ��ӵ��Ա�:");
		Element genderElem = personElem.addElement("�Ա�");
		genderElem.setText(br.readLine());
		System.out.println("������Ҫ��ӵ�����:");
		Element ageElem = personElem.addElement("����");
		ageElem.setText(br.readLine());
		System.out.println("������Ҫ��ӵĵ绰:");
		Element phoneElem = personElem.addElement("�绰");
		phoneElem.setText(br.readLine());
		System.out.println("������Ҫ��ӵ�qq:");
		Element qqElem = personElem.addElement("qq");
		qqElem.setText(br.readLine());
		System.out.println("������Ҫ��ӵ�email:");
		Element emailElem = personElem.addElement("email");
		emailElem.setText(br.readLine());
		System.out.println("������!");
	}
}
