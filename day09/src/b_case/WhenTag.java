package b_case;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WhenTag extends SimpleTagSupport {
	private boolean test;
	
	public void setTest(boolean test) {
		this.test = test;
	}

	@Override
	public void doTag() throws JspException, IOException {
	
		if(test) {
			this.getJspBody().invoke(null);
		}
		ChooseTag parent=(ChooseTag)this.getParent();
		parent.setFlag(test);
	}
}
