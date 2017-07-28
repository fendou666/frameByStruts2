package com.seasky.managersys.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.seasky.managersys.tools.DBUtil;

public class ForgetDaoImp implements IForgetDao {
	private DBUtil db=null;
	public ForgetDaoImp() {
		db=new DBUtil();
	}
	
	@Override
	public int checkForget(String name, int userId, String pwd, String phone) {
		int rs =0;
		String iphone;
		String iname;
		String ipwd;
		String sql="select u.name,u.phone,u.pwd from userInfo u where id="+userId;
		ResultSet rs1 = db.executeQuery(sql);
		try {
			if (rs1.next()) {
				iphone = rs1.getString("phone");
				iname = rs1.getString("name");
				ipwd = rs1.getString("pwd");
				System.out.println("forgetDao层拿到userid为"+userId);
				System.out.println("forgetDao层拿到phone为"+phone);
				System.out.println("forgetDao层查询到的手机号为"+iphone);
				System.out.println("forgetDao层查询到的姓名为"+iname);
				System.out.println("forgetDao层拿到pwd为"+pwd);
				System.out.println("forgetDao层查询到的pwd为"+ipwd);
				if (phone.equals(iphone)&&name.equals(iname)) {
					if (!ipwd.equals(pwd)) {
						sql="update userinfo set pwd = "+pwd+" where id="+userId;
						rs = db.executeUpdate(sql);
						System.out.println("rs is ok!!");
						System.out.println("forgetDao层从数据库查询拿到的查询结果："+rs);
						return rs;
					} 
					else{
						return -1;
					}
				} else {
					System.out.println("rs is wrong!!!");
					return rs;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
