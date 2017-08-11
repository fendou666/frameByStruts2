package com.sims.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.StudentServiceImp;

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
		String action=request.getParameter("action");
		if(action.equals("findpwd")){
			String id=null;
			String tel=null;
			String idCard=null;
			String email=null;
			String pwd=null;
			
			id=request.getParameter("id");
			tel=request.getParameter("tel");
			idCard=request.getParameter("idCard");
			email=request.getParameter("email");
			
			
			if(id.equals("")||id==null||tel.equals("")||tel==null||idCard.equals("")||idCard==null||email.equals("")||email==null){
				request.getRequestDispatcher("PasswordBack.jsp").forward(request, response);
				return;
			}else{
				IStudentService is=new StudentServiceImp();
				Student stu = is.findStudentById(id);
				if(stu.getTel()==Long.parseLong(tel)){
					if(stu.getIdCard().equals(idCard)){
						if(stu.getEmail().equals(email)){
							request.setAttribute("findpwdMes", "验证成功！");
							System.out.println("验证成功！");
							request.setAttribute("id", id);
							request.getRequestDispatcher("changepwd.jsp").forward(request, response);
						}else{
							request.setAttribute("findpwdMes", "邮箱不正确");
							System.out.println("邮箱不正确");
							request.getRequestDispatcher("PasswordBack.jsp").forward(request, response);
						}
					}else{
						request.setAttribute("findpwdMes", "身份证不正确");
						System.out.println("身份证不正确");
						request.getRequestDispatcher("PasswordBack.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("findpwdMes", "手机号不正确");
					System.out.println("手机号不正确");
					request.getRequestDispatcher("PasswordBack.jsp").forward(request, response);
				}
				
				
			}
		}
		
	}

}
