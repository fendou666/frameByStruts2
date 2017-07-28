package com.seasky.managersys.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seasky.managersys.model.service.IRegistService;
import com.seasky.managersys.model.service.RegistServiceImp;

/**
 * Servlet implementation class LogonServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		
		String userName = request.getParameter("user");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String pwd = request.getParameter("pwd");
		String pwdgain = request.getParameter("pwdgain");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String ident = request.getParameter("ident");
		String img = (String)request.getSession().getAttribute("rand");
		System.out.println("----------------------------------------");
		System.out.println("regist服务器从注册前台拿到的姓名："+userName);
		System.out.println("regist服务器从注册前台拿到的年龄："+age);
		System.out.println("regist服务器从注册前台拿到的性别："+sex);
		System.out.println("regist服务器从注册前台拿到的密码："+pwd);
		System.out.println("regist服务器从注册前台拿到的确认密码："+pwdgain);
		System.out.println("regist服务器从注册前台拿到的手机号："+phone);
		System.out.println("regist服务器从注册前台拿到的邮箱："+mail);
		System.out.println("regist服务器从注册前台拿到的验证码："+ident);
		System.out.println("regist服务器从验证服务器拿到的验证码："+img);
		System.out.println("----------------------------------------");
		IRegistService registService=new RegistServiceImp();
		
		request.removeAttribute("err");
		if (ident==null&&("").equals(ident)) {
			request.setAttribute("err", "验证码不能为空！");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
		else if(ident.equals(img)){
			if (phone!=null&&!("").equals(phone)&&phone.matches("[0-9]+")&&phone.length()==11) {
				if (phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[06-8])\\d{8}$")) {
					if (userName!=null&&!("").equals(userName)) {
						if (sex!=null&&!("").equals(sex)) {
							if (age!=null&&!("").equals(age)&&age.matches("[0-9]+")&&Integer.parseInt(age)<90) {
								if (mail!=null&&!("").equals(mail)) {
									if (pwd!=null&&!("").equals(pwd)&&pwdgain!=null&&!("").equals(pwdgain)&&pwd.length()>5&&pwd.length()<13) {
										if (pwd.equals(pwdgain)){
											int count=registService.checkRegist(userName, sex, Integer.parseInt(age), pwd, phone, mail);
											if(count>0){
												request.setAttribute("text", "注册已提交，等待管理员审核");
												request.getRequestDispatcher("regist.jsp").forward(request, response);
											}else if (count==-1) {
												request.setAttribute("err", "您的手机号已被注册！请更换新手机号");
												request.getRequestDispatcher("regist.jsp").forward(request, response);
											}else{
												request.setAttribute("err", "信息提交失败！请重新申请");
												request.getRequestDispatcher("regist.jsp").forward(request, response);
											}
										} else {
											request.setAttribute("err", "密码和确认密码必须一致！");
											request.getRequestDispatcher("regist.jsp").forward(request, response);
										}
									} else {
										request.setAttribute("err", "密码和确认密码必须为6-12位！");
										request.getRequestDispatcher("regist.jsp").forward(request, response);
									}
								} else {
									request.setAttribute("err", "邮箱不能为空！");
									request.getRequestDispatcher("regist.jsp").forward(request, response);
								}
							} else {
								request.setAttribute("err", "年龄不能为空且必须为不大于90数字！");
								request.getRequestDispatcher("regist.jsp").forward(request, response);
							}
						} else {
							request.setAttribute("err", "性别不能为空！");
							request.getRequestDispatcher("regist.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("err", "姓名不能为空！");
						request.getRequestDispatcher("regist.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("err", "手机号格式不合法，必须为大陆手机号！");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("err", "手机号不能为空且必须为11位数字！");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("err", "验证码有误！");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
