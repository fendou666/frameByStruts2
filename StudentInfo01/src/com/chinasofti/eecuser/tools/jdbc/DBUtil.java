package com.chinasofti.eecuser.tools.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBUtil {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private Connection conn;
	private String errInfo;
	private static DBUtil db = new DBUtil(); 
	
	private DBUtil(){
		
		if(!getDbInitInfo()){
			errInfo = "加载数据库配置文件失败";
		}else{
			getConn();
		}
	}
	private boolean getDbInitInfo(){
		boolean ret = true;
		Properties pro = new Properties();
		try {
			pro.load(DBUtil.class.getResourceAsStream("jdbcpro.properties"));
			driver 	= pro.getProperty("driver", "oracle.jdbc.OracleDriver");
			url 	= pro.getProperty("url", "jdbc:oracle:thin:@175.3.13.32");
			user 	= pro.getProperty("user", "scott");
			pwd 	= pro.getProperty("pwd", "tiger");
			if(driver==null || url==null || user==null || pwd==null){
				ret = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	private Connection getConn(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			errInfo = "没有找到JDBC驱动";
			e.printStackTrace();
		} catch (SQLException e) {
			errInfo = "创建数据库连接失败";
			e.printStackTrace();
		}
		return conn;
	}
	
	public static DBUtil getJDBC(){
		return db;
	}
	public String getErrInfo(){
		return errInfo;
	}
	
	public  ResultSet queryDate(String sql, ArrayList<Object> objList){
		ResultSet rs = null;
		if(errInfo!=null){
			return rs;
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//PreparedStatement ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(objList!=null){
				for(int i=0; i<objList.size();i++){
					ps.setObject(i+1, objList.get(i));
					System.out.println("i对象为" + objList.get(i));
				}
			}
			System.out.println("sql执行语句为:" + sql);
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			errInfo = "查询sql执行失败";
			e1.printStackTrace();
		}
		return rs;
	}
	
	
	public  int updateSql(String sql,  ArrayList<Object> objList){
		int ret = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(objList!=null){
				for(int i=0; i<objList.size();i++){
					ps.setObject(i+1, objList.get(i));
					System.out.println("i对象为" + objList.get(i));
				}
			}
			ret = ps.executeUpdate();
			System.out.println("增删改执行的sql 是" + sql);
		} catch (SQLException e) {
			errInfo = "增删该sql执行失败";
			System.out.println(errInfo);
			e.printStackTrace();
		}
		return ret;
	}
	
}
