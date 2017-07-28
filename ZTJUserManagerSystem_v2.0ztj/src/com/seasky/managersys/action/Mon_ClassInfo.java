package com.seasky.managersys.action;


import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.service.IMonitorService;
import com.seasky.managersys.model.service.MonitorServiceImp;

public class Mon_ClassInfo extends ActionSupport {

	private static final long serialVersionUID = 2238219768089766780L;//序列化
	//界面提交的数据
	private int classId;	
	private int headmasterId;
	private int teacherId;
//	提交给界面的数据
	private UserInfo teacher;//任课老师
	private UserInfo headmaster;//班主任
	private List<UserInfo> stuInfos;//所在班级所有成员
	
	@Override
	public String execute() throws Exception {
		IMonitorService mService = new MonitorServiceImp();
		teacher = mService.getTeacherName(teacherId);
		headmaster = mService.getTeacherName(headmasterId);
		stuInfos = mService.getUserInfos(classId);
		if(stuInfos == null){
			return INPUT;
		}
		return SUCCESS;
	}
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getHeadmasterId() {
		return headmasterId;
	}
	public void setHeadmasterId(int headmasterId) {
		this.headmasterId = headmasterId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public UserInfo getTeacher() {
		return teacher;
	}

	public void setTeacher(UserInfo teacher) {
		this.teacher = teacher;
	}

	public UserInfo getHeadmaster() {
		return headmaster;
	}

	public void setHeadmaster(UserInfo headmaster) {
		this.headmaster = headmaster;
	}

	public List<UserInfo> getStuInfos() {
		return stuInfos;
	}

	public void setStuInfos(List<UserInfo> stuInfos) {
		this.stuInfos = stuInfos;
	}
}
