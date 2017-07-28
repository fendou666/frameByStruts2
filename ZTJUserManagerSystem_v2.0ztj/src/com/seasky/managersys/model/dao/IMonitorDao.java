package com.seasky.managersys.model.dao;

import java.util.List;

import com.seasky.managersys.model.bean.UserInfo;

public interface IMonitorDao {
	public List<UserInfo> getUserInfos(int classId);
	
	public int updateLeader(int id,int classId,int groupId);
	
	public List<UserInfo> getLeaderInfos(int classId,int power);
	
	public int updateStudent(int id,int groupId);
	
	public UserInfo getTeacherName(int id);

	/*public List<UserInfo> getClassStudents(int classId);//获取除班长以外的学员的数据
*/	
	public List<UserInfo> getStudents(int classId,int power);//获取普通学员的数据
}
