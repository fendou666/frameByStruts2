package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Team;
import com.sims.mvc.model.dao.IStudentUpdateDAO;
import com.sims.mvc.model.service.IClassService;
import com.sims.mvc.model.service.ITeamService;
import com.sims.mvc.model.service.TeamServce;

public class testPage {
	@Test
    public void testClassService(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	IClassService bean = ac.getBean("classService", IClassService.class);
    	CClass c=new CClass("1008","1000","","","");
    	bean.addClass(c);
    }
	
	@Test
    public void testTeamService(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	ITeamService bean1 = ac.getBean("teamService", ITeamService.class);
//    	Team t=new Team("100","1000","100","10");
//    	bean1.findStudentByTeam("1");
        System.out.println("1:"+bean1.findStudentByTeam("100"));

    }
	
	@Test
    public void testStudentUpdateService(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	IStudentUpdateDAO bean2 = ac.getBean("classService", IStudentUpdateDAO.class);
//    	Team t=new Team("100","1000","100","10");
    	
    }
	
	@Test
    public void testStudentFindService(){
    	ApplicationContext ac=new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	TeamServce bean1 = ac.getBean("studentService", TeamServce.class);
    	Team t=new Team("100","1000","100","10");
    	bean1.findStudentByTeam("1");
    }
	
	
}
