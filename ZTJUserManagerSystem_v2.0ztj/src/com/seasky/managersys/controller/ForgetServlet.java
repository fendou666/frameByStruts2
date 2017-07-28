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
		//1.��ȡ������������ ע����������
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
		System.out.println("forget����������������ҳ���õ������� ��"+name);
		System.out.println("forget����������������ҳ���õ���ѧ�ţ�"+userId);
		System.out.println("forget����������������ҳ���õ����ֻ��ţ�"+phone);
		System.out.println("forget����������������ҳ���õ��������룺"+pwd);
		System.out.println("forget����������������ҳ���õ���ȷ�����룺"+pwdgain);
		System.out.println("forget����������������ҳ���õ�����֤�룺"+ident);
		System.out.println("----------------------------------------");
		UserInfo user=null;
		IForgetService forgetService=new ForgetServiceImp();
		
		request.removeAttribute("err");
		if (ident==null&&("").equals(ident)) {
			request.setAttribute("err", "��֤�벻��Ϊ�գ�");
			request.getRequestDispatcher("forget.jsp").forward(request, response);
		}
		else if(ident.equals(img)){
			if (name!=null&&!("").equals(name)) {
				if (userId!=null&&!("").equals(userId)) {
					if (phone!=null&&!("").equals(phone)&&phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[06-8])\\d{8}$")) {
						if (pwd!=null&&!("").equals(pwd)&&pwdgain!=null&&!("").equals(pwdgain)&&pwd.length()>5&&pwd.length()<13) {
							if (pwd.equals(pwdgain)){
									int count=forgetService.checkForget(name,Integer.parseInt(userId),pwd,phone);
									System.out.println("servlet���յ���count��"+count);
									if (count!=0) {
										if(count==-1) {
											request.setAttribute("err", "�����벻���������һ�£�");
											request.getRequestDispatcher("forget.jsp").forward(request, response);
										}else{
											request.getSession().setAttribute("text", "�����޸ĳɹ���");
											request.getRequestDispatcher("index.jsp").forward(request, response);
										}
									}
									else {
										request.setAttribute("err", "�����֤��ƥ�䣬�������޸ģ�");
										request.getRequestDispatcher("forget.jsp").forward(request, response);
									}
							} else {
								request.setAttribute("err", "�����ȷ���������һ�£�");
								request.getRequestDispatcher("forget.jsp").forward(request, response);
							}
						} else {
							request.setAttribute("err", "�����ȷ���������Ϊ6-12λ��");
							request.getRequestDispatcher("forget.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("err", "�ֻ��Ÿ�ʽ����");
						request.getRequestDispatcher("forget.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("err", "ѧ�Ų���Ϊ�գ�");
					request.getRequestDispatcher("forget.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("err", "�û�������Ϊ�գ�");
				request.getRequestDispatcher("forget.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("err", "��֤������");
			request.getRequestDispatcher("forget.jsp").forward(request, response);
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
