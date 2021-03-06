package com.chinasofti.eecuser.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class StudentDAOImp implements IStudentDAO {
	
	private UserInfo userInfo=null;
	private DBUtil dbUtil;
	public DBUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public UserInfo queryUserById(int eecId) {
		String sql="select u.eec_name,u.eec_id,u.sex,u.birthday,u.telephone,u.email,u.class_Id,r.role_id,r.role_name"
					+ " from eecuser u ,eecrole r where u.role_id = r.role_id and u.eec_id=?";
		
		PreparedStatement ps;
		try {
			System.out.println("DAO:"+dbUtil.getConn());
			ps = dbUtil.getConn().prepareStatement(sql);
			
			ps.setInt(1, eecId);
			ResultSet rs = ps.executeQuery();
			System.out.println("queryUserById sql:"+sql);
			if(rs.next()){
				userInfo=new UserInfo();
				userInfo.setName(rs.getString("eec_name"));
				userInfo.setId(rs.getInt("eec_id"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setBirthday(rs.getDate("birthday"));
				userInfo.setTelephone(rs.getLong("telephone"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setClassId(rs.getInt("class_id"));
				userInfo.setRoleId(rs.getInt("role_id"));
				userInfo.setRoleName(rs.getString("role_name"));
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return userInfo;
	}
	
	@Override
	public ArrayList<UserInfo> queryByClassId(int classId) {
		ArrayList<UserInfo> list=null;
		String sql="select u.eec_name,u.eec_id,u.sex,u.birthday,u.telephone,u.email,u.class_Id,r.role_id,r.role_name"
				+ " from eecuser u ,eecrole r where u.role_id = r.role_id and u.role_id<3004 and class_id=?";
	
		PreparedStatement ps;
		try {
			ps = dbUtil.getConn().prepareStatement(sql);
			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.isFirst()){
					list=new ArrayList<UserInfo>();
				}
				userInfo=new UserInfo();
				userInfo.setName(rs.getString("eec_name"));
				userInfo.setId(rs.getInt("eec_id"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setBirthday(rs.getDate("birthday"));
				userInfo.setTelephone(rs.getLong("telephone"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setClassId(rs.getInt("class_id"));
				userInfo.setRoleId(rs.getInt("role_id"));
				userInfo.setRoleName(rs.getString("role_name"));
				list.add(userInfo);
			}
			rs.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return list;
	}
	
	
	@Override
	public int updateUserById(long telephone,String email,int eecId) {
		int count=0;
		String sql="update eecuser set telephone=?,email=? where EEC_Id=?";
	
		PreparedStatement ps;
	try {
		ps = dbUtil.getConn().prepareStatement(sql);
		ps.setLong(1, telephone);
		ps.setString(2, email);
		ps.setInt(3, eecId);
		count = ps.executeUpdate();
		System.out.println("sql:"+sql);
		
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
		return count;
	}

	@Override
	public ArrayList<UserInfo> queryUserByConditions(int eecId, int classId,String eecName,
			String roleName) {
		ArrayList<UserInfo> list=null;
		String sql="select u.eec_name,u.eec_id,u.sex,u.birthday,u.telephone,u.email,u.class_Id,r.role_id,r.role_name"
					+ " from eecuser u ,eecrole r where 1=1";
		String stuId = eecId+"";
		String stuClass = classId+"";
		if(roleName != null && !roleName.equals("")){
			sql+=" and u.role_id=r.role_id and role_name='"+roleName+"'";
		}
		else{
			sql+="and u.role_id=r.role_id and u.role_id<3004";
		}
		if(stuId != null && !stuId.equals("-1")){
			System.out.println("stu=========");
			sql+=" and u.eec_id="+eecId;
		}
		if(stuClass != null && !stuClass.equals("-1")){
			sql+=" and u.class_id="+classId;
		}
		if(eecName != null && !eecName.equals("")){
			sql+=" and u.eec_name='"+eecName+"'";
		}
		System.out.println("queryUserByConditions========sql==:"+sql);
		PreparedStatement ps;
		try {
			ps = dbUtil.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.isFirst()){
					list=new ArrayList<UserInfo>();
				}
				userInfo=new UserInfo();
				userInfo.setName(rs.getString("eec_name"));
				userInfo.setId(rs.getInt("eec_id"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setBirthday(rs.getDate("birthday"));
				userInfo.setTelephone(rs.getLong("telephone"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setRoleName(rs.getString("role_name"));
				userInfo.setClassId(rs.getInt("class_id"));
				userInfo.setRoleId(rs.getInt("role_id"));
				System.out.println("userInfo:"+userInfo);
				list.add(userInfo);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return list;
	}


	
}
