package com.chinasofti.eecuser.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class LoginController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		String userPermission = request.getParameter("userPermission");
		if(userPermission!=null){
			if(userPermission.equals("0")){
				modelAndView.setViewName("Z6Admin/Admin");;
			}else{
				modelAndView.setViewName("error");;
			}
		}else{
			modelAndView.setViewName("error");;
		}
		return modelAndView;
	}

}
