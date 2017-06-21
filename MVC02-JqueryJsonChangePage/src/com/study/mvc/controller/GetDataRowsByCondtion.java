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
import com.study.mvc.tools.PageManager;

/**
 * Servlet implementation class GetDataRowsByCondtion
 */
@WebServlet("/GetDataRowsByCondtion")
public class GetDataRowsByCondtion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDataRowsByCondtion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========doGet============");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========doPost============");
		//1 设置解码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//请求数据获得
		String findCondition="";
		
		//2 被Ajax请求的流返回  Json
		PrintWriter out = response.getWriter();
		
		findCondition=request.getParameter("stuname");
		String page=request.getParameter("page");
		
		if(findCondition!=null){
			//------------确定页码需求
			if(page.equals("first")){
				PageManager.currentPageNo=1;
			}
			else if(page.equals("pre")){
				PageManager.currentPageNo=PageManager.getPrePageNo();
			}
			else if(page.equals("nxt")){
				PageManager.currentPageNo=PageManager.getNextPageNo();
			}
			else if(page.equals("last")){
				PageManager.currentPageNo=PageManager.getMaxPageNo();
			}
			else{
				PageManager.getMaxPageNo();
				PageManager.currentPageNo=Integer.parseInt(page);		
			}
			//-----------------------
			IStudentService setudentService=new StudentServiceImp();
			List<StudentInfo> studentAll = setudentService.getStudentsByName(findCondition);			
			out.write("[");
			//最大页码交给画面
			System.out.println("PageManager.getMaxPageNo:"+PageManager.getMaxPageNo());
			request.getSession().setAttribute("maxp", PageManager.getMaxPageNo());
			System.out.println("PageManager.getMaxPageNo:"+PageManager.getMaxPageNo());
			if(studentAll!=null){
				if(studentAll.size()>0){
					int i=0;
					for(StudentInfo x: studentAll){
						i++;
						out.write("{");
						out.write("\"id\":\""+x.getId()+"\",");
						out.write("\"name\":\""+x.getName()+"\",");
						out.write("\"sex\":\""+x.getSex()+"\",");
						out.write("\"age\":\""+x.getAge()+"\",");
						out.write("\"gradeFrom\":\""+x.getGradeFrom()+"\"");
						if(i==studentAll.size()){
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
					out.write("\"gradeFrom\":\"\"");
					out.write("}");
				}
			}
			else{
				out.write("{");
				out.write("\"id\":\"\",");
				out.write("\"name\":\"\",");
				out.write("\"sex\":\"\",");
				out.write("\"age\":\"\",");
				out.write("\"gradeFrom\":\"\"");
				out.write("}");
			}
			out.write("]");
		}
		else{
			out.write("[");
			out.write("{");
			out.write("\"id\":\"\",");
			out.write("\"name\":\"\",");
			out.write("\"sex\":\"\",");
			out.write("\"age\":\"\",");
			out.write("\"gradeFrom\":\"\"");
			out.write("}");
			out.write("]");
		}
	}

}
