package com.sims.mvc.model.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.tools.DBUtil;

public class StudentUpdateDAOImp implements IStudentUpdateDAO {
	private JdbcTemplate jt = null;
	
	public StudentUpdateDAOImp(){
	}
	@Override
	public int addStudent(Student student, String pwd) {
		String sql="insert into sims_student values('"
			+student.getId()+"','"
			+pwd+"','"
			+student.getName()+"','"
			+student.getSex()+"',"
			+student.getAge()+",'"
			+student.getGradFrom()+"',"
			+student.getTel()+",'"
			+student.getAddr()+"','"
			+student.getIdCard()+"','"
			+student.getEmail()+"',null,null,0,0,0,0,0)";
		
		return jt.update(sql);
	}

	@Override
	public int deleteStuent(Student student) {
		return 0;
	}

	@Override
	public int modifyStudent(Student student) {
		String sql="update sims_student "
			+ " set s_name='"+student.getName()
			+ "',   s_sex='"+student.getSex()
			+"',    s_age="+student.getAge()
			+",     s_gradfrom='"+student.getGradFrom()
			+"',    s_tel="+student.getTel()
			+",     s_addr='"+student.getAddr()
			+"',    s_idCard='"+student.getIdCard()
			+"',    s_email='"+student.getEmail()
			+"',	s_teamid='"+(student.getTeamID()==null?"":student.getTeamID())
			+"',	s_classid='"+(student.getClassID()==null?"":student.getClassID())
			+"',    isleader="+student.getT_leader()
			+",     ismonitor="+student.getC_monitor()
			+",     isnormadmin="+student.getC_man_teacher()
			+",     isteacteach="+student.getC_teac_teacher()
			+",     ismanteach="+student.getNormal_manager()
			+"  where s_id='"+student.getId() + "'";
		
		return jt.update(sql);
	}
	
	@Override
	public int modifyStudentPWD(String id, String pwd) {
		String sql="update sims_student s set s.s_pwd="+pwd+" where s.s_id='" + id + "'";
		return jt.update(sql);
	}
	
	public JdbcTemplate getJt() {
		return jt;
	}
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	@Test
	public void testDao() {
		
		ClassPathXmlApplicationContext ac =
				new ClassPathXmlApplicationContext("ApplicationContext.xml");
		IStudentUpdateDAO stuDAo = (IStudentUpdateDAO) ac.getBean("studentUpdateDAOImp");
		System.out.println(stuDAo.modifyStudentPWD("1", "123"));
	}
}
