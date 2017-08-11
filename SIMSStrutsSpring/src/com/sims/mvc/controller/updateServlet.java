package com.sims.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.StudentServiceImp;

/**
 * Servlet implementation class queryServlet
 */
@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action=request.getParameter("action");
		 String id=null;	//学号
		 String pwd=null;	//密码
		 String name=null;	//姓名
		 String sex=null;	//性别
		 String age= null;	//年龄
		 String gradFrom=null;	//毕业院校
		 String tel= null;	//电话号码
		 String addr=null;	//地址
		 String idCard =null;	//身份证号码
		 String email =null;	//邮箱
		 Student stu=null;
		 
		 
		 
		 //注册学员
		 if(action.equals("insert_stu")){
			 id=request.getParameter("id");
			 pwd=request.getParameter("pwd");
			 name=request.getParameter("name");
			 sex=request.getParameter("sex");
			 age=request.getParameter("age");
			 gradFrom=request.getParameter("gradFrom");
			 tel=request.getParameter("tel");
			 addr=request.getParameter("addr");
			 idCard=request.getParameter("idCard");
			 email=request.getParameter("email");
			 System.out.println(tel);
			 System.out.println("tel"+tel.length());
			 
			 if(id==null||id.equals("")||pwd==null||pwd.equals("")||name==null||name.equals("")||sex==null||sex.equals("")
					 ||age==null||age.equals("")||gradFrom.equals("")||gradFrom==null||tel.equals("")||tel==null||
					 addr.equals("")||addr==null||idCard.equals("")||idCard==null||email.equals("")||email==null){
				 request.setAttribute("registErr", "填写内容不能为空！");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
					return;
			 }else if(tel.length()>=14){
				 request.setAttribute("registErr", "电话号码超过了14位请重新输入！");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
					return;
			 }else{
				 System.out.println(1);
				 stu=new Student();
				 stu.setId(id);
				 stu.setName(name);
				 stu.setSex(sex);
				 stu.setAge(Integer.parseInt(age));
				 stu.setGradFrom(gradFrom);
				 stu.setTel(Long.parseLong(tel));
				 stu.setAddr(addr);
				 stu.setIdCard(idCard);
				 stu.setEmail(email);
				 
				 IStudentService is=new StudentServiceImp();
				 int addStudent = is.addStudent(stu, pwd);
				 if(addStudent>0){
					 System.out.println("添加成功");
//					 System.out.println("add"+addStudent);
//					 request.setAttribute("registMsg", "恭喜你注册成功！");
					 request.getRequestDispatcher("Login.jsp").forward(request, response);
				 }else{
					 System.out.println("添加失败");
				 }
			 }
		 }
		 
		 if(action.equals("changepwd")){
			 id=request.getParameter("id");
			 pwd=request.getParameter("pwd");
			 String apwd=request.getParameter("apwd");
			 if(apwd!=null&&!apwd.equals("")&&pwd!=null&&!pwd.equals("")){
				 if(apwd.equals(pwd)){
					 IStudentService is=new StudentServiceImp();
					 int changePwd = is.modifyStudentPWD(id, pwd);
					 if(changePwd>0){
						request.setAttribute("chpwd", "修改成功");
						request.getRequestDispatcher("Login.jsp").forward(request, response);
					 }else{
						 request.setAttribute("chpwd", "修改失败，请重新输入");
							request.getRequestDispatcher("changepwd.jsp").forward(request, response);
					 }
				 }else{
					 request.setAttribute("chpwd", "所输密码不相同");
						request.getRequestDispatcher("changepwd.jsp").forward(request, response);
				 }
				
			 }else{
				 request.setAttribute("chpwd", "密码不能为空");
					request.getRequestDispatcher("changepwd.jsp").forward(request, response);
			 }
		 }
	}

}
