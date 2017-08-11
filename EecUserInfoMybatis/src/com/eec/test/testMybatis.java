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

import com.chinasofti.eecuser.model.dao.UserInfoMapper;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class testMybatis {
	private String mybatisConfigLocation;
	private SqlSessionFactory sqfc;
	private SqlSession ops;
	@Before
	public void testDao() throws IOException{
		mybatisConfigLocation = "com/chinasofti/eecuser/config/mybatis.xml";
		// 获取配置流
		InputStream is = Resources.getResourceAsStream(mybatisConfigLocation);
		// 创建工厂
		sqfc = new  SqlSessionFactoryBuilder().build(is);
		ops = sqfc.openSession();
		UserInfoMapper uMap = ops.getMapper(UserInfoMapper.class);
		UserInfo userInfo = uMap.selectByPrimaryKey(170203021L);
		System.out.println(userInfo.toString());
	}
	
	@Test
	public void testTeacherQuery(){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("v_maxPageRows", 3);
		param.put("v_currentPageNumber", 1);
		param.put("v_condition", " AND u.role_id>=3004 AND u.role_id<=3005");
		ops.selectOne("com.chinasofti.eecuser.model.dao.UserInfoMapper.queryDataByCondition", param);
		for (UserInfo stu: (List<UserInfo>)param.get("result")){
			System.out.println(stu);
		}
		System.out.println("总记录条数为" + param.get("v_count"));
	}
	
	
}
