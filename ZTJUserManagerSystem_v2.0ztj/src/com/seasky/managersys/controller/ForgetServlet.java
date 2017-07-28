package com.seasky.managersys.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.service.ForgetServiceImp;
import com.seasky.managersys.model.service.IForgetService;

/**
 * Servlet implementation class LogonServlet
 */
@WebServlet("/ForgetServlet")
public class ForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetServlet() {
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
		//1.获取画面请求数据 注意乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		String pwdgain = request.getParameter("pwdgain");
		String ident = request.getParameter("ident");
		String img = (String)request.getSession().getAttribute("rand");
		System.out.println("----------------------------------------");
		System.out.println("forget服务器从忘记密码页面拿到的姓名 ："+name);
		System.out.println("forget服务器从忘记密码页面拿到的学号："+userId);
		System.out.println("forget服务器从忘记密码页面拿到的手机号："+phone);
		System.out.println("forget服务器从忘记密码页面拿到的新密码："+pwd);
		System.out.println("forget服务器从忘记密码页面拿到的确认密码："+pwdgain);
		System.out.println("forget服务器从忘记密码页面拿到的验证码："+ident);
		System.out.println("----------------------------------------");
		UserInfo user=null;
		IForgetService forgetService=new ForgetServiceImp();
		
		request.removeAttribute("err");
		if (ident==null&&("").equals(ident)) {
			request.setAttribute("err", "验证码不能为空！");
			request.getRequestDispatcher("forget.jsp").forward(request, response);
		}
		else if(ident.equals(img)){
			if (name!=null&&!("").equals(name)) {
				if (userId!=null&&!("").equals(userId)) {
					if (phone!=null&&!("").equals(phone)&&phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[06-8])\\d{8}$")) {
						if (pwd!=null&&!("").equals(pwd)&&pwdgain!=null&&!("").equals(pwdgain)&&pwd.length()>5&&pwd.length()<13) {
							if (pwd.equals(pwdgain)){
									int count=forgetService.checkForget(name,Integer.parseInt(userId),pwd,phone);
									System.out.println("servlet接收到的count："+count);
									if (count!=0) {
										if(count==-1) {
											request.setAttribute("err", "新密码不可与旧密码一致！");
											request.getRequestDispatcher("forget.jsp").forward(request, response);
										}else{
											request.getSession().setAttribute("text", "密码修改成功！");
											request.getRequestDispatcher("index.jsp").forward(request, response);
										}
									}
									else {
										request.setAttribute("err", "身份验证不匹配，请重新修改！");
										request.getRequestDispatcher("forget.jsp").forward(request, response);
									}
							} else {
								request.setAttribute("err", "密码和确认密码必须一致！");
								request.getRequestDispatcher("forget.jsp").forward(request, response);
							}
						} else {
							request.setAttribute("err", "密码和确认密码必须为6-12位！");
							request.getRequestDispatcher("forget.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("err", "手机号格式有误！");
						request.getRequestDispatcher("forget.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("err", "学号不能为空！");
					request.getRequestDispatcher("forget.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("err", "用户名不能为空！");
				request.getRequestDispatcher("forget.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("err", "验证码有误！");
			request.getRequestDispatcher("forget.jsp").forward(request, response);
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
