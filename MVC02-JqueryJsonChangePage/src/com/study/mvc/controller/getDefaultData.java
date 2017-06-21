package com.study.mvc.controller;

import java.io.IOException;
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
 * Servlet implementation class getDefaultData
 */
@WebServlet("/getDefaultData")
public class getDefaultData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDefaultData() {
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
		//��ѧԱ��Ϣһ�����ݺ���
		/*IStudentService setudentService=new StudentServiceImp();
		List<StudentInfo> studentAll = setudentService.getStudentAll();
		//studentAll=null;
		request.setAttribute("stus", studentAll);*/
		request.getRequestDispatcher("UserList.jsp").forward(request, response);
	}

}
