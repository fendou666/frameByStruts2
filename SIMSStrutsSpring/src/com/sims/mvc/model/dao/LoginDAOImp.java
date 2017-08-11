package com.sims.mvc.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.tools.DBUtil;

public class LoginDAOImp implements ILoginDAO {
	private JdbcTemplate jt;
	public JdbcTemplate getJt() {
		return jt;
	}
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	public LoginDAOImp() {
	}

	@Override
	public Student checkLogin(String id, String pwd) {
		
		return jt.query("select s.s_id,s.s_pwd,s.s_name,s.s_sex,s.s_age,s.s_gradfrom,"
				+ "s.s_tel,s.s_addr,s.s_idcard,s.s_email,s.s_teamid,s.s_classid,s.isleader,"
				+ "s.ismonitor,s.ismanteach,s.isteacteach,s.isnormadmin "
				+ "from sims_student s  where s.s_id='"+ id + "'",new ResultSetExtractor<Student>(){

					@Override
					public Student extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						if(rs.next()){
							if (pwd.equals(rs.getString("s_pwd"))){
								return new Student(
										rs.getString("s_id"),
										rs.getString("s_name"),
										rs.getString("s_sex"),
										rs.getInt("s_age"),
										rs.getString("s_gradFrom"),
										(int) rs.getLong("s_tel"),
										rs.getString("s_addr"),
										rs.getString("s_idcard"),
										rs.getString("s_email"),
										rs.getString("s_teamid"),
										rs.getString("s_classid"),
										rs.getInt("isleader"),
										rs.getInt("ismonitor"),
										rs.getInt("ismanteach"),
										rs.getInt("isteacteach"),
										rs.getInt("isnormadmin")
										);
							}
						}
						return null;
					}
				});

	}

	@Override
	public int checkID(String id) {
		String sql = "select s.s_id,s.s_name from sims_student s where s.s_id='"
				+ id + "'";
		return jt.queryForInt(sql);
		
	}
	@Override
	public  String checkPermi(String per, String id) {
		if (per.equals("stu")) {
			return  "success";
		} else {
			return jt.query("select " + per + " from sims_student  where s_id='"+ id + "'", new ResultSetExtractor<String>(){
				@Override
				public String extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					if (rs.next()) {
						int perda = rs.getInt(per);
						System.out.println("checkPermi(String per, String id)==="
								+ perda);
						if (perda == 1) {
							return  "success";
						} else {
							return  "fail";
						}
					}else{
						return "fail";
					}
				}
			});
		}
	}
	
	@Test
	public void loginDAOTest(){
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		LoginDAOImp bean = ac.getBean("loginDAOImp", LoginDAOImp.class);
		System.out.println(bean.checkLogin("413", "123"));
	}
}
