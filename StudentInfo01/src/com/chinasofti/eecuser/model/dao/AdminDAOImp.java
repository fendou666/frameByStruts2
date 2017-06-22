package com.chinasofti.eecuser.model.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class AdminDAOImp implements IAdminDAO {
	
	@Override
	public UserInfo queryDataById(int eecId) {
		UserInfo u = null;
		List<UserInfo> userList = queryDataByCondition(-1, -1, eecId, null);
		if(userList!=null){
			u = userList.get(0);
		}
		return u;
	}
	
	
	// 条件拼接， 如果返回值为""， 确定没有任何数据， 然后sql查询直接返回为null
//	private String getConditionString(int roleId, int classId,
//			int eecId, String eecName){
//		String conditionStr = "";
//		conditionStr += " AND u.role_id=" +roleId;
//		conditionStr += " AND u.class_id=" +roleId;
//		conditionStr += " AND u.eec_id=" +roleId;
//		if(eecName != null){
//			conditionStr += " AND u.eec_name='" +eecName +"'";
//		}
//		return conditionStr;
//	}
	
	private String getConditionString(int roleId, int classId,
			int eecId, String eecName,  ArrayList<Object> objList){
		
		String conditionStr = "";
		if(roleId!=-1 && roleId!=0){
			conditionStr += " AND u.role_id=?";
			objList.add(roleId);
		}
		if(classId!=-1 && classId!=0){
			conditionStr += " AND u.class_id=?";
			objList.add(classId);
		}
		if(eecId!=-1){
			conditionStr += " AND u.eec_id=?";
			objList.add(eecId);
		}
		if(eecName != null){
			conditionStr += " AND u.eec_name=?";
			objList.add(eecName);
		}
		return conditionStr;
	}
	
	@Override
	public List<UserInfo> queryDataByCondition(int roleId, int classId,
			int eecId, String eecName) {
		ArrayList<Object> objList = new ArrayList<Object>();
		// TODO  java.sql.SQLException: 对只转发结果集的无效操作: first
		String conditionStr = getConditionString(roleId, classId, eecId, eecName,  objList);
		List<UserInfo> userList = null;
		
		if(conditionStr.equals("")){
//			return null; // 默认也需要查找不能返回
		}
		
		String sql="select u.class_Id,u.EEC_Id,u.EEC_Name,u.sex,u.age,u.email,u.telephone,r.role_name from eecuser u,eecrole r";
		sql += " WHERE 1='1' AND u.role_id = r.role_id AND u.role_id >=3004  AND u.role_id <=3005";
		sql += conditionStr;
		System.out.println("执行的sql语句是" + sql);
		
		ResultSet rs = DBUtil.getJDBC().queryDate(sql, objList);
		if(rs!=null){
			try {
				userList = new ArrayList<UserInfo>();
				while(rs.next()){
					// TODO java.sql.SQLException: 对只转发结果集的无效操作: first
//					if(rs.first()){
//						userList = new ArrayList<UserInfo>();
//					}
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (userList.size() == 0){
			userList = null;
		}
		return userList;  
	}

	@Override
	public boolean insertEecuserData(UserInfo u) {
		//INSERT INTO eecuser(class_Id, EEC_Id, EEC_Name, sex, age, email, telephone, role_id) VALUES();
		String sql = "INSERT INTO eecuser(class_Id, EEC_Id, EEC_Name, sex, age, email, telephone, role_id) VALUES(?,?,?,?,?,?,?,?)";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(u.getClassId());
		objList.add(u.getId());
		objList.add(u.getName());
		objList.add(u.getSex());
		objList.add(u.getAge());
		objList.add(u.getEmail());
		objList.add(u.getTelephone());
		objList.add(u.getRoleId());
		int insertCount = DBUtil.getJDBC().updateSql(sql, objList);
		if(insertCount == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean deleteEecuserDataById(int id) {
		String sql = "DELETE FROM eecuser WHERE EEC_Id=" +id;
		int delCount = DBUtil.getJDBC().updateSql(sql, null);
		if(delCount == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean updateEecuserData(UserInfo u) {
		String sql="Update eecuser set class_Id="+u.getClassId()
						+ ",EEC_Name='"+u.getName()
						+"',email="+u.getEmail()
						+",telephone="+u.getTelephone()
						+",sex='"+u.getSex()
						+",Role_id="+u.getRoleId()
						+"where EEC_ID="+u.getId();
		int updateCount = DBUtil.getJDBC().updateSql(sql, null);
		if(updateCount == 0){
			return false;
		}else{
			return true;
		}
	}

	

}
