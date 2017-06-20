package com.chinasofti.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
SessionAware, ServletResponseAware
{
    
    protected HttpServletResponse response;
    protected Map session;
    protected HttpServletRequest request;

    @Override
    public void setServletResponse(HttpServletResponse arg0) {
        response = arg0;
    }

    @Override
    public void setSession(Map<String, Object> arg0) {
        session = arg0;
    }

    @Override
    public void setServletRequest(HttpServletRequest arg0) {
        request = arg0;
    }

}
