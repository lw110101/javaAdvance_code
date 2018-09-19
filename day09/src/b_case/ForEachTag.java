package b_case;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachTag extends SimpleTagSupport {
	private Object items;
	private String var;

	public void setItems(Object items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		/*//List集合
		PageContext pageContext = (PageContext)this.getJspContext();
		if(items instanceof List) {
			List list=(List)items;
			for(Object object:list) {
				pageContext.setAttribute(var, object);
				this.getJspBody().invoke(null);
			}
		}
		//Map集合
		if(items instanceof Map) {
			Map map=(Map)items;
			Set<Entry> entrySet=map.entrySet();
			for (Entry entry : entrySet) {
				pageContext.setAttribute(var, entry);
				this.getJspBody().invoke(null);
			}
		}*/
		PageContext pageContext = (PageContext)this.getJspContext();
		Collection collection=null;
		if(items instanceof List) {
			collection=(List)items;
		}
		if(items instanceof Map) {
			Map map=(Map)items;
			collection=map.entrySet();
		}
		for (Object object : collection) {
			pageContext.setAttribute(var, object);
			this.getJspBody().invoke(null);
		}
	}
}
