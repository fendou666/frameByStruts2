package com.eec.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	
	private  ApplicationContext ac;

	@Before
	public void initConfig(){
		ac = new ClassPathXmlApplicationContext("com/eec/config/Ac.xml");
	}
	
	@Test
	public void testDButil(){
		
	}
	
	@Test
	public void testDAO(){
		
	}
	
	@Test
	public void testService(){
		
	}
	
}
