package contactSys_jsp_dao_Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.Element;

import contactSys_jsp_entity.Contact;
import contactSys_jsp_util.XmlUtil;
import contactSys_jsp_dao.ContactDao;

public class ContactDaoImpl implements ContactDao {
	File file = new File("E:/contactSys_jsp_contact.xml");
	Document doc=null;
	@Override
	// 添加联系人
	public void addContact(Contact contact) {
		doc=XmlUtil.getDocment(file);
		Element rootElem=doc.getRootElement();
		Element contactElem = rootElem.addElement("contact");
		/**
		 * 由系统自动生成随机且唯一的ID值，赋值给联系人
		 */
		String uuid = UUID.randomUUID().toString().replace("-", "");
		// 封装数据
		contactElem.addAttribute("id", uuid);
		contactElem.addElement("name").setText(contact.getName());
		contactElem.addElement("gender").setText(contact.getGender());
		contactElem.addElement("age").setText(contact.getAge() + "");
		contactElem.addElement("email").setText(contact.getEmail());
		contactElem.addElement("qq").setText(contact.getQq());
		contactElem.addElement("phone").setText(contact.getPhone());
		contactElem.addElement("ad").setText(contact.getAd());
		XmlUtil.xmlWriter(doc, file);
	}

	@Override // 删除联系人
	public void deleteContact(String id) {

		doc = XmlUtil.getDocment(file);
		Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" + id + "']");
		if (contactElem != null) {
			contactElem.detach();
		}
		XmlUtil.xmlWriter(doc,file);
	}

	@Override // 修改联系人
	public void updateContact(Contact contact) {
		
		doc = XmlUtil.getDocment(file);
		Element modifContact = (Element) doc.selectSingleNode("//contact[@id='" + contact.getId() + "']");
		// 修改联系人内容
		modifContact.element("name").setText(contact.getName());
		modifContact.element("gender").setText(contact.getGender());
		modifContact.element("age").setText(contact.getAge() + "");
		modifContact.element("email").setText(contact.getEmail());
		modifContact.element("qq").setText(contact.getQq());
		modifContact.element("phone").setText(contact.getPhone());
		modifContact.element("ad").setText(contact.getAd());

		XmlUtil.xmlWriter(doc,file);
	}

	@SuppressWarnings("unchecked")
	@Override // 查看所有联系人
	public List<Contact> checkContacts() {
		List<Contact> list = new ArrayList<Contact>();
		doc = XmlUtil.getDocment(file);

		List<Element> contactElems =(List<Element>) doc.selectNodes("//contact");
		if (contactElems.size() == 0) {
			System.out.println("没有一个联系人!");
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
		return list;

	}

	@Override // 根据id查询联系人
	public Contact findById(String id) {

		doc = XmlUtil.getDocment(file);

		Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" + id + "']");
		Contact contact = null;
		if (contactElem != null) {
			// 创建COntact对象
			contact = new Contact();
			contact.setId(contactElem.attributeValue("id"));
			contact.setName(contactElem.elementText("name"));
			contact.setGender(contactElem.elementText("gender"));
			contact.setAge(Integer.parseInt(contactElem.elementText("age")));
			contact.setPhone(contactElem.elementText("phone"));
			contact.setEmail(contactElem.elementText("email"));
			contact.setQq(contactElem.elementText("qq"));
			contact.setAd(contactElem.elementText("ad"));
		}
		return contact;
	}
	//测试UUID的方法
	public static void main(String[] args) {
		String id=UUID.randomUUID().toString();
		System.out.println(id);
	}

	@Override
	public boolean checkName(String name) {
		doc=XmlUtil.getDocment(file);
		Element elem=(Element)doc.selectSingleNode("//name[text()='"+name+"']");
		if (elem != null) {
			return true;
		} else {
			return false;
		}
	}
}
