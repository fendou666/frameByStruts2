package com.sims.mvc.model.bean;

public class CClass {
	private String id;
	private String name;
	private String monitorID;   // 班长id
	private String manTeacherID;  // 班主任id
	private String teacTeacherID; // 任课老师id
	public CClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CClass(String id, String name, String monitorID, String manTeacher,
			String teacTeacher) {
		super();
		this.id = id;
		this.name = name;
		this.monitorID = monitorID;
		this.manTeacherID = manTeacher;
		this.teacTeacherID = teacTeacher;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMonitorID() {
		return monitorID;
	}
	public void setMonitorID(String monitorID) {
		this.monitorID = monitorID;
	}
	public String getManTeacherID() {
		return manTeacherID;
	}
	public void setManTeacherID(String manTeacher) {
		this.manTeacherID = manTeacher;
	}
	public String getTeacTeacherID() {
		return teacTeacherID;
	}
	public void setTeacTeacherID(String teacTeacher) {
		this.teacTeacherID = teacTeacher;
	}
}
