package com.seasky.managersys.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.seasky.managersys.model.bean.UserInfo;
import com.seasky.managersys.tools.DBUtil;

public class LoginDaoImp implements ILoginDao {
	private DBUtil db=null;
	public LoginDaoImp() {
		db=new DBUtil();
	}
	
	@Override
	public UserInfo checkLogin(int userId, String pwd) {
		UserInfo user=null;
		int id=0;
		String name=null;
		String sex=null;
		int age=0;
		int headmasterId=0;
		int teacherId=0;
		int classId=0;
		int groupId=0;
		long phone=0;
		String address=null;
		String mail=null;
		int power=0;
		String isDelete=null;
		System.out.println("loginDao层拿到userid为"+userId);
		String sql="select u.id,u.name,u.pwd,u.sex,u.age"
				+ ",u.headmasterid,u.teacherid,u.classid"
				+ ",u.groupid,u.phone,u.address,u.mail"
				+ ",u.power,u.isdelete from userInfo u where id="+userId;
		ResultSet rs = db.executeQuery(sql);
		System.out.println("rs is ok!!");
		try {
			if(rs.next()){
				if(rs.getString("pwd").equals(pwd)){
					id=rs.getInt("id");
					name=rs.getString("name");
					sex=rs.getString("sex");
					age=rs.getInt("age");
					headmasterId=rs.getInt("headmasterid");
					teacherId=rs.getInt("teacherid");
					classId=rs.getInt("classid");
					groupId=rs.getInt("groupid");
					phone=rs.getLong("phone");
					address=rs.getString("address");
					mail=rs.getString("mail");
					power=rs.getInt("power");
					isDelete=rs.getString("isDelete");
					/*if(headmasterId==0&&teacherId==0&&classId==0&&groupId==0){
						user = new UserInfo(id, name, sex, age, phone, address, mail, power, isDelete);
					}else{*/
						user=new UserInfo(id, name, sex, age, headmasterId, teacherId, classId, groupId, phone, address, mail, power, isDelete);
					/*}*/
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("teacherId:"+teacherId);
		System.out.println("headmasterId:"+headmasterId);
		return user;
	}

	@Override
	public UserInfo checkLogin(long iphone, String pwd) {
			UserInfo user=null;
			int id=0;
			String name=null;
			String sex=null;
			int age=0;
			int headmasterId=0;
			int teacherId=0;
			int classId=0;
			int groupId=0;
			long phone=0;
			String address=null;
			String mail=null;
			int power=0;
			String isDelete=null;
			System.out.println("loginDao层拿到手机号为"+iphone);
			String sql="select u.id,u.name,u.pwd,u.sex,u.age"
					+ ",u.headmasterid,u.teacherid,u.classid"
					+ ",u.groupid,u.phone,u.address,u.mail"
					+ ",u.power,u.isdelete from userInfo u where phone="+iphone;
			ResultSet rs = db.executeQuery(sql);
			System.out.println("rs is ok!!");
			try {
				if (rs.next()) {
					if(rs.getString("pwd").equals(pwd)){
						System.out.println("已进入查询");
						id=rs.getInt("id");
						name=rs.getString("name");
						sex=rs.getString("sex");
						age=rs.getInt("age");
						headmasterId=rs.getInt("headmasterid");
						teacherId=rs.getInt("teacherid");
						classId=rs.getInt("classid");
						groupId=rs.getInt("groupid");
						phone=rs.getLong("phone");
						address=rs.getString("address");
						mail=rs.getString("mail");
						power=rs.getInt("power");
						isDelete=rs.getString("isDelete");
						if(headmasterId==0&&teacherId==0&&classId==0&&groupId==0){
							user = new UserInfo(id, name, sex, age, phone, address, mail, power, isDelete);
							System.out.println("他是老师");
						}else{
							user=new UserInfo(id, name, sex, age, headmasterId, teacherId, classId, groupId, phone, address, mail, power, isDelete);
							System.out.println(name+"\t"+id);
							System.out.println("他是学生");
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(33333333);
			System.out.println("= teacherId:"+teacherId);
			System.out.println("=  headmasterId:"+headmasterId);
		return user;
	}
	

}
