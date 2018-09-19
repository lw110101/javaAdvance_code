package b_case;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OtherWiseTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		ChooseTag choose=(ChooseTag)this.getParent();
		if(!choose.isFlag()) {
			this.getJspBody().invoke(null);
		}
	}
}
