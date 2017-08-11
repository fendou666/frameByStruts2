package com.sims.mvc.controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.ClassServiceImp;
import com.sims.mvc.model.service.IClassService;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.StudentServiceImp;
import com.sims.mvc.tools.JsonMesg;
import com.sims.mvc.tools.PageManager;

public class ManTeacherAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private String action = null;
	private String classid = null;
	private String studyid = null;
	private String page = null;
	private String id;
	private String name;
	private String style;
	private IStudentService stservice;
	private IClassService clas ;

	private Map<String, Object> session = null;

	private String result = null;
	public ManTeacherAction(){}

	// 查询班级学员信息
	public String queClassStudent() {
		// 获取班id
		Student stu = (Student) session.get("stuInfo");
		String classId = stu.getClassID();
		List<Student> list = stservice.findStudentByClassID(classId);
		PageManager.setFindPage(page);
		result = JsonMesg.getJsonArray(list);
		return SUCCESS;
	}

	// 查询所有未分班学员信息
	public String queNotClassStudent() {
		List<Student> list = stservice.findStudentByClassID("");
		PageManager.setFindPage(page);
		result = JsonMesg.getJsonArray(list);
		return SUCCESS;
	}
	
	

	// 任命罢免班长
	public String classManagee() {
		Student stu = (Student) session.get("stuInfo");
		String classid = stu.getClassID();
		CClass ClassById = clas.findClassById(classid);
		List<Student> list = stservice.findStudentByClassID(classid);
		for (Student s : list) {
			if (s.getC_monitor() == 1) {
				s.setC_monitor(0);
				stservice.modifyStudent(s);
			}
			if (studyid.equals(s.getId()) && s.getC_monitor() != 1) {
				s.setC_monitor(1);
				stservice.modifyStudent(s);
				ClassById.setId(classid);
				ClassById.setMonitorID(studyid);
				clas.modifyClass(ClassById); 
			}
		}
		return SUCCESS;
	}

	// 添加班级新成员
	public String insertStudents() {
		Student stu = (Student) session.get("stuInfo");
		String classId = stu.getClassID();
		Student s = stservice.findStudentById(studyid);
		s.setClassID(classId);
		stservice.modifyStudent(s);
		return SUCCESS;
	}

	// 开除班级学生
	public String deleteStudents() {
		Student s = stservice.findStudentById(studyid);
		s.setClassID("");
		stservice.modifyStudent(s);
		return SUCCESS;
	}

	@Override
	public String execute() {
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getStudyid() {
		return studyid;
	}

	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public IStudentService getStservice() {
		return stservice;
	}

	public void setStservice(IStudentService stservice) {
		this.stservice = stservice;
	}

	public IClassService getClas() {
		return clas;
	}

	public void setClas(IClassService clas) {
		this.clas = clas;
	}
	

}
