package com.chinasofti.eecuser.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/StudentQueryServlet")
public class StudentQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentQueryServlet() {
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
		System.out.println("StudentQueryServlet=========");
		List<UserInfo> user = null;
		PrintWriter out = response.getWriter();
		UserInfo userinfo = (UserInfo)request.getSession().getAttribute("userinfo");
		//int classId = userinfo.getClassId();
		//¼ÙÉèclassIdµÄÖµ
		int classId = 20170207;
		String action = request.getParameter("action");
		String eecId = request.getParameter("eecId");
		System.out.println("eecId:"+eecId);
		String eecName = request.getParameter("eecName");
		System.out.println("eecName:"+eecName);
		String roleName = request.getParameter("roleName");
		System.out.println("roleName:"+roleName);
		
		IStudentService iss = new StudentServiceImp();
		
		
		if(action.equals("conditions")){
			if(eecId==null||eecId.equals("")){
				eecId="-1";
			}
			user = iss.queryUserByConditions(Integer.parseInt(eecId),classId, eecName, roleName);
		}
		if(action.equals("classId")){
			user = iss.queryByClassId(classId);
		}
		request.getSession().setAttribute("user", user);
		if(user!=null){
			out.write("[");
			if(user.size()>0){
				int i=0;
				for(UserInfo us: user){
					i++;
					out.write("{");
					out.write("\"classId\":\""+us.getClassId()+"\",");
					out.write("\"id\":\""+us.getId()+"\",");
					out.write("\"name\":\""+us.getName()+"\",");
					out.write("\"sex\":\""+us.getSex()+"\",");
					out.write("\"birthday\":\""+us.getBirthday()+"\",");
					out.write("\"telephone\":\""+us.getTelephone()+"\",");
					out.write("\"email\":\""+us.getEmail()+"\",");
					out.write("\"roleName\":\""+us.getRoleName()+"\"");
					
					if(i==user.size()){
						out.write("}");
					}else{
						out.write("},");
					}
					
				}
			}
			out.write("]");
		}
		else{
			out.write("[");
			System.out.print("[");
			out.write("{");
			System.out.print("{");
			out.write("\"classId\":\"\",");
			System.out.print("\"classId\":\"\",");
			out.write("\"id\":\"\",");
			System.out.print("\"id\":\"\",");
			out.write("\"name\":\"\",");
			System.out.print("\"name\":\"\",");
			out.write("\"sex\":\"\",");
			System.out.print("\"sex\":\"\",");
			out.write("\"birthday\":\"\",");
			System.out.print("\"birthday\":\"\",");
			out.write("\"telephone\":\"\",");
			System.out.print("\"telephone\":\"\",");
			out.write("\"email\":\"\",");
			System.out.print("\"email\":\"\",");
			out.write("\"roleName\":\"\"");
			System.out.print("\"roleName\":\"\"");
			out.write("}");
			System.out.print("}");
			out.write("]");
			System.out.print("]");
		}
	}
		

}
