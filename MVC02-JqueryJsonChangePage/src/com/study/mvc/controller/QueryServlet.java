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
		//ҵ�����
		String action=request.getParameter("action");
		PrintWriter out = response.getWriter();
		String runMessage="��ѯ�ɹ���";
		
		if(action==null){
			runMessage="��������ָ���ȷ���뷵����һҳ���´�����";
		}
		
		if(request.getSession().getAttribute("userInfo")==null){
			runMessage="����û�е�¼�����¼���ٽ��б�������";
		}
		//ѧԱ
		if(action.equals("getStuByID")){
			//��������ȡ��
			String id=request.getParameter("id");
			IStudentService studentService=new StudentServiceImp();
			StudentInfo stu = studentService.getStudentById(Integer.parseInt(id));
			//stu=null;
			if(stu!=null){
				request.setAttribute("stu", stu);
				request.getRequestDispatcher("ModifyStuInfo.jsp").forward(request, response);
			}
			else{
				runMessage="��ѧԱ��Ϣ�����ڣ���";
				//��ѧԱ��Ϣһ�����ݺ���
				/*IStudentService setudentService=new StudentServiceImp();
				List<StudentInfo> studentAll = setudentService.getStudentAll();
				request.setAttribute("stus", studentAll);*/
				request.setAttribute("msg", runMessage);
				request.getRequestDispatcher("UserList.jsp").forward(request, response);
			}
		}
		//��ʦ
		//��ʦ ������ �೤ �鳤��������������
	}

}
