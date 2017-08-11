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

	// ��Ӱ༶��Ϣ���б���
	private String id = null;
	private String name = null;
	private String manTeacherID = null;
	private String teacTeacherID = null;

	// ����������
	private String clID = null; // ��id
	private String clManId = null; // ��Ҫ�����İ�����id

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
			mesg = new JsonMesg("1");// ��Ų���Ϊ��
		} else {
			curClass = is.findClassById(classID); // ��ѯ��ǰ����Ϣ
			if (curClass != null) {
				result = JsonMesg.getJsonArray(curClass);
			} else {
				mesg = new JsonMesg("2");// ���޴˰࣡
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
			mesg = new JsonMesg("3");// "û�а༶���봴����"
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
			mesg = new JsonMesg("4");// "����д�����"
		} else {
			CClass cur_class = is.findClassById(id); // �鿴�Ƿ����У���ʵ��������ajax

			if (cur_class != null) {
				mesg = new JsonMesg("5");// "��ǰ�༶�Ѿ����ڣ�"
			} else {
				// ������
				CClass cc = new CClass(id, name, null, manTeacherID,
						teacTeacherID);

				// ��Ӱ�
				int isAdd = is.addClass(cc);

				// �����ӳɹ�
				if (isAdd > 0) {
					// ��ѯ����Ҫ�������ε��ˣ���Ӱ�id�����ð�����Ȩ��
					manTeach = stuService.findStudentById(manTeacherID);
					manTeach.setClassID(id); // ��Ӱ�id
					manTeach.setC_man_teacher(1); // ���ð�����Ȩ��
					stuService.modifyStudent(manTeach);

					// ��ѯ����Ҫ���ο���ʦ���ˣ���Ӱ�id�����ð�����Ȩ��
					Student techTeach = stuService
							.findStudentById(teacTeacherID);
					techTeach.setClassID(id); // ��Ӱ�id
					techTeach.setC_teac_teacher(1); // �����ο���ʦȨ��
					stuService.modifyStudent(techTeach);

					mesg = new JsonMesg("6");// "�½��ɹ���"
				}
			}
		}

		if (mesg != null) {
			result = JsonMesg.getJsonObject(mesg);
		}
		return SUCCESS;
	}

	// ����������
	public String appointManTeacher() throws Exception {
		int modifyNew = 0;

		if (clID == null || clID.equals("") || clManId == null
				|| clManId.equals("")) {
			mesg = new JsonMesg("7"); // �༶ID�������ID����Ϊ�գ�
		} else {

			curClass = is.findClassById(clID); // ���ݴ���İ༶id��ȡ�༶
			String oldManTeaId = curClass.getManTeacherID();// ��ȡ��ǰ�������id

			Student oldManTeacher = stuService.findStudentById(oldManTeaId); // ͨ��������id�õ������ζ���

			List<Student> classStudents = stuService.findStudentByClassID(clID); // ��ȡ����ѧ������

			if (classStudents != null) {
				for (Student s : classStudents) {
					// �������İ�����id�Ǳ���ѧ��id
					if (s.getC_man_teacher() == 0
							&& s.getClassID().equals(clID)) {

						// ���±��������id
						curClass.setManTeacherID(clManId);
						int modifyClass = is.modifyClass(curClass);

						// ������³ɹ��������ô���Ȩ��
						if (modifyClass > 0) {
							if (oldManTeacher != null) { // �����ǰ��ԭ�а����Σ���ȡ����Ȩ��
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
				mesg = new JsonMesg("8"); // �����ɹ���
			} else {
				mesg = new JsonMesg("9"); // ����ʧ�ܣ����Ǳ����ˣ�
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
