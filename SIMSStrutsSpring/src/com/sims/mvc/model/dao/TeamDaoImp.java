package com.sims.mvc.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.tools.DBUtil;

public class TeamDaoImp{
	DBUtil dbutil = DBUtil.getInstance();
	public List<Student> findStudentByTeam(String classid) {
		List<Student> list=null;
		String sql=null;
		sql="select s.s_id,s.s_name,s.s_sex,s.s_age,s.s_gradfrom,s.s_tel,s.s_addr,s.s_idcard,s.s_email,s.s_teamid from sims_student s where s.s_team is null and s.s_classid='"+classid+"'";
		ResultSet rs = dbutil.query(sql);
		try {
			while(rs.next()){
				Student stu=new Student();
				stu.setId(rs.getString("s_id"));
				System.out.println(stu.getId());
				stu.setName(rs.getString("s_name"));
				stu.setSex(rs.getString("s_sex"));
				System.out.println(stu.getSex());
				stu.setAge(rs.getInt("s_age"));
				stu.setGradFrom(rs.getString("s_gradFrom"));
				stu.setAddr(rs.getString("s_addr"));
				stu.setIdCard(rs.getString("s_idcard"));
				stu.setEmail(rs.getString("s_email"));
				stu.setTeamID(rs.getString("s_teamid"));
				if(rs.isFirst()){
					list=new ArrayList<Student>();
				}
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int updateTeam(String teamId,String id){
		String sql=null;
		sql="update sims_student  set s_teamid='"+teamId+"' where s_id='"+id+"'";
		return dbutil.executeUpdate(sql);
	}
	}


