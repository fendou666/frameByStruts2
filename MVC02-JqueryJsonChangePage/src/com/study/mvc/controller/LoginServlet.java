package com.study.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.model.bean.UserInfo;
import com.study.mvc.model.service.ILoginService;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.LoginServiceImp;
import com.study.mvc.model.service.StudentServiceImp;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//0 定义区
		String userid=null; //用户编号
		String pwd=null;      //用户密码
		UserInfo userInfo =null;
		
		//1 设置解码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//2 接收画面给的请求情报
		userid=request.getParameter("userid");
		pwd=request.getParameter("pwd");
				
		PrintWriter out = response.getWriter();
		
		String data="";
		//3.1 数据验证---合法性验证  Ajax
		if(userid==null||pwd==null||userid.equals("")||pwd.equals("")){
			data= "用户编号或密码不能为空！！";
			out.write(data);
			return;
		}
		
		//2 正确性验证  Service完成用户的正确性数据验证
		ILoginService loginService=new LoginServiceImp();
		if(userid.matches("[0-9]+")){
			userInfo = loginService.checkUserInfo(Integer.parseInt(userid), pwd);
		}
		else{
			data= "用户编号或密码错误！！";
			out.write(data);
			return;
		}
		
		//控制
		if(userInfo==null){
			//用户情报认证未通过
			data= "用户编号或密码错误！！";
			out.write(data);
			return;
		}
		else{
			data= "成功登录";
			out.write(data);//用户情报认证通过
			request.getSession().setAttribute("userInfo", userInfo);
			return;
			/*
			//将学员信息一览数据呼出
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentAll();
			//studentAll=null;
			request.setAttribute("stus", studentAll);
			request.getRequestDispatcher("UserList.jsp").forward(request, response);*/
		}
	}
}
