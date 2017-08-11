package com.sims.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.IStudentService;

public class UpdateStudentAction extends ActionSupport implements SessionAware ,ModelDriven{
	private static final long serialVersionUID = 4674157187613558039L;
	
	private IStudentService stuService;
	private Student stu=new Student();
	
	public IStudentService getStuService() {
		return stuService;
	}

	public void setStuService(IStudentService stuService) {
		this.stuService = stuService;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	private Map<String, Object> session = null;
	HttpServletResponse response = null;
	private String stuid;
	private String stuname;
	private String stusex;
	private String stuage;
	private String stugradFrom;
	private String stutel;
	private String stuaddr;
	private String stuidCard;
	private String stuemail;

	@Override
	public String execute() throws Exception {
		if (session.get("userinfo") == null) {
			addActionMessage("����û�е�¼�������µ�¼��");
		}

		// ѧԱ������Ϣ�޸�
		// ��������ȡ��
		stusex = stusex.equals("0") ? "��" : "Ů";
		if (stuid == null || stuname == null || stusex == null
				|| stuage == null || stugradFrom == null || stutel == null
				|| stuaddr == null || stuidCard == null || stuemail == null) {
			return INPUT;
		}
		
		Student stu = stuService.findStudentById(stuid);
		stu.setName(stuname);
		stu.setSex(stusex);
		stu.setAge(Integer.parseInt(stuage));
		stu.setAddr(stuaddr);
		stu.setGradFrom(stugradFrom);
		stu.setTel(Long.parseLong(stutel));
		stu.setIdCard(stuidCard);
		stu.setEmail(stuemail);

		// �޸�����
		int res = stuService.modifyStudent(stu);
		if (res == 1) {
			session.put("stuInfo", stu);
			addActionMessage("������Ϣ�޸�"); // ����ʲô����˵���ʾ
			session.put("linkTitle", "������Ϣ�޸�"); // ��ָҪ��ת��ҳ��������ʾ
			session.put("link", "/content/student/updateStuInfo.jsp"); // Ҫ��ת�����ӵ�ַurl
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStusex() {
		return stusex;
	}

	public void setStusex(String stusex) {
		this.stusex = stusex;
	}

	public String getStuage() {
		return stuage;
	}

	public void setStuage(String stuage) {
		this.stuage = stuage;
	}

	public String getStugradFrom() {
		return stugradFrom;
	}

	public void setStugradFrom(String stugradFrom) {
		this.stugradFrom = stugradFrom;
	}

	public String getStutel() {
		return stutel;
	}

	public void setStutel(String stutel) {
		this.stutel = stutel;
	}

	public String getStuaddr() {
		return stuaddr;
	}

	public void setStuaddr(String stuaddr) {
		this.stuaddr = stuaddr;
	}

	public String getStuidCard() {
		return stuidCard;
	}

	public void setStuidCard(String stuidCard) {
		this.stuidCard = stuidCard;
	}

	public String getStuemail() {
		return stuemail;
	}

	public void setStuemail(String stuemail) {
		this.stuemail = stuemail;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public Object getModel() {
		return stu;
	}
}