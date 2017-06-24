package com.chinasofti.eecuser.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class AdminClassDAOImp implements IAdminClassDAO {

	@Override
	public List<ClassInfo> queryClassInfoByCondition(int class_id, SqlDataPage classPage) {
		List<ClassInfo> classList = null;
		String sql = "{?=call eecClassInfoPageRows(?,?,?)}";
		
		try {
			// 函数存储过程通过这中方式调用
			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
			pc.registerOutParameter(1, OracleTypes.CURSOR);
			pc.setObject(2, classPage.getPageMaxRows());
			pc.setObject(3, classPage.getCurrentPage());
			pc.registerOutParameter(4, OracleTypes.INTEGER);
			pc.execute();
			
			ResultSet rs = (ResultSet)pc.getObject(1);
			if(rs!=null){
				// 设置总记录条数
				classPage.setAllRows((int)pc.getObject(5));
				// 设置最大页码
				classPage.setMaxPageIndexByAllRows();
				classList = new ArrayList<ClassInfo>();
				
				while(rs.next()){
					classList.add(new ClassInfo(
							Integer.parseInt(rs.getString("class_Id")),
							rs.getString("describe"),
							rs.getString("monitor_name"),
							rs.getString("javateacher_name"),
							rs.getString("classteacher_name")
					));
				}
			}
			System.out.println("获取到sql数量是" + pc.getObject(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (classList!=null && classList.size() == 0){
			classList = null;
		}
		return classList;  
		
	}

	@Override
	public boolean createClass(ClassInfo newClass) {
		String sql = "INSERT CLASS(class_id,monitor_id,javateacher_id,classteacher_id, describe) VALUES(?,?,?,?,?)";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(newClass.getClassId());
		objList.add(newClass.getMonitorId());
		objList.add(newClass.getJavateacherId());
		objList.add(newClass.getClassteacherId());
		objList.add(newClass.getDescribe());
		int insertRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(insertRows == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean deleteClass(int classId) {
		String sql = "DELETE FROM CLASS WHERE class_id=?";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(20170207);
		int delRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(delRows == 0){
			return false;
		}else{
			return true;
		}
	}

}
