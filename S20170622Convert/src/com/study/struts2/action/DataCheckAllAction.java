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
		//System.out.println("设置的姓名是:" + userName);
		this.userName = userName;
	}

	public String[] getLikes() {
		return likes;
	}

	public void setLikes(String[] likes) {
		this.likes = likes;
	}
	// get/set 需要吗？
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
	// 经过测试只会调用 validate 和与功能函数对应的validate ， 其他的不会被调用
	public void validateAAA(){
		if(age<0 || age>60){
			addActionError("validateAAA中 年龄只能在0到60之间");
		}
	}
	
	// 自定义的功能校验方法， 必须是validate+ 自定义方法的首字母大写方法
	public void validateFunc(){
		System.out.println("我是 validateFunc 我只为func验证服务");
		if(age<0 || age>60){
			addActionError("validateFunc中 年龄只能在0到60之间");
		}
	}
	// 自定义的功能方法
	public String func() throws Exception{
		return SUCCESS;
	}
	
	// 这里测试通过 在struts.xml中写自定义method然后调用不同的方法
	public void validateFuncT1(){
		System.out.println("我是 validateFuncT1 我只为func验证服务");
		if(age<0 || age>60){
			addActionError("validateFuncT1中 年龄只能在0到60之间");
		}
	}
	public String funcT1() throws Exception{
		
		return SUCCESS;
	}
	
	public void validateFuncT2(){
		System.out.println("我是 validateFuncT2 我只为func验证服务");
		if(age<0 || age>60){
			addActionError("validateFuncT2中 年龄只能在0到60之间");
		}
	}
	public String funcT2() throws Exception{
		
		return SUCCESS;
	}
	
	public void validateFuncT3(){
		System.out.println("我是 validateFuncT3 我只为func验证服务");
		if(age<0 || age>60){
			addActionError("validateFuncT3中 年龄只能在0到60之间");
		}
	}
	public String funcT3() throws Exception{
		
		return SUCCESS;
	}
	
	
	@Override
	public void validate() {
		System.out.println("我是 validate 每个验证都要执行我");
		if(age<0 || age>60){
			addActionError("validate中 年龄只能在0到60之间");
		}
	}
	
	@Override
	public String execute() throws Exception {
		if(age<0 || age>60){
			addActionError("execute 中 年龄只能在0到60之间");
			return INPUT;
		}
		return SUCCESS;
	}
	
}
