package com.study.vo;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
//第一步：写一个TagSupport的子类
public class MyFirstTagLisb extends TagSupport {
	//1 定义一个接受document流的页内容对象
	private PageContext pageContext;
	private static final long serialVersionUID = 2227917439922291911L;
	private String key;
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    //2重写doStartTag和setPageContext
	@Override
	public int doStartTag() throws JspException {
		//一般标签处理都是放在开始标签内部实现
		try {
			pageContext.getOut().write("这是我第一次自定义标签:key:"+key+":value:"+value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	@Override
	//接受document流的页内容对象
	public void setPageContext(PageContext pageContext) {
		this.pageContext=pageContext;
		super.setPageContext(pageContext);
	}
}
