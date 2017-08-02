package com.chinasofti.eecuser.controller.filter;

import java.io.IOException;

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
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/Z6Student/Studentaadf.jsp" })
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String eecId = (String)req.getSession().getAttribute("eecId");
		eecId = "170207008";
		IStudentService iss = new StudentServiceImp();
		UserInfo user = iss.queryUserById(Integer.parseInt(eecId));
		//UserInfo user = null;
		String roleName = user.getRoleName();
		if(roleName.equals("学生")){
			//req.getRequestDispatcher("/Z6Student/Student.jsp").forward(request, response);
			System.out.println("ContextPath:" + req.getServletContext().getContextPath());
			System.out.println("RealPath:" + req.getServletContext().getRealPath("/Z6Student/Student.jsp"));
			System.out.println("uri:" + req.getRequestURI());
			//req.getRequestDispatcher("/Z6Student/Student.jsp").forward(request, response);
		}
//		else if(roleName.equals("组长")){
//			req.getRequestDispatcher("Leader.jsp").forward(request, response);;
//		}
//		else if(roleName.equals("班长")){
//			req.getRequestDispatcher("Monitor.jsp").forward(request, response);;
//		}
//		else if(roleName.equals("班主任")){
//			req.getRequestDispatcher("HeadTeacher.jsp").forward(request, response);;
//		}
//		else if(roleName.equals("任课老师")){
//			req.getRequestDispatcher("TheTeacher.jsp").forward(request, response);;
//		}
//		else{
//			req.getRequestDispatcher("Admin.jsp").forward(request, response);;
//		}
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
