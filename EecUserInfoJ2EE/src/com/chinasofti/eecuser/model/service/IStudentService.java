package com.chinasofti.eecuser.model.service;

import java.util.ArrayList;
import java.util.List;

import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface IStudentService {
	/**
	 * 根据eecId查询用户个人信息
	 * @param id
	 * @return 返回查询到的对象用户信息
	 */
	public UserInfo queryUserById(int eecId);
	/**
	 * 根据所给的条件查询用户信息
	 * @param eecId
	 * @param eecName
	 * @param roleName
	 * @return 返回查询到的用户信息
	 */
	public ArrayList<UserInfo> queryUserByConditions(int eecId, int classId,String eecName,
			String roleName);
	/**
	 * 查询班级信息
	 * @return 返回班级信息的集合
	 */
	public List<UserInfo> queryByClassId(int classId);
	/**
	 * 根据eecId修改用户信息
	 * @param eecId
	 * @return 返回修改后的对象用户信息
	 */
	public int updateUserById(long telephone,String email,int eecId);
}
