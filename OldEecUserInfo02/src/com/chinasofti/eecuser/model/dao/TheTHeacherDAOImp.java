package com.chinasofti.eecuser.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class TheTHeacherDAOImp implements ITheTheacherDAO {

	@Override
	public UserInfo showPersonalInfo(int id) {
		
		return null;
	}

	@Override
	public List<UserInfo> getClassUserInfo(int classId, SqlDataPage theTheacherPage) {
		String conditionStr = " u.class_id="+classId + " ";
		System.out.println("条件语句是 " + conditionStr);
		// 返回的用户列表
		List<UserInfo> userList = null;
		String sql = "{?=call eecAdminQueryTeacherPageRows(?,?,?,?)}";
		
		try {
			// 函数存储过程通过这中方式调用
			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
			pc.registerOutParameter(1, OracleTypes.CURSOR);
			pc.setObject(2, theTheacherPage.getPageMaxRows());
			pc.setObject(3, theTheacherPage.getCurrentPage());
			pc.setObject(4, conditionStr);
			pc.registerOutParameter(5, OracleTypes.INTEGER);
			
			pc.execute();
			
			ResultSet rs = (ResultSet)pc.getObject(1);
			if(rs!=null){
				// 设置总记录条数
				theTheacherPage.setAllRows((int)pc.getObject(5));
				// 设置最大页码
				theTheacherPage.setMaxPageIndexByAllRows();
				userList = new ArrayList<UserInfo>();
				
				while(rs.next()){
					System.out.println("id 为 "  + rs.getString("eec_id"));
					userList.add(new UserInfo(
							Integer.parseInt(rs.getString("class_Id")),
							Integer.parseInt(rs.getString("EEC_Id")),
							rs.getString("EEC_Name"),
							rs.getString("sex"),
							Integer.parseInt(rs.getString("age")),
							rs.getString("email"),
							Long.parseLong(rs.getString("telephone")),
							rs.getString("role_name")
					));
				}
			}
			System.out.println("获取到sql数量是" + pc.getObject(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (userList!=null && userList.size() == 0){
			userList = null;
		}
		return userList; 
	}

}
