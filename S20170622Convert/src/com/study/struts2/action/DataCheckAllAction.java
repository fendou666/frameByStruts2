package com.study.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import com.study.struts2.javabean.Telephone;

public class DataCheckAllAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String userName;
	private int age;
	private String[] likes;
	private Telephone tel;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		//System.out.println("���õ�������:" + userName);
		this.userName = userName;
	}

	public String[] getLikes() {
		return likes;
	}

	public void setLikes(String[] likes) {
		this.likes = likes;
	}
	// get/set ��Ҫ��
	public Telephone getTel() {
		return tel;
	}

	public void setTel(Telephone tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	// ��������ֻ����� validate ���빦�ܺ�����Ӧ��validate �� �����Ĳ��ᱻ����
	public void validateAAA(){
		if(age<0 || age>60){
			addActionError("validateAAA�� ����ֻ����0��60֮��");
		}
	}
	
	// �Զ���Ĺ���У�鷽���� ������validate+ �Զ��巽��������ĸ��д����
	public void validateFunc(){
		System.out.println("���� validateFunc ��ֻΪfunc��֤����");
		if(age<0 || age>60){
			addActionError("validateFunc�� ����ֻ����0��60֮��");
		}
	}
	// �Զ���Ĺ��ܷ���
	public String func() throws Exception{
		return SUCCESS;
	}
	
	// �������ͨ�� ��struts.xml��д�Զ���methodȻ����ò�ͬ�ķ���
	public void validateFuncT1(){
		System.out.println("���� validateFuncT1 ��ֻΪfunc��֤����");
		if(age<0 || age>60){
			addActionError("validateFuncT1�� ����ֻ����0��60֮��");
		}
	}
	public String funcT1() throws Exception{
		
		return SUCCESS;
	}
	
	public void validateFuncT2(){
		System.out.println("���� validateFuncT2 ��ֻΪfunc��֤����");
		if(age<0 || age>60){
			addActionError("validateFuncT2�� ����ֻ����0��60֮��");
		}
	}
	public String funcT2() throws Exception{
		
		return SUCCESS;
	}
	
	public void validateFuncT3(){
		System.out.println("���� validateFuncT3 ��ֻΪfunc��֤����");
		if(age<0 || age>60){
			addActionError("validateFuncT3�� ����ֻ����0��60֮��");
		}
	}
	public String funcT3() throws Exception{
		
		return SUCCESS;
	}
	
	
	@Override
	public void validate() {
		System.out.println("���� validate ÿ����֤��Ҫִ����");
		if(age<0 || age>60){
			addActionError("validate�� ����ֻ����0��60֮��");
		}
	}
	
	@Override
	public String execute() throws Exception {
		if(age<0 || age>60){
			addActionError("execute �� ����ֻ����0��60֮��");
			return INPUT;
		}
		return SUCCESS;
	}
	
}