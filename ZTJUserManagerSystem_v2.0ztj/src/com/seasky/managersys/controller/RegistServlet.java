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
		//1.��ȡ������������ ע����������
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
		System.out.println("regist��������ע��ǰ̨�õ���������"+userName);
		System.out.println("regist��������ע��ǰ̨�õ������䣺"+age);
		System.out.println("regist��������ע��ǰ̨�õ����Ա�"+sex);
		System.out.println("regist��������ע��ǰ̨�õ������룺"+pwd);
		System.out.println("regist��������ע��ǰ̨�õ���ȷ�����룺"+pwdgain);
		System.out.println("regist��������ע��ǰ̨�õ����ֻ��ţ�"+phone);
		System.out.println("regist��������ע��ǰ̨�õ������䣺"+mail);
		System.out.println("regist��������ע��ǰ̨�õ�����֤�룺"+ident);
		System.out.println("regist����������֤�������õ�����֤�룺"+img);
		System.out.println("----------------------------------------");
		IRegistService registService=new RegistServiceImp();
		
		request.removeAttribute("err");
		if (ident==null&&("").equals(ident)) {
			request.setAttribute("err", "��֤�벻��Ϊ�գ�");
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
												request.setAttribute("text", "ע�����ύ���ȴ�����Ա���");
												request.getRequestDispatcher("regist.jsp").forward(request, response);
											}else if (count==-1) {
												request.setAttribute("err", "�����ֻ����ѱ�ע�ᣡ��������ֻ���");
												request.getRequestDispatcher("regist.jsp").forward(request, response);
											}else{
												request.setAttribute("err", "��Ϣ�ύʧ�ܣ�����������");
												request.getRequestDispatcher("regist.jsp").forward(request, response);
											}
										} else {
											request.setAttribute("err", "�����ȷ���������һ�£�");
											request.getRequestDispatcher("regist.jsp").forward(request, response);
										}
									} else {
										request.setAttribute("err", "�����ȷ���������Ϊ6-12λ��");
										request.getRequestDispatcher("regist.jsp").forward(request, response);
									}
								} else {
									request.setAttribute("err", "���䲻��Ϊ�գ�");
									request.getRequestDispatcher("regist.jsp").forward(request, response);
								}
							} else {
								request.setAttribute("err", "���䲻��Ϊ���ұ���Ϊ������90���֣�");
								request.getRequestDispatcher("regist.jsp").forward(request, response);
							}
						} else {
							request.setAttribute("err", "�Ա���Ϊ�գ�");
							request.getRequestDispatcher("regist.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("err", "��������Ϊ�գ�");
						request.getRequestDispatcher("regist.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("err", "�ֻ��Ÿ�ʽ���Ϸ�������Ϊ��½�ֻ��ţ�");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("err", "�ֻ��Ų���Ϊ���ұ���Ϊ11λ���֣�");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("err", "��֤������");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
