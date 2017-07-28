package com.seasky.managersys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.model.service.IMonitorService;
import com.seasky.managersys.model.service.MonitorServiceImp;

/**
 * Servlet implementation class AppointLeaderServlet
 */
@WebServlet("/MonChooseServlet")
public class MonChooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonChooseServlet() {  }

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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		IMonitorService ims = new MonitorServiceImp();
//		��ȡ�������Լ��ο���ʦ��id
		String headmasterI = request.getParameter("headmasterId");
		String teacherI = request.getParameter("teacherId");
		String classId = request.getParameter("classId");
		int headmasterId = parseId(headmasterI);
		int teacherId = parseId(teacherI);
		int classI = parseId(classId);//�༶id
		UserInfo userTeacher = ims.getTeacherName(headmasterId);
		UserInfo headmaster = ims.getTeacherName(teacherId);
		System.out.println("�����Σ�"+headmaster.getName());
		System.out.println("�ο���ʦ��"+userTeacher.getName());
		
		System.out.println("action:"+action);
		if(action.equals("modifyLeader")){
//			ָ���鳤
			String id = request.getParameter("id");//ѧ��id
			String groupId = request.getParameter("groupId");//���id
			int groupI = parseId(groupId);
//			�Ը�id����ݽ����޸�
			int count = ims.updateLeader(Integer.parseInt(id),classI,groupI);
			if(count==0){
				System.out.println("δ�޸ĳɹ�����");
			}else{
				System.out.println("�޸ĳɹ�����");
			}
			
			int power = 3;
			List<UserInfo> listStuEM = ims.getStudents(classI, power);
			request.setAttribute("listStuEM", listStuEM);
//			������������
			request.setAttribute("userTeacher", userTeacher);
			request.setAttribute("headmaster", headmaster);
			request.getRequestDispatcher("Mon_pointLeader.jsp").forward(request, response);
		}
		if(action.equals("divGroup")){
			String selValue = request.getParameter("selValue");//�鳤ID 
			String id = request.getParameter("id");
			
			System.out.println("selValue:"+selValue);
			System.out.println("id:"+id);
			if(selValue==null||selValue.equals("")||id==null||id.equals("")){
				request.getRequestDispatcher("Mon_divGroup.jsp").forward(request, response);
				return;
			}else{
				int count = ims.updateStudent(Integer.parseInt(id), Integer.parseInt(selValue));
				if(count==0){
					System.out.println("δ�޸ĳɹ�����");
				}else{
					System.out.println("�޸ĳɹ�����");
				}
			}
//			������������
			int power = 2;
//			������������
			request.setAttribute("userTeacher", userTeacher);
			request.setAttribute("headmaster", headmaster);
			System.out.println("�����Σ�"+headmaster.getName());
			System.out.println("�ο���ʦ��"+userTeacher.getName());
			List<UserInfo> listLeader = ims.getLeaderInfos(classI, power);//��ȡ�鳤������
			List<UserInfo> listStu = ims.getStudents(classI, power);
			request.setAttribute("listStu", listStu);
			request.setAttribute("listLeader", listLeader);
			request.getRequestDispatcher("Mon_divGroup.jsp").forward(request, response);
		}
	}

//	������ȡ���ַ���id����ת����ת��Ϊ����
	private static int parseId(String strId){
		int id = 0;
		if(strId==null||strId.equals("")){
			id = 0;
		}
		else{
			id = Integer.parseInt(strId);
		}
		return id;
	}
}
