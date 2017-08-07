package com.chinasofti.eecuser.model.javabean;

public class ClassInfo {
	
	private int classId;
	private int monitorId;
	private int theTeacherId;
	private int headTeacherId;
	private String describe;
	private String monitorName;
	private String theTeacherName;
	private String headTeacherName;
	
	public ClassInfo() {
	
	}

	public ClassInfo(int classId, int monitorId, int theTeacherId,
			int headTeacherId, String describe, String monitorName,
			String theTeacherName, String headTeacherName) {
		super();
		this.classId = classId;
		this.monitorId = monitorId;
		this.theTeacherId = theTeacherId;
		this.headTeacherId = headTeacherId;
		this.describe = describe;
		this.monitorName = monitorName;
		this.theTeacherName = theTeacherName;
		this.headTeacherName = headTeacherName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}

	public int getTheTeacherId() {
		return theTeacherId;
	}

	public void setTheTeacherId(int theTeacherId) {
		this.theTeacherId = theTeacherId;
	}

	public int getHeadTeacherId() {
		return headTeacherId;
	}

	public void setHeadTeacherId(int headTeacherId) {
		this.headTeacherId = headTeacherId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getTheTeacherName() {
		return theTeacherName;
	}

	public void setTheTeacherName(String theTeacherName) {
		this.theTeacherName = theTeacherName;
	}

	public String getHeadTeacherName() {
		return headTeacherName;
	}

	public void setHeadTeacherName(String headTeacherName) {
		this.headTeacherName = headTeacherName;
	}

	@Override
	public String toString() {
		return "ClassInfo [classId=" + classId + ", monitorId=" + monitorId
				+ ", theTeacherId=" + theTeacherId + ", headTeacherId="
				+ headTeacherId + ", describe=" + describe + ", monitorName="
				+ monitorName + ", theTeacherName=" + theTeacherName
				+ ", headTeacherName=" + headTeacherName + "]";
	}
	
	
}
