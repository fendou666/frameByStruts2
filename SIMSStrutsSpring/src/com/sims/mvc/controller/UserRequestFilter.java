package com.sims.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

import com.sims.mvc.model.bean.Student;

@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/content/*" })
public class UserRequestFilter implements Filter {

    public UserRequestFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  req= (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		String path = new String(req.getRequestURL());
		
		Student stu = (Student)req.getSession().getAttribute("stuInfo");
		String user_right = (String) req.getSession().getAttribute("user_right");
		
		String root = req.getContextPath();
		
		if(stu == null){
			res.sendRedirect(root + "/jsp/Login.jsp");
			return;
		}else{
			if( (path.contains("/teamLeader/") && !user_right.equals("isleader"))
				|| (path.contains("/classMonit/") && !user_right.equals("ismonitor"))
				|| (path.contains("/classManger/") && !user_right.equals("ismanteach"))
				|| (path.contains("/teacher/") && !user_right.equals("isteacteach"))
				|| (path.contains("/admin/") && !user_right.equals("isnormadmin"))
				|| (path.contains("/superAdmin/") && !user_right.equals("admin"))){
				
				res.sendRedirect(root + "/jsp/fail.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
