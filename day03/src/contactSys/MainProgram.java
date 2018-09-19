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
			System.out.println("请选择功能: (A)添加联系人     (B)删除联系人     (C)修改联系人     (D)查看所有联系人     (Q)退出");
			String option = br.readLine();
			
			if ("a".equalsIgnoreCase(option)) {
				Contact contact = setContact(br);
				operator.addContact(contact);
			} else if ("b".equalsIgnoreCase(option)) {
				System.out.println("请输入要删除的联系人ID:");
				String id=br.readLine();
				operator.deleteContact(id);
			} else if ("c".equalsIgnoreCase(option)) {
				Contact contact=new Contact();
				System.out.println("请输入要修改的联系人对象:");
				String modifID=br.readLine();
				contact.setId(modifID);
				operator.updateContact(contact);
				
			} else if ("d".equalsIgnoreCase(option)) {
				List<Contact> list=operator.checkContacts();
				for (Contact contact : list) {
					System.out.println(contact);
				}
			} else if ("q".equalsIgnoreCase(option)) {
				System.out.println("退出!");
				break;
				
			} else {
				System.out.println("输入有错，请重新选择！");
			}
		}
	}

	protected static Contact setContact(BufferedReader br) throws IOException {
		Contact contact  = new Contact();
		System.out.println("请输入要添加的编号:");
		String id=br.readLine();
		contact.setId(id);
		System.out.println("请输入要添加的姓名:");
		String name = br.readLine();
		contact.setName(name);
		System.out.println("请输入要添加的性别:");
		String gender=br.readLine();
		contact.setGender(gender);
		System.out.println("请输入要添加的年龄:");
		int age=Integer.parseInt(br.readLine());
		contact.setAge(age);
		System.out.println("请输入要添加的电话:");
		String phone=br.readLine();
		contact.setPhone(phone);
		System.out.println("请输入要添加的qq:");
		String qq=br.readLine();
		contact.setQq(qq);
		System.out.println("请输入要添加的email:");
		String email=br.readLine();
		contact.setEmail(email);
		System.out.println("请输入要添加的地址:");
		String ad=br.readLine();
		contact.setAd(ad);
		return contact;
	}
}
