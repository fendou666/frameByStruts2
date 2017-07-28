package com.seasky.managersys.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	//定义区
	private static String driver;
	private static String url;
	private static String user;
	private static String pwd;
	static{
		Properties p=new Properties();
		try {
			p.load(DBUtil.class.getResourceAsStream("config.properties"));
			driver=p.getProperty("driver", "oracle.jdbc.OracleDriver");
			url=p.getProperty("url", "jdbc:oracle:thin:@175.3.13.17:1521:orcl");
			user=p.getProperty("user", "scott");
			pwd=p.getProperty("pwd", "tiger");
			System.out.println();
			//加载驱动
			Class.forName(driver);
			System.out.println("driver is ok!!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public DBUtil() {}
		
	public static Connection getConn(){
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("jdbc conn is ok!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
		
	//增删改
	public static int executeUpdate(String sql){
		int rec=0;
		Statement st = null;
		try {
			st=getConn().createStatement();
			rec=st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rec;
	}
		
	//查询
	public static ResultSet executeQuery(String sql){
		Statement st = null;
		ResultSet rs=null;
		try {
			st = getConn().createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
