package com.seasky.managersys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.service.IMonitorService;
import com.seasky.managersys.model.service.MonitorServiceImp;

/**
 * Servlet implementation class MonitorServlet
 */
@WebServlet("/MonitorServlet")
public class MonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorServlet() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取画面请求数据 注意乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		if(request.getSession().getAttribute("user")==null){
			response.sendRedirect("index.jsp");
			return;
		}
		
		String action = request.getParameter("action");
//		获取班级并对其进行处理
		String classId = request.getParameter("classId");
		int classI = parseId(classId);
		System.out.println("用户所在班级:" + classI);
		
//		获取班主任以及任课老师的id
		String headmasterI = request.getParameter("headmasterId");
		String teacherI = request.getParameter("teacherId");
		int headmasterId = parseId(headmasterI);
		int teacherId = parseId(teacherI);
		
		IMonitorService ims = new MonitorServiceImp();
		UserInfo userTeacher = ims.getTeacherName(headmasterId);
		UserInfo headmaster = ims.getTeacherName(teacherId);
		request.setAttribute("userTeacher", userTeacher);
		request.setAttribute("headmaster", headmaster);
		System.out.println("班主任："+headmaster.getName());
		System.out.println("任课老师："+userTeacher.getName());
		if(action.equals("classInf")){
			List<UserInfo> list = ims.getUserInfos(classI);
			request.setAttribute("list", list);
			request.getRequestDispatcher("Mon_classUserInfo.jsp").forward(request, response);
		}else if(action.equals("classDiv")){
//			指定组长
			int power = 3;
			List<UserInfo> listStuEM = ims.getStudents(classI, power);
			request.setAttribute("listStuEM", listStuEM);
			request.getRequestDispatcher("Mon_pointLeader.jsp").forward(request, response);
		}else if(action.equals("classDivPeo")){
//			分组
			int power = 2;
			List<UserInfo> listStu = ims.getStudents(classI, power);
			request.setAttribute("listStu", listStu);
			List<UserInfo> listLeader = ims.getLeaderInfos(classI, power);
			request.setAttribute("listLeader", listLeader);
			System.out.println("classDivPeo");
			request.getRequestDispatcher("Mon_divGroup.jsp").forward(request, response);
		}
	}

//	对所读取的字符串id进行转换，转换为整型
	private static int parseId(String strId){
		int id = 0;
		if(strId==null||strId.equals("")){
			id = 0;
		}
		else{
			id = Integer.parseInt(strId);
		}
		return id;
	}
}
