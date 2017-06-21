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
		//0 ������
		String userid=null; //�û����
		String pwd=null;      //�û�����
		UserInfo userInfo =null;
		
		//1 ���ý���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//2 ���ջ�����������鱨
		userid=request.getParameter("userid");
		pwd=request.getParameter("pwd");
				
		PrintWriter out = response.getWriter();
		
		String data="";
		//3.1 ������֤---�Ϸ�����֤  Ajax
		if(userid==null||pwd==null||userid.equals("")||pwd.equals("")){
			data= "�û���Ż����벻��Ϊ�գ���";
			out.write(data);
			return;
		}
		
		//2 ��ȷ����֤  Service����û�����ȷ��������֤
		ILoginService loginService=new LoginServiceImp();
		if(userid.matches("[0-9]+")){
			userInfo = loginService.checkUserInfo(Integer.parseInt(userid), pwd);
		}
		else{
			data= "�û���Ż�������󣡣�";
			out.write(data);
			return;
		}
		
		//����
		if(userInfo==null){
			//�û��鱨��֤δͨ��
			data= "�û���Ż�������󣡣�";
			out.write(data);
			return;
		}
		else{
			data= "�ɹ���¼";
			out.write(data);//�û��鱨��֤ͨ��
			request.getSession().setAttribute("userInfo", userInfo);
			return;
			/*
			//��ѧԱ��Ϣһ�����ݺ���
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentAll();
			//studentAll=null;
			request.setAttribute("stus", studentAll);
			request.getRequestDispatcher("UserList.jsp").forward(request, response);*/
		}
	}
}
