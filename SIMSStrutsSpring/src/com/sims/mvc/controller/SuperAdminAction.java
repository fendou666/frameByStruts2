package com.sims.mvc.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.tools.JsonMesg;
import com.sims.mvc.tools.PageManager;

public class SuperAdminAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private IStudentService stuService;
	private String result = null;
	
	private String oper = null;
	private String page = null;
	
	private String id = null;
	
	public SuperAdminAction(){
	}
	
	public String queryAdmin() throws Exception {
		PageManager.setFindPage(page);  //设置分页当前页
		
		
		//获取一般管理员列表
		List<Student> list = stuService.findStudentByIsAdmin();
		
		if(list != null){
			result =  JsonMesg.getJsonArray(list);
		}else{
			JsonMesg mesg = new JsonMesg("暂无数据");
			result = JsonMesg.getJsonObject(mesg);
		}
		return SUCCESS;	
	}
	
	public String addAdmin() throws Exception {
		Student stu = stuService.findStudentById(id);
		
		//添加的人不存在
		if(stu == null){
			JsonMesg mesg = new JsonMesg("添加的id不存在！！！");
			result = JsonMesg.getJsonObject(mesg);
		}
		
		//添加的人已经是管理员了
		else if(stu.getNormal_manager() == 1){
			JsonMesg mesg = new JsonMesg("已经是管理员了，不用重复添加！！！");
			result = JsonMesg.getJsonObject(mesg);
		}else{
			//设置此id为管理员
			stu.setNormal_manager(1);
			int res = stuService.modifyStudent(stu);
			if(res == 1){
				JsonMesg mesg = new JsonMesg("id:" + id + ",已经添加为管理员");
				result = JsonMesg.getJsonObject(mesg);
			}
		}
		
		return SUCCESS;
	}
	
	public String delAdmin() throws Exception{
		Student stu = stuService.findStudentById(id);
		
		//罢免的人不存在
		if(stu == null){
			JsonMesg mesg = new JsonMesg("罢免的id不存在！！！");
			result = JsonMesg.getJsonObject(mesg);
			return SUCCESS;
		}
		
		//罢免的人不是管理员
		else if(stu.getNormal_manager() != 1){
			JsonMesg mesg = new JsonMesg("该id不是管理员id，不能罢免！！！");
			result = JsonMesg.getJsonObject(mesg);
			return SUCCESS;
		}
		
		//罢免此id管理员
		stu.setNormal_manager(0);
		int res = stuService.modifyStudent(stu);
		if(res == 1){
			JsonMesg mesg = new JsonMesg("id:" + id + ",已经被罢免");
			result = JsonMesg.getJsonObject(mesg);
			return SUCCESS;
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public IStudentService getStuService() {
		return stuService;
	}

	public void setStuService(IStudentService stuService) {
		this.stuService = stuService;
	}
	
	@Test
	public void testDao() {
		ClassPathXmlApplicationContext ac =
				new ClassPathXmlApplicationContext("ApplicationContext.xml");
		SuperAdminAction stuDAo = (SuperAdminAction) ac.getBean("superAdminAction");
		try {
			System.out.println(stuDAo.queryAdmin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
