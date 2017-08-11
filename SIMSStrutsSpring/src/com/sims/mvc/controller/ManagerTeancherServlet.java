package com.sims.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.ClassServiceImp;
import com.sims.mvc.model.service.IClassService;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.StudentServiceImp;

/**
 * Servlet implementation class ManagerTeancherServlet
 */
@WebServlet("/ManagerTeancherServlet")
public class ManagerTeancherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerTeancherServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//获取action的值
		String action=request.getParameter("action");
		//获取班id
		Student stu=(Student)request.getSession().getAttribute("stuInfo");
		String classId=stu.getClassID();
		//查询所有班级学员信息
		IStudentService stservice=new StudentServiceImp();
		IClassService clas=new ClassServiceImp();
		
		if(action.equals("quealclassstudent")){
			List<Student> list=stservice.findStudentByClassID(classId);
			request.setAttribute("study", list);
			request.getRequestDispatcher("/content/classManger/ManagerTacher.jsp").forward(request, response);
		}
		
		//查询所有未分班学员信息
		if(action.equals("quealnotclassstudent")){
			List<Student> list=stservice.findStudentByClassID("");
			request.setAttribute("study", list);
			System.out.println("123343=="+ list);
			request.getRequestDispatcher("/content/classManger/NotManagerTacher.jsp").forward(request, response);
		}
		
		//任命罢免班长
		if(action.equals("classmanagee")){
			String classid=request.getParameter("classid");
			String studyid=request.getParameter("studyid");
			CClass ClassById = clas.findClassById(classid);
			List<Student> list=stservice.findStudentByClassID(classid);
			for(Student s:list){
				if(s.getC_monitor()==1){
					s.setC_monitor(0);
					stservice.modifyStudent(s);
				}
				if(studyid.equals(s.getId())&&s.getC_monitor()!=1){
						s.setC_monitor(1);
						stservice.modifyStudent(s);
						ClassById.setId(classid);
						ClassById.setMonitorID(studyid);
						
						clas.modifyClass(ClassById);
						request.setAttribute("succ", "任命成功");
						request.getRequestDispatcher("/content/classManger/ManagerTacher.jsp").forward(request, response);
				}	
			}
		}
		
		//添加班级新成员
		if(action.equals("insertstudents")){
			String studyid=request.getParameter("studyid");	
			Student s=stservice.findStudentById(studyid);
				s.setClassID(classId);
				stservice.modifyStudent(s);
				request.getRequestDispatcher("/content/classManger/ManagerTacher.jsp").forward(request, response);
		}
		
		//开除班级学生
		if(action.equals("deletestudent")){
			String studyid=request.getParameter("studyid");
			Student s=stservice.findStudentById(studyid);
			s.setClassID("");
			stservice.modifyStudent(s);
			request.getRequestDispatcher("/content/classManger/ManagerTacher.jsp").forward(request, response);
		}
	}

}


