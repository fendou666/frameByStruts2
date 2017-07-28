package com.study.vo;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
//��һ����дһ��TagSupport������
public class MyFirstTagLisb extends TagSupport {
	//1 ����һ������document����ҳ���ݶ���
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
    //2��дdoStartTag��setPageContext
	@Override
	public int doStartTag() throws JspException {
		//һ���ǩ�����Ƿ��ڿ�ʼ��ǩ�ڲ�ʵ��
		try {
			pageContext.getOut().write("�����ҵ�һ���Զ����ǩ:key:"+key+":value:"+value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	@Override
	//����document����ҳ���ݶ���
	public void setPageContext(PageContext pageContext) {
		this.pageContext=pageContext;
		super.setPageContext(pageContext);
	}
}
