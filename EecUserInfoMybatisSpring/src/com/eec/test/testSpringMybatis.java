package com.eec.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinasofti.eecuser.model.dao.UserInfoMapper;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class testSpringMybatis {
	private  ApplicationContext ac;

	@Before
	public void initConfig(){
		ac = new ClassPathXmlApplicationContext("com/eec/config/Ac.xml");
	}
	
	@Test
	public void testSpringMybatisQuery(){
		UserInfoMapper uBean = ac.getBean("stuMapperDAO", UserInfoMapper.class);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("v_maxPageRows", 3);
		param.put("v_currentPageNumber", 1);
		param.put("v_condition", " AND u.role_id>=3004 AND u.role_id<=3005");
		List<UserInfo> uList = uBean.queryDataByCondition(param);
		for (UserInfo stu: uList){
			System.out.println(stu);
		}
	}
	
}
