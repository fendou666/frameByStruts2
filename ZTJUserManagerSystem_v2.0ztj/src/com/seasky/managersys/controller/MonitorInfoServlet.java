package com.seasky.managersys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.service.IMonitorService;
import com.seasky.managersys.model.service.MonitorServiceImp;
import com.seasky.managersys.tools.PageManagerMonitor;

/**
 * Servlet implementation class MonitorInfoServlet
 */
@WebServlet("/MonitorInfoServlet")
public class MonitorInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorInfoServlet() {  }

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		IMonitorService ims = new MonitorServiceImp();
//		本班级的所有成员
		if(action.equals("classInf")){
			int classId = alterClassId(request);	//班级编号处理
			choosePage(request);					//页码处理
			List<UserInfo> list = ims.getUserInfos(classId);
			out.write("[");
			if(list!=null){
				if(list.size()>0){
					int i=0;
					for(UserInfo x: list){
						i++;
						out.write("{");
						out.write("\"id\":\""+x.getId()+"\",");
						out.write("\"name\":\""+x.getName()+"\",");
						out.write("\"sex\":\""+x.getSex()+"\",");
						out.write("\"age\":\""+x.getAge()+"\",");
						out.write("\"phone\":\""+x.getPhone()+"\",");
						out.write("\"address\":\""+x.getAddress()+"\",");
						out.write("\"mail\":\""+x.getMail()+"\",");
						out.write("\"status\":\""+x.getDelete()+"\"");
						if(i==list.size()){
							out.write("}");
						}
						else{
							out.write("},");
						}
					}
				}
				else{
					out.write("{");
					out.write("\"id\":\"\",");
					out.write("\"name\":\"\",");
					out.write("\"sex\":\"\",");
					out.write("\"age\":\"\",");
					out.write("\"phone\":\"\",");
					out.write("\"address\":\"\",");
					out.write("\"mail\":\"\",");
					out.write("\"status\":\"\"");
					out.write("}");
				}
			}
			else{
				out.write("{");
				out.write("\"id\":\"\",");
				out.write("\"name\":\"\",");
				out.write("\"sex\":\"\",");
				out.write("\"age\":\"\",");
				out.write("\"phone\":\"\",");
				out.write("\"address\":\"\",");
				out.write("\"mail\":\"\",");
				out.write("\"status\":\"\"");
				out.write("}");
			}
			out.write("]");
		}
		if(action.equals("divGroup")||action.equals("pointLeader")){
			int power = 0;
			int classId = alterClassId(request);	//班级编号处理
			choosePage(request);					//页码处理
			if(action.equals("divGroup")){
				power = 2;
				
			}
			else{
				power = 3;
			}
			List<UserInfo> listStu = ims.getStudents(classId, power);
			out.write("[");
			if(listStu!=null){
				if(listStu.size()>0){
					int i=0;
					for(UserInfo x: listStu){
						i++;
						out.write("{");
						out.write("\"id\":\""+x.getId()+"\",");
						out.write("\"name\":\""+x.getName()+"\",");
						out.write("\"groupId\":\""+x.getGroupId()+"\",");
						out.write("\"power\":\""+x.getPower()+"\",");
						out.write("\"classId\":\""+x.getClassId()+"\"");
						if(i==listStu.size()){
							out.write("}");
						}
						else{
							out.write("},");
						}
					}
				}
				else{
					out.write("{");
					out.write("\"id\":\"\",");
					out.write("\"name\":\"\",");
					out.write("\"groupId\":\"\",");
					out.write("\"power\":\"\",");
					out.write("\"classId\":\"\"");
					out.write("}");
				}
			}
			else{
				out.write("{");
				out.write("\"id\":\"\",");
				out.write("\"name\":\"\",");
				out.write("\"groupId\":\"\",");
				out.write("\"power\":\"\",");
				out.write("\"classId\":\"\"");
				out.write("}");
			}
			out.write("]");
		}
		if(action.equals("pointLeader")){
			
		}
	}
//	修改页码
	private static void choosePage(HttpServletRequest request){
		String page=request.getParameter("page");
		if (page.equals("first")) {
			PageManagerMonitor.currentPageNo=1;
		}else if (page.equals("pre")) {
			PageManagerMonitor.currentPageNo=PageManagerMonitor.getPrePageNo();
		}else if (page.equals("nxt")) {
			PageManagerMonitor.currentPageNo=PageManagerMonitor.getNextPageNo();
		}else if (page.equals("last")) {
			PageManagerMonitor.currentPageNo=PageManagerMonitor.getMaxPageNo();
		}else{
			if (PageManagerMonitor.getMaxPageNo()<=Integer.parseInt(page)) {
				PageManagerMonitor.currentPageNo=PageManagerMonitor.getMaxPageNo();
			}else if(Integer.parseInt(page)<=1){
				PageManagerMonitor.currentPageNo=1;
			}else{
				PageManagerMonitor.currentPageNo=Integer.parseInt(page);
			}
		}
	}
	
//	处理班级编号
	private static int alterClassId(HttpServletRequest request){
		String classI = request.getParameter("classId");
		int classId = 0;
		if(classI==null){
			classId=0;
		}else{
			classId=Integer.parseInt(classI);
		}
		return classId;
	}
}
