package com.chinasofti.eecuser.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.IStudentService;
import com.chinasofti.eecuser.model.service.StudentServiceImp;

/**
 * Servlet implementation class StudentUpdateServlet
 */
@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("update=============");
		PrintWriter out = response.getWriter();
		String oper= request.getParameter("oper");
		String eecId = (String)request.getSession().getAttribute("eecId");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		//¼Ù¶¨eecId
		eecId = "170207008";
		
		System.out.println("email:"+email);
		System.out.println("telephone:"+telephone);
		IStudentService iss = new StudentServiceImp();
		if(oper.equals("show")){
			UserInfo user = iss.queryUserById(Integer.parseInt(eecId));
		}
		if(oper.equals("updData")){
			System.out.println("updateUserById=====save======");
			iss.updateUserById(Long.parseLong(telephone), email, Integer.parseInt(eecId));
		}
		
		UserInfo user = iss.queryUserById(Integer.parseInt(eecId));
		request.getSession().setAttribute("user", user);
		System.out.println("UPDATE user:"+user);
		
		if(user!=null){
			out.write("[");
			out.write("{");
			out.write("\"id\":\""+user.getId()+"\",");
			out.write("\"name\":\""+user.getName()+"\",");
			out.write("\"sex\":\""+user.getSex()+"\",");
			out.write("\"birthday\":\""+user.getBirthday()+"\",");
			out.write("\"telephone\":\""+user.getTelephone()+"\",");
			out.write("\"email\":\""+user.getEmail()+"\",");
			out.write("\"classId\":\""+user.getClassId()+"\"");
			out.write("}");
			out.write("]");
		}
		else{
			out.write("[");
			System.out.print("[");
			out.write("{");
			out.write("\"id\":\"\",");
			out.write("\"name\":\"\",");
			out.write("\"sex\":\"\",");
			out.write("\"birthDate\":\"\",");
			out.write("\"telephone\":\"\",");
			out.write("\"email\":\"\",");
			out.write("\"classId\":\"");
			out.write("}");
			out.write("]");
		}
	
	}

}
