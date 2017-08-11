package com.sims.mvc.model.service;

import java.util.List;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.dao.IStudentFindDAO;
import com.sims.mvc.model.dao.TeamDaoImp;

public class TeamServce{
	private TeamDaoImp stuFind=new TeamDaoImp();
	public List<Student> findStudentByTeam(String classid) {
		return stuFind.findStudentByTeam(classid);
	}
	public int updateTeam(String teamId,String id){
		return stuFind.updateTeam(teamId,id);
	}
}
