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
			errInfo = "�������ݿ������ļ�ʧ��";
			System.out.println(errInfo);
		}else{
			getConnection();
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
	private Connection getConnection(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			errInfo = "û���ҵ�JDBC����";
			System.out.println(errInfo);
			e.printStackTrace();
		} catch (SQLException e) {
			errInfo = "�������ݿ�����ʧ��";
			System.out.println(errInfo);
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
	//DBUtil.getJDBC().getConn();
	public Connection getConn(){
		return db.conn;
	}
	public  ResultSet queryDate(String sql, ArrayList<Object> objList){
		ResultSet rs = null;
		if(errInfo!=null){
			return rs;
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(objList!=null){
				for(int i=0; i<objList.size();i++){
					ps.setObject(i+1, objList.get(i));
					System.out.println("i����Ϊ" + objList.get(i));
				}
			}
			System.out.println("sqlִ�����Ϊ:" + sql);
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			errInfo = "��ѯsqlִ��ʧ��";
			System.out.println(errInfo);
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
					System.out.println("i����Ϊ" + objList.get(i));
				}
			}
			ret = ps.executeUpdate();
			System.out.println("��ɾ��ִ�е�sql ��" + sql);
		} catch (SQLException e) {
			errInfo = "��ɾ��sqlִ��ʧ��";
			System.out.println(errInfo);
			e.printStackTrace();
		}
		return ret;
	}
	
}
