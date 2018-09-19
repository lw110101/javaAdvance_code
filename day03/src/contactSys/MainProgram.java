package contactSys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainProgram {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		ContactOperatorImp operator=new ContactOperatorImp();
		while (true) {
			System.out.println("��ѡ����: (A)�����ϵ��     (B)ɾ����ϵ��     (C)�޸���ϵ��     (D)�鿴������ϵ��     (Q)�˳�");
			String option = br.readLine();
			
			if ("a".equalsIgnoreCase(option)) {
				Contact contact = setContact(br);
				operator.addContact(contact);
			} else if ("b".equalsIgnoreCase(option)) {
				System.out.println("������Ҫɾ������ϵ��ID:");
				String id=br.readLine();
				operator.deleteContact(id);
			} else if ("c".equalsIgnoreCase(option)) {
				Contact contact=new Contact();
				System.out.println("������Ҫ�޸ĵ���ϵ�˶���:");
				String modifID=br.readLine();
				contact.setId(modifID);
				operator.updateContact(contact);
				
			} else if ("d".equalsIgnoreCase(option)) {
				List<Contact> list=operator.checkContacts();
				for (Contact contact : list) {
					System.out.println(contact);
				}
			} else if ("q".equalsIgnoreCase(option)) {
				System.out.println("�˳�!");
				break;
				
			} else {
				System.out.println("�����д�������ѡ��");
			}
		}
	}

	protected static Contact setContact(BufferedReader br) throws IOException {
		Contact contact  = new Contact();
		System.out.println("������Ҫ��ӵı��:");
		String id=br.readLine();
		contact.setId(id);
		System.out.println("������Ҫ��ӵ�����:");
		String name = br.readLine();
		contact.setName(name);
		System.out.println("������Ҫ��ӵ��Ա�:");
		String gender=br.readLine();
		contact.setGender(gender);
		System.out.println("������Ҫ��ӵ�����:");
		int age=Integer.parseInt(br.readLine());
		contact.setAge(age);
		System.out.println("������Ҫ��ӵĵ绰:");
		String phone=br.readLine();
		contact.setPhone(phone);
		System.out.println("������Ҫ��ӵ�qq:");
		String qq=br.readLine();
		contact.setQq(qq);
		System.out.println("������Ҫ��ӵ�email:");
		String email=br.readLine();
		contact.setEmail(email);
		System.out.println("������Ҫ��ӵĵ�ַ:");
		String ad=br.readLine();
		contact.setAd(ad);
		return contact;
	}
}
