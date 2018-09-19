 package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 1）控制标签体内容是否输出
	2）控制标签余下内容是否输出
	3）控制重复输出标签体内容
	4）改变标签体内容
	5）带属性的标签。---> 在标签处理器中添加一个成语变量和setter方法
 */
public class UseTag extends SimpleTagSupport {
	private Integer num;
	
	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public void doTag() throws JspException, IOException {
		/**
		 * 1.什么都不做，输出标签余下内容
		 */
		System.out.println("标签内容输出了");
		/**
		 * 2.JspBody调用invoke方法输出标签体与标签余下内容
		this.getJspBody().invoke(null); 
		//2.1只需要标签体内容，不用余下内容
		throw new SkipPageException();
		*/
		
		/**
		 * 3.重复输出内容
		for (int i = 1; i <= 5; i++) {
			this.getJspBody().invoke(null);
		}
		*/
		
		/**
		 * 4.改变标签体的内容
		
		//4.1 得到标签体内容
		JspFragment jspBody=this.getJspBody();
		//4.2创建一个StringWriter临时容器  带有缓冲区
		StringWriter write=new StringWriter();
		//4.3把标签体拷贝到临时容器
		jspBody.invoke(write);
		//4.4取出临时容器的内容
		String content=write.toString();
		//4.5修改内容
		content=content.toLowerCase();
		//4.6手动写出到浏览器
		JspWriter out=jspBody.getJspContext().getOut();
		out.write(content);
		 */
		/**
		 * 5.带属性的标签*/
		for (int i = 1; i <= num; i++) {
			this.getJspBody().invoke(null);
		}
		 
	}
}
