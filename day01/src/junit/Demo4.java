package junit;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

public class Demo4 {

	@Test
	public void testProperty() throws Exception {
		Person p = new Person();
		// 属性描述器
		PropertyDescriptor descriptor = new PropertyDescriptor("id", Person.class);
		// 获取对应的set或get方法
		Method m = descriptor.getWriteMethod();
		m.invoke(p, 110);
		System.out.println(p);
	}
	
	@Test
	public void getAllProperty() throws Exception {
		
		BeanInfo beaninfo=Introspector.getBeanInfo(Person.class);
		
		PropertyDescriptor[] propertys=beaninfo.getPropertyDescriptors();
		
		for(PropertyDescriptor p:propertys) {
			//System.out.println(p.getReadMethod());
			
			System.out.println(p.getWriteMethod());
		}
		
		/*MethodDescriptor[] methods=beaninfo.getMethodDescriptors();

		for(MethodDescriptor m:methods) {
			//System.out.println(p.getReadMethod());
			
			System.out.println(m.getMethod());
		}*/
		
	}
}
