package junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class Demo5 {

	public static void main(String[] args) throws Exception {
		
		String id="110";
		
		String name="小花";
		
		String birthday="2018-3-8";
		//注册一个转换器
		ConvertUtils.register(new Converter() {
			
			@SuppressWarnings("rawtypes")
			@Override// type : 目前所遇到的数据类型。  value :目前参数的值。		
			public Object convert(Class type, Object value) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = format.parse(value.toString());
				} catch (ParseException e) {

					e.printStackTrace();
				}
				return date;
			}
		}, Date.class);
		
		Person p=new Person();
		BeanUtils.setProperty(p, "id", id);
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "birthday", birthday);
		
		System.out.println(p);
	}
}
 