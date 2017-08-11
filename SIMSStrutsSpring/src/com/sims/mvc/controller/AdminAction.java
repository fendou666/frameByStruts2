package com.sims.mvc.controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.IClassService;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.tools.JsonMesg;
import com.sims.mvc.tools.PageManager;

public class AdminAction extends ActionSupport implements ModelDriven<CClass> {

	private static final long serialVersionUID = 1L;

	private String result = null;
	private JsonMesg mesg = null;

	private String page = null;
	private String classID = null;

	// 添加班级信息所有变量
	private String id = null;
	private String name = null;
	private String manTeacherID = null;
	private String teacTeacherID = null;

	// 任命班主任
	private String clID = null; // 班id
	private String clManId = null; // 需要任命的班主任id

	private CClass curClass = new CClass();
	private Student manTeach = new Student();
	private IClassService is;
	private IStudentService stuService;

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String queryClassInfo() throws Exception {
		if (classID == null || classID.equals("")) {
			mesg = new JsonMesg("1");// 班号不能为空
		} else {
			curClass = is.findClassById(classID); // 查询当前班信息
			if (curClass != null) {
				result = JsonMesg.getJsonArray(curClass);
			} else {
				mesg = new JsonMesg("2");// 查无此班！
			}
		}
		if (mesg != null) {
			result = JsonMesg.getJsonObject(mesg);
		}
		return SUCCESS;
	}

	public String queryAllClassesInfo() throws Exception {
		PageManager.setFindPage(page);
		List<CClass> classAll = is.getClassesAll();

		if (classAll == null) {
			mesg = new JsonMesg("3");// "没有班级，请创建！"
		} else {
			result = JsonMesg.getJsonArray(classAll);
		}

		if (mesg != null) {
			result = JsonMesg.getJsonObject(mesg);
		}
		return SUCCESS;
	}

	public String addClassInfo() throws Exception {

		if (id == null || id.equals("") || name == null || name.equals("")
				|| manTeacherID == null || manTeacherID.equals("")
				|| teacTeacherID == null || teacTeacherID.equals("")) {
			mesg = new JsonMesg("4");// "请填写所有项！"
		} else {
			CClass cur_class = is.findClassById(id); // 查看是否已有，其实可以做成ajax

			if (cur_class != null) {
				mesg = new JsonMesg("5");// "当前班级已经存在！"
			} else {
				// 创建班
				CClass cc = new CClass(id, name, null, manTeacherID,
						teacTeacherID);

				// 添加班
				int isAdd = is.addClass(cc);

				// 如果添加成功
				if (isAdd > 0) {
					// 查询出需要做班主任的人，添加班id和设置班主任权限
					manTeach = stuService.findStudentById(manTeacherID);
					manTeach.setClassID(id); // 添加班id
					manTeach.setC_man_teacher(1); // 设置班主任权限
					stuService.modifyStudent(manTeach);

					// 查询出需要做任课老师的人，添加班id和设置班主任权限
					Student techTeach = stuService
							.findStudentById(teacTeacherID);
					techTeach.setClassID(id); // 添加班id
					techTeach.setC_teac_teacher(1); // 设置任课老师权限
					stuService.modifyStudent(techTeach);

					mesg = new JsonMesg("6");// "新建成功！"
				}
			}
		}

		if (mesg != null) {
			result = JsonMesg.getJsonObject(mesg);
		}
		return SUCCESS;
	}

	// 任命班主任
	public String appointManTeacher() throws Exception {
		int modifyNew = 0;

		if (clID == null || clID.equals("") || clManId == null
				|| clManId.equals("")) {
			mesg = new JsonMesg("7"); // 班级ID或班主任ID不能为空！
		} else {

			curClass = is.findClassById(clID); // 根据传入的班级id获取班级
			String oldManTeaId = curClass.getManTeacherID();// 获取当前班班主任id

			Student oldManTeacher = stuService.findStudentById(oldManTeaId); // 通过班主任id拿到班主任对象

			List<Student> classStudents = stuService.findStudentByClassID(clID); // 获取本班学生集合

			if (classStudents != null) {
				for (Student s : classStudents) {
					// 如果输入的班主任id是本班学生id
					if (s.getC_man_teacher() == 0
							&& s.getClassID().equals(clID)) {

						// 更新本班班主任id
						curClass.setManTeacherID(clManId);
						int modifyClass = is.modifyClass(curClass);

						// 如果更新成功，则设置此人权限
						if (modifyClass > 0) {
							if (oldManTeacher != null) { // 如果当前班原有班主任，则取消其权限
								oldManTeacher.setC_man_teacher(0);
								stuService.modifyStudent(oldManTeacher);
							}

							s.setC_man_teacher(1);
							modifyNew = stuService.modifyStudent(s);
							break;
						}
					}
				}
			}

			if (modifyNew > 0) {
				mesg = new JsonMesg("8"); // 任命成功！
			} else {
				mesg = new JsonMesg("9"); // 任命失败，不是本班人！
			}
		}

		if (mesg != null) {
			result = JsonMesg.getJsonObject(mesg);
		}
		return SUCCESS;
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

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
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

	public String getManTeacherID() {
		return manTeacherID;
	}

	public void setManTeacherID(String manTeacherID) {
		this.manTeacherID = manTeacherID;
	}

	public String getTeacTeacherID() {
		return teacTeacherID;
	}

	public void setTeacTeacherID(String teacTeacherID) {
		this.teacTeacherID = teacTeacherID;
	}

	public String getClID() {
		return clID;
	}

	public void setClID(String clID) {
		this.clID = clID;
	}

	public String getClManId() {
		return clManId;
	}

	public void setClManId(String clManId) {
		this.clManId = clManId;
	}

	@Override
	public CClass getModel() {
		return null;
	}

	public IClassService getIs() {
		return is;
	}

	public void setIs(IClassService is) {
		this.is = is;
	}

	public IStudentService getStuService() {
		return stuService;
	}

	public void setStuService(IStudentService stuService) {
		this.stuService = stuService;
	}

}
