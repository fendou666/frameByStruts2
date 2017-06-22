package com.chinasofti.eecuser.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eecuser.model.javabean.UserAceessANW;
import com.chinasofti.eecuser.model.service.ISVCAccessSafeANS;
import com.chinasofti.eecuser.model.service.UserAccessANWImp;

/**
 * Servlet implementation class AccessSafeAnswerServlet
 */
@WebServlet("/AccessSafeAnswerServlet")
public class AccessSafeAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccessSafeAnswerServlet() {
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String eecIdStr = request.getParameter("eecId");
		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");
		String answer3 = request.getParameter("answer3");
		
		
		int ret = 0; // 0������֤��ȷ�� 1������Ϣ�п�ֵ , 2���������ݶԱȺ����
		UserAceessANW UA = null;
		ISVCAccessSafeANS accessService = new UserAccessANWImp();
		
		if(eecIdStr==null || eecIdStr=="" ||
			answer1==null || answer1=="" ||
			answer2==null || answer1=="" ||
			answer3==null || answer1==""
				){
			request.setAttribute("errInfo", "��Ϣ����Ϊ��");
			//request.getRequestDispatcher("accessSafeAnswer.jsp").forward(request, response);
		}else{
			try{
				System.out.println("parseIntǰID" + eecIdStr);
				int eecId = Integer.parseInt(eecIdStr);
				System.out.println("parseInt��ID" + eecId);
				UA = new UserAceessANW(eecId, answer1, answer2);
				System.out.println("javabean �е�id" + UA.getEec_id());
				if(accessService.accessAnswer(UA, answer3)){
					ret = 0;
				}else{
					ret = 1;
				}
				System.out.println("ret Ϊ" + ret);
			}catch(Exception e){
				ret = 1;
			}
		}
		// ����ַ�
		if(ret!=0){
			request.getRequestDispatcher("accessSafeAnswer.jsp").forward(request, response);
		}else{
			// �ź������ֵҳ��
			request.getRequestDispatcher("pwdReset.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
