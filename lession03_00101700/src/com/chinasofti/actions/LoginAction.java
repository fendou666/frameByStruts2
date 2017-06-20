package com.chinasofti.actions;

import org.apache.struts2.ServletActionContext;

import com.chinasofti.common.MyException;
import com.chinasofti.vos.UserVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * Filename:    LoginAction.java  
 * Description:  ��¼��ز��� 
 * Copyright:   Copyright (c) 2015-2016 All Rights Reserved.
 * Company:     chinasofti.com Inc.
 * @author:     mazheng 
 * @version:    1.0  
 * Create at:   2015��3��11�� ����2:30:33  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015��3��11��      mazheng      1.0         1.0 Version  
 *
 */
public class LoginAction extends BaseAction implements ModelDriven, Preparable {

    private UserVO user;

    @Override
    public Object getModel() {

        return user;
    }

    @Override
    public void prepare() throws Exception {
        if (user == null) {
            user = new UserVO();
        }
    }

    /**
     * ��¼����
     * @return
     * @throws MyException 
     */
    public String login() throws MyException {

        // TODO ���ݿ���֤ģ��
        String uname = user.getUname();
        String pass = user.getPass();
        if (uname != null && !"".equals(uname) && pass != null && !"".equals(pass)) {

            if ("admin".equals(uname) && "admin".equals(pass)) {

                //                ActionContext.getContext().getSession().put("username", uname);
                //                ServletActionContext.getRequest().getSession().setAttribute("username1", "mary1");
                session.put("username1", "mary1");
                request.setAttribute("username2", "mary2");

                return Action.SUCCESS;
            }
        }

        addActionMessage("username and password error");
        return "loginerror";
    }
}
