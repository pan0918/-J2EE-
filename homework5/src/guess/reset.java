package guess;

import javax.servlet.jsp.tagext.BodyTagSupport;

public class reset extends BodyTagSupport{
	public NumberGuessBean bean;
	public void setbean(NumberGuessBean bean)
	{
		this.bean=bean;
	}
	public int doStartTag(){
		bean.reset();
		return EVAL_BODY_INCLUDE;
	}
}
