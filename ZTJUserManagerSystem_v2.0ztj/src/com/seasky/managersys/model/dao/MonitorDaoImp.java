package com.seasky.managersys.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.tools.DBUtil;
import com.seasky.managersys.tools.PageManagerMonitor;

public class MonitorDaoImp implements IMonitorDao {
	private DBUtil db;
	
	public MonitorDaoImp(){
		db = new DBUtil();
	}
	
//	获取本班的所有成员
	@Override
	public List<UserInfo> getUserInfos(int classId) {
		List<UserInfo> list=null;
		ResultSet rs=null;
		try {
			String sql="{?=call getClassPageRowss(?,?,?,?)}";
			CallableStatement prepareCall = db.getConn().prepareCall(sql);
			prepareCall.registerOutParameter(1, OracleTypes.CURSOR);
			prepareCall.registerOutParameter(5, OracleTypes.NUMBER);
			//最大页面表示行数
			prepareCall.setInt(2, PageManagerMonitor.pageMaxRows);
			System.out.println("PageManagerMonitor.pageMaxRows:"+PageManagerMonitor.pageMaxRows);
			//当前页码
			prepareCall.setInt(3, PageManagerMonitor.currentPageNo);
			System.out.println("PageManagerMonitor.currentPageNo："+PageManagerMonitor.currentPageNo);
			//classId
			prepareCall.setInt(4, classId);
			prepareCall.execute();
			
			if (PageManagerMonitor.initFlg==false) {
				PageManagerMonitor.initPageInfo(10, 1, prepareCall.getInt(5));
			}
			rs=(ResultSet)prepareCall.getObject(1);
			int i = 1;
			while(rs.next()){
				i++;
				if(rs.isFirst()){
					i=0;
					list=new ArrayList<UserInfo>();
				}
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				int age=rs.getInt("age");
				long phone =rs.getLong("phone");
				String address=rs.getString("address");
				String mail=rs.getString("mail");
				int groupId=rs.getInt("groupId");
				int power=rs.getInt("power");
				String isDelete=rs.getString("isDelete");
				if(isDelete==null){
					isDelete = "未审核";
				}else if(isDelete.equals("true")){
					isDelete = "不在校";
				}else{
					isDelete = "在校";
				}
				UserInfo user = new UserInfo(id, name, sex, age, phone, classId, groupId, address, mail, power, isDelete);
				list.add(user);
			}
			System.out.println("getUserInfos i:"+i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	指定组长，修改原组组长的权限
	@Override
	public int updateLeader(int id,int classId,int groupId) {
		String sqlSel = "select power from userInfo where id="+id;
		ResultSet rs = db.executeQuery(sqlSel);
		int power = 0;
		try {
			if(rs.next()){
				power = rs.getInt("power");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int count = 0;
		System.out.println("power:"+power);
		if(power!=2){
			String sql = "update userInfo set power="+2+", groupId="+id+" where id="+id+" and classId="+classId;
			String sql1 = "update userInfo set power="+1+" where id="+groupId+" and classId="+classId;
			count = db.executeUpdate(sql);
			count += db.executeUpdate(sql1);
			System.out.println("count:"+count);
		}
		return count;
	}

//	获取组长的信息
	@Override
	public List<UserInfo> getLeaderInfos(int classId, int power) {
		List<UserInfo> list = null;
		UserInfo user=null;
		int id=0;
		String name=null;
		String isDelete=null;
		String sql="select u.id,u.name,u.isDelete"
				+ " from userInfo u where u.classId="+ classId +"and u.power="+power;
		ResultSet rs = db.executeQuery(sql);
		System.out.println("rs is ok!!!");
		
		list = new ArrayList<UserInfo>();
		try {
			while(rs.next()){
				id=rs.getInt("id");
				name=rs.getString("name");
				isDelete=rs.getString("isDelete");
				if(isDelete.equals("true")){
					continue;
				}
				else{
					user=new UserInfo(id, name, classId, power, isDelete);
				}
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	分配组员,根据指定的组长的id修改小组的编号
	@Override
	public int updateStudent(int id, int groupId) {
		String sql = "update userInfo set groupId="+groupId+" where id =" +id;
		return db.executeUpdate(sql);
	}

	/*//获取除班长以外的学员的数据
	@Override
	public List<UserInfo> getClassStudents(int classId) {
		List<UserInfo> list=null;
		ResultSet rs=null;
		try {
			String sql="{?=call getClassPageRowss(?,?,?,?)}";
			CallableStatement prepareCall = db.getConn().prepareCall(sql);
			prepareCall.registerOutParameter(1, OracleTypes.CURSOR);
			prepareCall.registerOutParameter(5, OracleTypes.NUMBER);
			//最大页面表示行数
			prepareCall.setInt(2, PageManagerMonitor.pageMaxRows);
			//当前页码
			prepareCall.setInt(3, PageManagerMonitor.currentPageNo);
			//classId
			prepareCall.setInt(4, classId);
			prepareCall.execute();
			
			if (PageManagerMonitor.initFlg==false) {
				PageManagerMonitor.initPageInfo(10, 1, prepareCall.getInt(5));
			}
			rs=(ResultSet)prepareCall.getObject(1);
			while(rs.next()){
				if(rs.isFirst()){
					list=new ArrayList<UserInfo>();
				}
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int groupId=rs.getInt("groupId");
				int power=rs.getInt("power");
				if(power!=1||power!=2){
					continue;
				}
				String isDelete=rs.getString("isDelete");
				if(isDelete.equals("true")){
					continue;
				}
				UserInfo user=new UserInfo(id, power, name, groupId, isDelete);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}*/

	//获取普通学员的数据
	@Override
	public List<UserInfo> getStudents(int classId, int power) {
		List<UserInfo> list=null;
		ResultSet rs=null;
		try {
			String sql="{?=call getClassPageRowss(?,?,?,?)}";
			@SuppressWarnings("static-access")
			CallableStatement prepareCall = db.getConn().prepareCall(sql);
			prepareCall.registerOutParameter(1, OracleTypes.CURSOR);
			prepareCall.registerOutParameter(5, OracleTypes.NUMBER);
			//最大页面表示行数
			prepareCall.setInt(2, PageManagerMonitor.pageMaxRows);
			//当前页码
			prepareCall.setInt(3, PageManagerMonitor.currentPageNo);
			//classId
			prepareCall.setInt(4, classId);
			prepareCall.execute();
			
			if (PageManagerMonitor.initFlg==false) {
				PageManagerMonitor.initPageInfo(10, 1, prepareCall.getInt(5));
			}
			rs=(ResultSet)prepareCall.getObject(1);
			while(rs.next()){
				if(rs.isFirst()){
					list=new ArrayList<UserInfo>();
				}
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int groupId=rs.getInt("groupId");
				int power1=rs.getInt("power");
				if(power1!=power){
					String isDelete=rs.getString("isDelete");
					if(isDelete.equals("true")){
						continue;
					}
					UserInfo user=new UserInfo(id, power1, classId, name, groupId, isDelete);
					list.add(user);
				}
				else{
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	获取老师的姓名
	@Override
	public UserInfo getTeacherName(int id) {
		UserInfo user = null;
		String sql = "select name from teacherInfo where id="+id;
		ResultSet rs = db.executeQuery(sql);
		try {
			while(rs.next()){
				String name = rs.getString("name");
				user = new UserInfo(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
