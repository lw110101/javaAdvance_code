package tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ShowIpTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		HttpServletRequest request=(HttpServletRequest)((PageContext)this.getJspContext()).getRequest();
		
		String ip=request.getRemoteHost();
		
		JspWriter out=this.getJspContext().getOut();
		
		out.write("当前的Ip地址是:"+ip);
	}
}
