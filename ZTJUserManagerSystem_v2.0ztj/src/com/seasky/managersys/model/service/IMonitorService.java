package com.seasky.managersys.model.service;

import java.util.List;

import com.seasky.managersys.model.bean.UserInfo;

public interface IMonitorService {
	public List<UserInfo> getUserInfos(int classId);
	
	public int updateLeader(int id,int classId,int groupId);
	
	public List<UserInfo> getLeaderInfos(int classId,int power);
	
	public int updateStudent(int id,int groupId);
	
	public UserInfo getTeacherName(int id);
	
	/*public List<UserInfo> getClassStudents(int classId);//获取除班长以外的学员的数据
*/	
	public List<UserInfo> getStudents(int classId,int power);//获取学员的数据
}
