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

public class AdminTheacherDAOImp implements IAdminTheacherDAO {

	
	@Override
	public List<UserInfo> queryEecUserOutClass(int id, String name,
			SqlDataPage AddteacherPage) {
		String conditionStr = " u.class_id is null AND u.manager_id is null";
		if(id!=-1){
			conditionStr += " AND u.id="+id;
		}else if(name!=null){
			conditionStr += " AND u.name="+name;
		}
		System.out.println("条件执行语句是" + conditionStr);
		List<UserInfo> userList = null;
		String sql = "{?=call eecUserInfoPageRows(?,?,?,?)}";
		
		try {
			// 函数存储过程通过这中方式调用
			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
			pc.registerOutParameter(1, OracleTypes.CURSOR);
			pc.setObject(2, AddteacherPage.getPageMaxRows());
			pc.setObject(3, AddteacherPage.getCurrentPage());
			pc.setObject(4, conditionStr);
			pc.registerOutParameter(5, OracleTypes.INTEGER);
			
			pc.execute();
			
			ResultSet rs = (ResultSet)pc.getObject(1);
			if(rs!=null){
				// 设置总记录条数
				AddteacherPage.setAllRows((int)pc.getObject(5));
				// 设置最大页码
				AddteacherPage.setMaxPageIndexByAllRows();
				userList = new ArrayList<UserInfo>();
				
				while(rs.next()){
					System.out.println("id 为 "  + rs.getString("eec_id"));
					userList.add(new UserInfo(
							0, // class_id为null
							Integer.parseInt(rs.getString("EEC_Id")),
							rs.getString("EEC_Name"),
							rs.getString("sex"),
							Integer.parseInt(rs.getString("age")),
							rs.getString("email"),
							Long.parseLong(rs.getString("telephone")),
							null // role_name 为空
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

	@Override
	public boolean updEecUserOutClass(int id, int classId, int theacherType) {
		String sql = "UPDATE eecuser SET role_id=?, class_id=? WHERE eec_id=?";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(theacherType);
		objList.add(classId);
		objList.add(id);
		int updRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(updRows==0){
			return false;
		}else{
			return true;
		}
	}

	// 条件语句整理
	private String getConditionString(int roleId, int classId,
			int id, String name){
		
		String conditionStr = "";
		if(roleId!=-1 && roleId!=0){
			conditionStr += " AND u.role_id=" + roleId;
		}else{
			conditionStr += " AND u.role_id>=3004 AND u.role_id<=3005";
		}
		if(classId!=-1 && classId!=0){
			conditionStr += " AND u.class_id=" + classId;
		}
		if(id!=-1){
			conditionStr += " AND u.eec_id=" + id;
		}
		if(name != null){
			conditionStr += " AND u.eec_name='" + name +"'";
		}
		return conditionStr;
	}
		
	@Override
	public List<UserInfo> queryDataByCondition(int roleId, int classId,
			int id, String name, SqlDataPage teacherPage) {
		// where 需要的条件语句
		String conditionStr = getConditionString(roleId, classId, id, name);
		System.out.println("条件语句是 " + conditionStr);
		// 返回的用户列表
		List<UserInfo> userList = null;
		String sql = "{?=call eecAdminQueryTeacherPageRows(?,?,?,?)}";
		
		try {
			// 函数存储过程通过这中方式调用
			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
			pc.registerOutParameter(1, OracleTypes.CURSOR);
			pc.setObject(2, teacherPage.getPageMaxRows());
			pc.setObject(3, teacherPage.getCurrentPage());
			pc.setObject(4, conditionStr);
			pc.registerOutParameter(5, OracleTypes.INTEGER);
			
			pc.execute();
			
			ResultSet rs = (ResultSet)pc.getObject(1);
			if(rs!=null){
				// 设置总记录条数
				teacherPage.setAllRows((int)pc.getObject(5));
				// 设置最大页码
				teacherPage.setMaxPageIndexByAllRows();
				userList = new ArrayList<UserInfo>();
				
				while(rs.next()){
					System.out.println("id 为 "  + rs.getString("eec_id"));
					System.out.println("class_id 为：" + rs.getString("class_Id"));
					userList.add(new UserInfo(
							Integer.parseInt(rs.getString("class_Id")==null?"0000":rs.getString("class_Id")),
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

	@Override
	public boolean deleteTheacher(int id) {
		String sql = "DELETE FROM eecuser WHERE u.eec_id=?";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(id);
		int delRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(delRows==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean updateTheacher(UserInfo u) {
		String sql = "UPDATE eecuser u SET u.role_id=? WHERE u.eec_id=?";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(u.getRoleId());
		objList.add(u.getId());
		int updRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(updRows==0){
			return false;
		}else{
			return true;
		}
	}
	public static void main(String[] args) {
		System.out.println("int 最大值：" + Integer.MAX_VALUE);
	}
}
