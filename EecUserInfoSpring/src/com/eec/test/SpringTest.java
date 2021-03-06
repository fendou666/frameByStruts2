package com.eec.test;

import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinasofti.eecuser.model.dao.AdminTheacherDAOImp;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.AdminTheacherServiceImp;
import com.chinasofti.eecuser.model.service.IAdminTheacherService;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class SpringTest {
	
	
	private  ApplicationContext ac;

	@Before
	public void initConfig(){
		ac = new ClassPathXmlApplicationContext("com/eec/config/Ac.xml");
	}
	
	@Test
	public void testDButil(){
		DBUtil dbutil = ac.getBean("DBUtil", DBUtil.class);
		System.out.println(dbutil.getConn());
	}
	
	@Test
	public void testDAO(){
		AdminTheacherDAOImp theacherDAO = ac.getBean("AdminTheacherDAOImp", AdminTheacherDAOImp.class);
		SqlDataPage teacherPage = new SqlDataPage();
		teacherPage.setCurrentPage(1);
		teacherPage.setPageMaxRows(3);
		List<UserInfo> userList = theacherDAO.queryDataByCondition(-1, -1, -1, null, teacherPage);
		System.out.println(JSONArray.fromObject(userList).toString());
	}
	
	@Test
	public void testService(){
		IAdminTheacherService theacherService = ac.getBean("AdminTheacherServiceImp", AdminTheacherServiceImp.class);
		SqlDataPage teacherPage = new SqlDataPage();
		teacherPage.setCurrentPage(1);
		teacherPage.setPageMaxRows(3);
		List<UserInfo> userList = theacherService.queryDataByCondition(-1, -1, -1, null, teacherPage);
		System.out.println(JSONArray.fromObject(userList).toString());
	}
	
}
