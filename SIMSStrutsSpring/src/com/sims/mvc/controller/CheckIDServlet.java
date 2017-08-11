package com.sims.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sims.mvc.model.service.ILoginService;
import com.sims.mvc.model.service.LoginServiceImp;

/**
 * Servlet implementation class CheckUserServlet
 */
@WebServlet("/CheckIDServlet")
public class CheckIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIDServlet() {
        super();
    }

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
		
		String userId=request.getParameter("userId");
		userId=new String(userId.getBytes("ISO-8859-1"),"UTF-8" ); 
		PrintWriter out=response.getWriter();
		
		if(userId==null||userId.equals("")){
			out.write("0");
		}else{
			ILoginService lLoginService=new LoginServiceImp();
			if(lLoginService.checkID(userId)==1){
				out.write("1");
			}else{
				out.write("2");
			}
		}
	}

}
