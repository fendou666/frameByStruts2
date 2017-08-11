package com.sims.mvc.model.dao;

import java.util.List;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.bean.Team;

public interface ITeamDAO {
	//通过id查询组
	Team findTeamById(String id);
	
	//通过组名查询组
	List<Team> findTeamsByName(String name);
	
	//通过组长id查询组
	List<Team> findTeamsByLeaderID(String leaderID);
	
	//通过班级id查询组
	List<Team> findTeamsByClassID(String classID);
	
	//添加组
	int addTeam(Team team);
	
	//修改组
	int modifyTeam(Team team);
	
	//删除组
	int deleteTeam(String teamID);
	
	int updateTeam(String teamId,String id);
	
  List<Student> findStudentByTeam(String classid);
}
