 package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 1�����Ʊ�ǩ�������Ƿ����
	2�����Ʊ�ǩ���������Ƿ����
	3�������ظ������ǩ������
	4���ı��ǩ������
	5�������Եı�ǩ��---> �ڱ�ǩ�����������һ�����������setter����
 */
public class UseTag extends SimpleTagSupport {
	private Integer num;
	
	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public void doTag() throws JspException, IOException {
		/**
		 * 1.ʲô�������������ǩ��������
		 */
		System.out.println("��ǩ���������");
		/**
		 * 2.JspBody����invoke���������ǩ�����ǩ��������
		this.getJspBody().invoke(null); 
		//2.1ֻ��Ҫ��ǩ�����ݣ�������������
		throw new SkipPageException();
		*/
		
		/**
		 * 3.�ظ��������
		for (int i = 1; i <= 5; i++) {
			this.getJspBody().invoke(null);
		}
		*/
		
		/**
		 * 4.�ı��ǩ�������
		
		//4.1 �õ���ǩ������
		JspFragment jspBody=this.getJspBody();
		//4.2����һ��StringWriter��ʱ����  ���л�����
		StringWriter write=new StringWriter();
		//4.3�ѱ�ǩ�忽������ʱ����
		jspBody.invoke(write);
		//4.4ȡ����ʱ����������
		String content=write.toString();
		//4.5�޸�����
		content=content.toLowerCase();
		//4.6�ֶ�д���������
		JspWriter out=jspBody.getJspContext().getOut();
		out.write(content);
		 */
		/**
		 * 5.�����Եı�ǩ*/
		for (int i = 1; i <= num; i++) {
			this.getJspBody().invoke(null);
		}
		 
	}
}
