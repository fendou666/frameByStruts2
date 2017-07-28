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
		//ҵ�����
		String action=request.getParameter("action");
		PrintWriter out = response.getWriter();
		
		if(action==null){
			out.write("��������ָ���ȷ���뷵����һҳ���´�����");
		}
		
		if(request.getSession().getAttribute("userInfo")==null){
			out.write("����û�е�¼�����¼���ٽ��б�������");
		}
		//ѧԱ
		if(action.equals("stu_upd")){
			//��������ȡ��
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String sex=request.getParameter("sex");
			sex=sex.equals("0")?"��":"Ů";
			String age=request.getParameter("age");
			String gradeFrom=request.getParameter("gradeFrom");
			
			StudentInfo stu=new StudentInfo(Integer.parseInt(id),name,sex,Integer.parseInt(age),gradeFrom);
			
			IStudentService studentService=new StudentServiceImp();
			
			studentService.save(stu);

			//��ѧԱ��Ϣһ�����ݺ���
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentAll();
			request.setAttribute("stus", studentAll);
			request.getRequestDispatcher("UserList.jsp").forward(request, response);
		}

		if(action.equals("stu_del")){
			//��������ȡ��
			String id=request.getParameter("id");
			IStudentService studentService=new StudentServiceImp();
			
			studentService.delete(Integer.parseInt(id));

			//��ѧԱ��Ϣһ�����ݺ���
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentAll();
			request.setAttribute("stus", studentAll);
			request.getRequestDispatcher("UserList.jsp").forward(request, response);
		}
		//��ʦ
		//��ʦ ������ �೤ �鳤��������������
	}

}
