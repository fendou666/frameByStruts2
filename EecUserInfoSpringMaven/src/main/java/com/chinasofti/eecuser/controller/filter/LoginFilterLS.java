package com.chinasofti.eecuser.controller.filter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.model.service.IStudentService;
import com.chinasofti.eecuser.model.service.StudentServiceImp;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
		} 
					, urlPatterns = { "/adfdasf" })
public class LoginFilterLS implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilterLS() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}
	
	private HashMap getRoleDirMap(){
		// Ȩ��Ŀ¼��Ӧ��ϵ
		HashMap<String, String> roleDirMap = new  HashMap<String, String>();
		roleDirMap.put("3001", "Z6Student");
		roleDirMap.put("3002", "Z6Leader");
		roleDirMap.put("3003", "Z6Monitor");
		roleDirMap.put("3004", "Z6HeadTeacher");
		roleDirMap.put("3005", "Z6TheTeacher");
		roleDirMap.put("3006", "Z6Admin");
		return roleDirMap;
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// �����URIĿ¼�ָ��
		String sep = "/";
		// ��ȡURI��ַ
		String uri = req.getRequestURI();
		// ��ȡ������
		String project = req.getContextPath() + sep;
		
		if(uri.indexOf(project)!=-1){
			String filePath = uri.substring(project.length());
			int dirEndIndex = -1;
			if((dirEndIndex = filePath.lastIndexOf(sep))!=-1){
				//System.out.println("filePath�ǣ�" + filePath);
				String uriDir =  filePath.substring(0, dirEndIndex);
				//System.out.println("��ȡ�� URI Ŀ¼��:" + uriDir);
				// ������ʱ��roleIdд��
				String roleID = "3001";
				HashMap<String, String> roleDirMap = getRoleDirMap();
				if(roleID==null && "Z6Reg".equals(uriDir)){
					chain.doFilter(request, response);
				}else if(roleID!=null && roleDirMap.get(roleID).equals(uriDir)){
					chain.doFilter(request, response);
				}else{
					req.getRequestDispatcher("error");
				}
			}else{
				chain.doFilter(request, response);
			}
			
		}else{
			//System.out.println("����4");
		}
		
//		String eecId = (String)req.getSession().getAttribute("eecId");
//		eecId = "170207008";
//		IStudentService iss = new StudentServiceImp();
//		UserInfo user = iss.queryUserById(Integer.parseInt(eecId));
//		//UserInfo user = null;
//		String roleName = user.getRoleName();
//		if(roleName.equals("ѧ��")){
//			//req.getRequestDispatcher("/Z6Student/Student.jsp").forward(request, response);
//			System.out.println("ContextPath:" + req.getServletContext().getContextPath());
//			System.out.println("RealPath:" + req.getServletContext().getRealPath("/Z6Student/Student.jsp"));
//			System.out.println("uri:" + );
//			//req.getRequestDispatcher("/Z6Student/Student.jsp").forward(request, response);
//		}
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
