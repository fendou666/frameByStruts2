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
import com.study.mvc.model.dao.StudentDAOImp;
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		//业务分类
		String action=request.getParameter("action");
		PrintWriter out = response.getWriter();
		
		if(action==null){
			out.write("您的请求指令不正确！请返回上一页重新处理！！");
		}
		
		if(request.getSession().getAttribute("userInfo")==null){
			out.write("您还没有登录，请登录后再进行本操作！");
		}
		//学员
		if(action.equals("stu_upd")){
			//请求数据取得
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String sex=request.getParameter("sex");
			sex=sex.equals("0")?"男":"女";
			String age=request.getParameter("age");
			String gradeFrom=request.getParameter("gradeFrom");
			
			StudentInfo stu=new StudentInfo(Integer.parseInt(id),name,sex,Integer.parseInt(age),gradeFrom);
			
			IStudentService studentService=new StudentServiceImp();
			
			studentService.save(stu);

			//将学员信息一览数据呼出
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentAll();
			request.setAttribute("stus", studentAll);
			request.getRequestDispatcher("UserList.jsp").forward(request, response);
		}

		if(action.equals("stu_del")){
			//请求数据取得
			String id=request.getParameter("id");
			IStudentService studentService=new StudentServiceImp();
			
			studentService.delete(Integer.parseInt(id));

			//将学员信息一览数据呼出
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentAll();
			request.setAttribute("stus", studentAll);
			request.getRequestDispatcher("UserList.jsp").forward(request, response);
		}
		//老师
		//讲师 班主任 班长 组长。。。。。。。
	}

}
