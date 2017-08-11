package com.sims.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.dao.LoginDAOImp;
import com.sims.mvc.model.service.ILoginService;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.LoginServiceImp;
import com.sims.mvc.model.service.StudentServiceImp;
import com.sims.mvc.tools.JsonMesg;

public class LoginAction extends ActionSupport implements RequestAware, SessionAware{
	private static final long serialVersionUID = 1L;
	private String userId = null;
	private String pwd = null;
	private String permissions = null;
	private String randNum = null;  
	private String result = null;
	
	private String name;	//����
	private String sex;	//�Ա�
	private String age;	//����
	private String gradFrom;	//��ҵԺУ
	private String tel;	//�绰����
	private String addr;	//��ַ
	private String idCard;	//���֤����
	private String email;	//����
	
	private ILoginService loginService;
	private IStudentService studentService;
	
	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	private Student stuInfo = null;
	private Map<String, Object> request;
	private Map<String, Object> session;

	public LoginAction() {
	}
	
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String login() throws Exception{
		System.out.println("----------------------login");
		
//		PrintWriter out = response.getWriter();
		JsonMesg mesg = null;
		Object getRandNum = session.get("rand");

		if (userId == null || pwd == null || userId.equals("")
				|| pwd.equals("") || permissions == null
				|| permissions.equals("")) {
			
			mesg = new JsonMesg("1");
			System.out.println("----------------------login1");
			result = JsonMesg.getJsonObject(mesg);
			
			return "success";
		} else {
//			ILoginService iLoginService = new LoginServiceImp();
			stuInfo = loginService.checkStuInfo(userId, pwd);

			if (stuInfo == null) {
				mesg = new JsonMesg("2");
				result = JsonMesg.getJsonObject(mesg);
				System.out.println("----------------------login2");
				System.out.println("----------------------result:" + result);
				return "success";
			} else {
				
				String permi = loginService.checkPermi(permissions, userId);
				
				//��֤Ȩ��
				if (permi.equals("fail")) {
					
					mesg = new JsonMesg("3");
					result = JsonMesg.getJsonObject(mesg);
					System.out.println("----------------------login3");
					return "success";
				}
				
				//��֤����֤
				if (!randNum.equals(getRandNum)) {
					
					mesg = new JsonMesg("4");
					result = JsonMesg.getJsonObject(mesg);
					System.out.println("----------------------login4");
					return "success";
				}
				
				// ��stuInfo�ŵ�session
				session.put("stuInfo", stuInfo);
				System.out.println("permissions:" + permissions);

				if (stuInfo.getId().equals("admin")) {
					permissions = "admin";
				}
				
				mesg = new JsonMesg("5");
				result = JsonMesg.getJsonObject(mesg);
				session.put("user_right", permissions);
				System.out.println("----------------------login5");
				// ��ת��ѧ����Ϣչʾҳ��
				return "success";
			}
		}
		
	}
	
	public String regist() throws Exception{
		if(userId==null||userId.equals("")||pwd==null||pwd.equals("")||name==null||name.equals("")||sex==null||sex.equals("")
				 ||age==null||age.equals("")||gradFrom.equals("")||gradFrom==null||tel.equals("")||tel==null||
				 addr.equals("")||addr==null||idCard.equals("")||idCard==null||email.equals("")||email==null){
			addActionError("ע����Ϣ��������");
			return INPUT;
		}else{
			stuInfo=new Student();
			stuInfo.setId(userId);
			stuInfo.setName(name);
			stuInfo.setSex(sex);
			stuInfo.setAge(Integer.parseInt(age));
			stuInfo.setGradFrom(gradFrom);
			stuInfo.setTel(Long.parseLong(tel));
			stuInfo.setAddr(addr);
			stuInfo.setIdCard(idCard);
			stuInfo.setEmail(email);
			 int addStudent = studentService.addStudent(stuInfo, pwd);
			 if(addStudent>0){
				 System.out.println("��ӳɹ�");
				 addActionMessage("ע��ɹ���");
				 return SUCCESS;
			 }else{
				 addActionError("ע��ʧ��");
				 System.out.println("���ʧ��");
				 return INPUT;
			 }
		}
		
		
	}
	
	public String passwordBack(){
		if(userId.equals("")||userId==null||tel.equals("")||tel==null||idCard.equals("")||idCard==null||email.equals("")||email==null){
			addActionError("�ύ��Ϣ����Ϊ�գ�");
			return INPUT;
		}else{
			Student stu = studentService.findStudentById(userId);
			if(stu.getTel()==Long.parseLong(tel)){
				if(stu.getIdCard().equals(idCard)){
					if(stu.getEmail().equals(email)){
						addActionMessage("�޸ĳɹ���");
						return SUCCESS;
					}else{
						addActionError("���䲻��ȷ��");
						System.out.println("���䲻��ȷ");
						return INPUT;
					}
				}else{
					addActionError("���֤����ȷ��");
					System.out.println("���֤����ȷ");
					return INPUT;
				}
			}else{
				addActionError("�ֻ��Ų���ȷ��");
				System.out.println("�ֻ��Ų���ȷ");
				return INPUT;
			}
		}
	}
	
	String apwd=null;
	
	public String getApwd() {
		return apwd;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

	public String changePwd(){
		 if(apwd!=null||!apwd.equals("")||pwd!=null&&!pwd.equals("")||!userId.equals("")||userId!=null){
			 if(apwd.equals(pwd)){
				 int changePwd = studentService.modifyStudentPWD(userId, pwd);
				 System.out.println("changePwd:"+changePwd);
				 if(changePwd>0){
					 addActionMessage("ע��ɹ���");
					 return SUCCESS;
				 }else{
					 addActionError("�޸�ʧ�ܣ�����������");
					 return INPUT;
				 }
			 }else{
				 addActionError("�������벻��ͬ");
				 return INPUT;
			 }
			
		 }else{
			 addActionError("�������ݲ���Ϊ��");
			 return INPUT;
		 }
	 
		
		
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Student getStuInfo() {
		return stuInfo;
	}

	public void setStuInfo(Student stuInfo) {
		this.stuInfo = stuInfo;
	}

	public String getRandNum() {
		return randNum;
	}

	public void setRandNum(String randNum) {
		this.randNum = randNum;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGradFrom() {
		return gradFrom;
	}

	public void setGradFrom(String gradFrom) {
		this.gradFrom = gradFrom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
