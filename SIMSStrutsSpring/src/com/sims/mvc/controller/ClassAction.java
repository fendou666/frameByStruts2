package com.sims.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.bean.Team;
import com.sims.mvc.model.service.ClassServiceImp;
import com.sims.mvc.model.service.IClassService;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.ITeamService;
import com.sims.mvc.model.service.StudentServiceImp;
import com.sims.mvc.model.service.TeamServiceImp;
import com.sims.mvc.tools.JsonMesg;


public class ClassAction extends ActionSupport implements SessionAware,
ServletRequestAware {
    
	private static final long serialVersionUID = 3218221768282980955L;
	
	HttpServletRequest request=null;
	private static Map<String, Object> session = null;
	private Student stu = null;
	private String classId=null;
	private String id=null;
	private String name=null;
	private String leaderId=null;
	private String page;
	
	private String teamId;
	private String teamName;
	private String result = null;
	IStudentService studentService;
	ITeamService teamService;
	IClassService  classService;
	
    public ClassAction() {
    	
	}
  
	public void init(){
    	session = ServletActionContext.getContext().getSession();
		stu = (Student)session.get("stuInfo");
		System.out.println("stu===="+stu.toString());
		classId = stu.getClassID();// 获取班id
    }
    
//    获取Session
    public static Student getSession() {
		Student stu = (Student) session.get("stuInfo");
		return stu;
	}
    
	//	班级成员信息查询
	public String checkClassStudent(){
		init();
		List<Student> list = studentService.findStudentByClassID(classId);
//		System.out.println("list:"+list);
//		System.out.println("checkClassStudent--班级成员查询---classId:"+classId);
		result=JsonMesg.getJsonArray(list);
//		System.out.println("checkClassStudent--班级成员查询-result:"+result);
		return SUCCESS;
	}
	
//	班级信息展示
    public String showClass(){
    	init();
		CClass classInfo = classService.findClassById(classId);
//		System.out.println("showClass--班级信息结构---classInfo:"+classInfo);
		result=JsonMesg.getJsonArray(classInfo);
//		System.out.println("showClass--班级信息结构---result:"+result);
		return SUCCESS;
    }
	
    
//    创建组
    public String createTeam(){
    	init();
    	JsonMesg mesg = null;
//    	System.out.println("teamName-------------" + teamName);
//    	System.out.println("leaderId-------------" + leaderId);
    	
    	if(teamId==null||teamId.equals("")||teamName==null
    	   ||teamName.equals("")||leaderId==null||leaderId.equals("")){
    		System.out.println("teamId"+teamId+"teamName"+teamName+"leaderId"+leaderId);
    		  mesg = new JsonMesg("1");
    		  result = JsonMesg.getJsonObject(mesg);
    		  System.out.println("111111111111111---------------");
    		  return "success";
    	}else{
    	      Team team=new Team(teamId, teamName, leaderId,classId);
    	     
    	      teamService.addTeam(team);
//    	      System.out.println("team:ID"+team.getId()+"team:name"+team.getName()+"team:getLeaderID"+team.getLeaderID()+"team:getClassID"+team.getClassID());
//    	      System.out.println("222222222222---------------");
              Student student = studentService.findStudentById(leaderId);
              System.out.println("----teamId:"+teamId);
              student.setT_leader(1);  //设置组长权限
              student.setTeamID(teamId);  //设置组id
              
              int res = studentService.modifyStudent(student);
              System.out.println("222222222222---------------:"+res);
              if(res > 0){
              	System.out.println(leaderId + "组长权限授权");
              }
              mesg=new JsonMesg("2");
              result=JsonMesg.getJsonObject(mesg); 
    	}
		return SUCCESS;	
    }
    
    
//    组成员信息展示
    public String showTeamStudent(){
    	init();
    	JsonMesg mesg = null;
    	System.out.println("teamId:"+teamId);
    	if(teamId==null||teamId.equals("")){
			mesg = new JsonMesg("1");
			result = JsonMesg.getJsonObject(mesg);
		    return "success";
    	}else{	
    		List<Student> list = studentService.findStudentByTeamID(teamId, classId);
    		mesg = new JsonMesg("2");
//    		System.out.println("teamId:"+teamId);
//    		System.out.println("showTeamStudent--组成员---list:"+list);
    		result=JsonMesg.getJsonArray(list);
//    		System.out.println("showTeamStudent--组成员---result:"+result);
    	}
		return SUCCESS;
    }
    
    
//    所有组信息展示 通过班长的classID
    public String showTeam(){
    	init();
//    	System.out.println("showTeam==classId"+classId);
    	List<Team> list = teamService.findTeamsByClassID(classId);
//    	System.out.println("showTeam--组结构--list"+list);
    	result=JsonMesg.getJsonArray(list);
//    	System.out.println("showTeam--组结构--list"+result);
    	return SUCCESS;
    }
    
    
//    任命组长
    public String appoinTeamLeader(){
    	init();
//    	System.out.println("--appoinTeamLeader--teamId:"+teamId+"----leaderId:"+leaderId);
    	JsonMesg mesg = null;
    	if(teamId==null||teamId.equals("")||leaderId==null||leaderId.equals("")){
    		mesg = new JsonMesg("1");
			result = JsonMesg.getJsonObject(mesg);
		    return "success";	
    	}else{
    		Team team = teamService.findTeamById(teamId);
    		List<Student> list =studentService.findStudentByTeamID(teamId, classId);
//    		System.out.println("----team:"+team+"----teamId:"+teamId+"----leaderId:"+leaderId);
    		
    		for (Student s : list) {
    			 if(s.getT_leader()==1){
					 s.setT_leader(0);
					 studentService.modifyStudent(s);
				   }
    			 
//    			 System.out.println("list"+list);
    			 if(s.getId().equals(leaderId)&&s.getT_leader()!=1){
    				 s.setT_leader(1);
					 studentService.modifyStudent(s);
					 team.setLeaderID(leaderId);
					 teamService.modifyTeam(team);
//					 System.out.println("333"+team.getLeaderID());
					 mesg = new JsonMesg("2");
					 result = JsonMesg.getJsonObject(mesg);
				   }
    			 
			}
    	}
		return SUCCESS;
    }
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
    
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
	    this.request=request;		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}

    
	
   public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}


	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}
}
