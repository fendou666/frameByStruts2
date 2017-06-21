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
import com.study.mvc.model.service.IStudentService;
import com.study.mvc.model.service.StudentServiceImp;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String runMessage="查询成功！";
		
		if(action==null){
			runMessage="您的请求指令不正确！请返回上一页重新处理！！";
		}
		
		if(request.getSession().getAttribute("userInfo")==null){
			runMessage="您还没有登录，请登录后再进行本操作！";
		}
		//学员
		if(action.equals("getStuByID")){
			//请求数据取得
			String id=request.getParameter("id");
			IStudentService studentService=new StudentServiceImp();
			StudentInfo stu = studentService.getStudentById(Integer.parseInt(id));
			//stu=null;
			if(stu!=null){
				request.setAttribute("stu", stu);
				request.getRequestDispatcher("ModifyStuInfo.jsp").forward(request, response);
			}
			else{
				runMessage="该学员信息不存在！！";
				//将学员信息一览数据呼出
				/*IStudentService setudentService=new StudentServiceImp();
				List<StudentInfo> studentAll = setudentService.getStudentAll();
				request.setAttribute("stus", studentAll);*/
				request.setAttribute("msg", runMessage);
				request.getRequestDispatcher("UserList.jsp").forward(request, response);
			}
		}
		//老师
		//讲师 班主任 班长 组长。。。。。。。
	}

}
