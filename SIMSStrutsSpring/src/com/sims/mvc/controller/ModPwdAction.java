package com.sims.mvc.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.ILoginService;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.LoginServiceImp;
import com.sims.mvc.model.service.StudentServiceImp;

public class ModPwdAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 9004076591895638572L;
	private String old_pwd;
	private String new_pwd;
	private String con_pwd;
	private Map<String, Object> session = null;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String modPwd() throws Exception {
		if (old_pwd == null || new_pwd == null || con_pwd == null
				|| old_pwd.equals("") || new_pwd.equals("")
				|| con_pwd.equals("")) {
			addActionMessage("���벻��Ϊ��");
			return "pwdfail";
		}

		if (!new_pwd.equals(con_pwd)) {
			addActionMessage("�����������벻һ��");
			return "pwdfail";
		}
		Student cur_stu = (Student) session.get("stuInfo");
		String id = cur_stu.getId();
		ILoginService loginSerivce = new LoginServiceImp();
		Student stu = loginSerivce.checkStuInfo(id, old_pwd);
		System.out.println("stu" + stu);
		if (stu != null) {
			IStudentService stuService = new StudentServiceImp();
			int res = stuService.modifyStudentPWD(id, new_pwd);
			if (res > 0) {
				addActionMessage("�����޸�");// ����ʲô����˵���ʾ
				session.put("linkTitle", "�����޸�");// ��ָҪ��ת��ҳ��������ʾ
				session.put("link", "/content/student/mod_pwd.jsp"); // Ҫ��ת�����ӵ�ַurl
				return "success";
			} else {
				addActionMessage("��Ǹ����̨������");
				return "pwdfail";
			}
		} else {
			addActionMessage("ԭ�������");
			return "pwdfail";
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getOld_pwd() {
		return old_pwd;
	}

	public void setOld_pwd(String old_pwd) {
		this.old_pwd = old_pwd;
	}

	public String getNew_pwd() {
		return new_pwd;
	}

	public void setNew_pwd(String new_pwd) {
		this.new_pwd = new_pwd;
	}

	public String getCon_pwd() {
		return con_pwd;
	}

	public void setCon_pwd(String con_pwd) {
		this.con_pwd = con_pwd;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
