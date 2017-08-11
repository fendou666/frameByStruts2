package com.sims.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.service.IStudentService;
import com.sims.mvc.model.service.StudentServiceImp;

/**
 * Servlet implementation class queryServlet
 */
@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateServlet() {
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
		 String id=null;	//ѧ��
		 String pwd=null;	//����
		 String name=null;	//����
		 String sex=null;	//�Ա�
		 String age= null;	//����
		 String gradFrom=null;	//��ҵԺУ
		 String tel= null;	//�绰����
		 String addr=null;	//��ַ
		 String idCard =null;	//���֤����
		 String email =null;	//����
		 Student stu=null;
		 
		 
		 
		 //ע��ѧԱ
		 if(action.equals("insert_stu")){
			 id=request.getParameter("id");
			 pwd=request.getParameter("pwd");
			 name=request.getParameter("name");
			 sex=request.getParameter("sex");
			 age=request.getParameter("age");
			 gradFrom=request.getParameter("gradFrom");
			 tel=request.getParameter("tel");
			 addr=request.getParameter("addr");
			 idCard=request.getParameter("idCard");
			 email=request.getParameter("email");
			 System.out.println(tel);
			 System.out.println("tel"+tel.length());
			 
			 if(id==null||id.equals("")||pwd==null||pwd.equals("")||name==null||name.equals("")||sex==null||sex.equals("")
					 ||age==null||age.equals("")||gradFrom.equals("")||gradFrom==null||tel.equals("")||tel==null||
					 addr.equals("")||addr==null||idCard.equals("")||idCard==null||email.equals("")||email==null){
				 request.setAttribute("registErr", "��д���ݲ���Ϊ�գ�");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
					return;
			 }else if(tel.length()>=14){
				 request.setAttribute("registErr", "�绰���볬����14λ���������룡");
					request.getRequestDispatcher("regist.jsp").forward(request, response);
					return;
			 }else{
				 System.out.println(1);
				 stu=new Student();
				 stu.setId(id);
				 stu.setName(name);
				 stu.setSex(sex);
				 stu.setAge(Integer.parseInt(age));
				 stu.setGradFrom(gradFrom);
				 stu.setTel(Long.parseLong(tel));
				 stu.setAddr(addr);
				 stu.setIdCard(idCard);
				 stu.setEmail(email);
				 
				 IStudentService is=new StudentServiceImp();
				 int addStudent = is.addStudent(stu, pwd);
				 if(addStudent>0){
					 System.out.println("��ӳɹ�");
//					 System.out.println("add"+addStudent);
//					 request.setAttribute("registMsg", "��ϲ��ע��ɹ���");
					 request.getRequestDispatcher("Login.jsp").forward(request, response);
				 }else{
					 System.out.println("���ʧ��");
				 }
			 }
		 }
		 
		 if(action.equals("changepwd")){
			 id=request.getParameter("id");
			 pwd=request.getParameter("pwd");
			 String apwd=request.getParameter("apwd");
			 if(apwd!=null&&!apwd.equals("")&&pwd!=null&&!pwd.equals("")){
				 if(apwd.equals(pwd)){
					 IStudentService is=new StudentServiceImp();
					 int changePwd = is.modifyStudentPWD(id, pwd);
					 if(changePwd>0){
						request.setAttribute("chpwd", "�޸ĳɹ�");
						request.getRequestDispatcher("Login.jsp").forward(request, response);
					 }else{
						 request.setAttribute("chpwd", "�޸�ʧ�ܣ�����������");
							request.getRequestDispatcher("changepwd.jsp").forward(request, response);
					 }
				 }else{
					 request.setAttribute("chpwd", "�������벻��ͬ");
						request.getRequestDispatcher("changepwd.jsp").forward(request, response);
				 }
				
			 }else{
				 request.setAttribute("chpwd", "���벻��Ϊ��");
					request.getRequestDispatcher("changepwd.jsp").forward(request, response);
			 }
		 }
	}

}
