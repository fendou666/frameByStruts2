package com.seasky.managersys.model.service;

import java.util.List;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.dao.IMonitorDao;
import com.seasky.managersys.model.dao.MonitorDaoImp;

public class MonitorServiceImp implements IMonitorService {
	private IMonitorDao mdi;
	
	public MonitorServiceImp(){
		mdi = new MonitorDaoImp();
	}
	
	@Override
	public List<UserInfo> getUserInfos(int classId) {
		return mdi.getUserInfos(classId);
	}

	@Override
	public int updateLeader(int id,int classId,int groupId) {
		return mdi.updateLeader(id,classId,groupId);
	}

	@Override
	public List<UserInfo> getLeaderInfos(int classId, int power) {
		return mdi.getLeaderInfos(classId, power);
	}

	@Override
	public int updateStudent(int id, int groupId) {
		return mdi.updateStudent(id, groupId);
	}

	/*@Override
	public List<UserInfo> getClassStudents(int classId) {
		return mdi.getClassStudents(classId);
	}*/

	@Override
	public List<UserInfo> getStudents(int classId, int power) {
		return mdi.getStudents(classId, power);
	}

//	获取老师的信息
	@Override
	public UserInfo getTeacherName(int id) {
		return mdi.getTeacherName(id);
	}

}
