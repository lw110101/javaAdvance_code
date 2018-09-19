package contactSys;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

public class ContactOperatorImp implements ContactOperator {
	 Element rootElem = null;
	 File file=new File("./src/contactSys/contact.xml");
	@Override //�����ϵ��
	public void addContact(Contact contact)  {
		
		try {
			Document doc = XmlUtil.getDocment(file);
			rootElem=doc.getRootElement();
			
			Element contactElem = rootElem.addElement("contact");
			
			Element elem=(Element) rootElem.selectSingleNode("//contact[@id='" + contact.getId() + "']");
			if(elem!=null) {
				System.out.println("��ϵ�˵�ID��Ψһ�ģ��Ѵ��ڴ�ID!");
			}else {
				contactElem.addAttribute("id", contact.getId());
				contactElem.addElement("name").setText(contact.getName());
				contactElem.addElement("gender").setText(contact.getGender());
				contactElem.addElement("age").setText(contact.getAge() + "");
				contactElem.addElement("email").setText(contact.getEmail());
				contactElem.addElement("qq").setText(contact.getQq());
				contactElem.addElement("phone").setText(contact.getPhone());
				contactElem.addElement("ad").setText(contact.getAd());
				XmlUtil.xmlWriter(doc,file);	
				System.out.println("��ӳɹ�!");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}

	@Override//ɾ����ϵ��
	public void deleteContact(String id) {
		try {
			Document doc = XmlUtil.getDocment(file);
			rootElem=doc.getRootElement();
			Element contatcElem = (Element) rootElem.selectSingleNode("//contact[@id='" + id + "']");
			if (contatcElem == null) {
				System.out.println("�����ڸ�ID����ϵ��!");
			} else {
				contatcElem.detach();
				System.out.println("ɾ���ɹ�");
				XmlUtil.xmlWriter(doc,file);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override//�޸���ϵ��
	public void updateContact(Contact contact) {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			Document doc = XmlUtil.getDocment(file);
			rootElem=doc.getRootElement();
			Element modifContact = (Element) rootElem.selectSingleNode("//contact[@id='" + contact.getId() + "']");

			if (modifContact != null) {
				System.out.println("��ϵ����Ϣ:"+contact);
				System.out.println("��������Ҫ�޸ĵ���ϵ����Ϣ:");

				String modifInfo = br.readLine();
				Element selElem = (Element) modifContact.selectSingleNode(modifInfo);
				if(selElem!=null) {
					System.out.println("�������޸ĺ����Ϣ:");
					String content = br.readLine();
					selElem.setText(content);
					System.out.println("�޸ĳɹ���");
					XmlUtil.xmlWriter(doc,file);
				}else {
					System.out.println("����Ϣ������!");
				}
				
			} else {
				System.out.println("����ϵ�˲����ڣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override//�鿴������ϵ��
	public List<Contact> checkContacts() {
		List<Contact> list = new ArrayList<Contact>();
		try {
			Document doc = XmlUtil.getDocment(file);

			@SuppressWarnings("unchecked")
			List<Element> contactElems = doc.selectNodes("/contactList/contact");
			if (contactElems.size() == 0) {
				System.out.println("û��һ����ϵ��!");
			} else {
				for (Element elem : contactElems) {
					Contact contact = new Contact();
					contact.setId(elem.attributeValue("id"));
					contact.setName(elem.elementText("name"));
					contact.setGender(elem.elementText("gender"));
					contact.setAge(Integer.parseInt(elem.elementText("age")));
					contact.setPhone(elem.elementText("phone"));
					contact.setEmail(elem.elementText("email"));
					contact.setQq(elem.elementText("qq"));
					contact.setAd(elem.elementText("ad"));
					list.add(contact);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
}
