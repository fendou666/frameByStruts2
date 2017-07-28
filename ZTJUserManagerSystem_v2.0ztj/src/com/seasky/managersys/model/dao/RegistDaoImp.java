package com.seasky.managersys.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.seasky.managersys.tools.DBUtil;

public class RegistDaoImp implements IRegistDao {
	private DBUtil db=null;
	public RegistDaoImp() {
		db=new DBUtil();
	}
	
	@Override
	public int checkRegist(String userName,String sex,int age,String pwd,String phone,String mail) {
		String sql="";
		ResultSet rs = null;
		int rt = 0;
		/*System.out.println("registDao²ãÄÃµ½useridÎª"+userName);*/
		sql="select name from userInfo where phone = "+phone;
		rs = db.executeQuery(sql);
		try {
			if (!rs.next()) {
				sql="insert into userInfo values(id_index.nextval,'"+userName+"','"+pwd+"','"+sex+"','"+age+"',0,0,0,0,'"+phone+"',null,'"+mail+"',0,'false')";
				rt = db.executeUpdate(sql);
				System.out.println("rs is ok!!");
				return rt;
			}else{
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}
	

}
