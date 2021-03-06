package com.chinasofti.eecuser.tools.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.chinasofti.eecuser.model.javabean.UserInfo;

public class JdbcTmpTest {
	private JdbcTemplate jt;

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	// 动态sql查询
	public void testPrepareStatement(){
		jt.execute(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				String sql = "SELECT * FROM eecuser u WHERE u.role_id>=?";
				PreparedStatement ps = conn.prepareStatement(sql);	
				ps.setInt(1, 3004);
				return ps;
			}
			
		}, new PreparedStatementCallback(){
			@Override
			public Object doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					System.out.println(rs.toString());
				}
				return null;
			}
		});
	}
	// 函数，存储过程查询
	
	public void testProcedure(){
		List<UserInfo> execute = jt.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				String sql = "{call testemp(?,?,?)}"; 
				CallableStatement pc = conn.prepareCall(sql);
				pc.setInt(1, 111);;
				pc.setInt(2, 22222);
				pc.registerOutParameter(2, OracleTypes.INTEGER);
				pc.registerOutParameter(3, OracleTypes.CURSOR);
				return pc;
			}
		}, new CallableStatementCallback<List<UserInfo>>() {

			@Override
			public List<UserInfo> doInCallableStatement(CallableStatement pc)
					throws SQLException, DataAccessException {
				pc.execute();
				ResultSet rs  = (ResultSet)pc.getObject(3);
				while(rs.next()){
					System.out.println(rs.toString());
				}
				return null;
			}
		});
	}

	public void testFunc(){
		List<UserInfo> execute = jt.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				String sql = "{? = call testfunc(?,?,?)}"; 
				CallableStatement pc = conn.prepareCall(sql);
				pc.registerOutParameter(1, OracleTypes.CURSOR);
				pc.setInt(2, 111);;
				pc.setInt(3, 22222);
				pc.registerOutParameter(3, OracleTypes.INTEGER);
				pc.registerOutParameter(4, OracleTypes.INTEGER);
				return pc;
			}
		}, new CallableStatementCallback<List<UserInfo>>() {

			@Override
			public List<UserInfo> doInCallableStatement(CallableStatement pc)
					throws SQLException, DataAccessException {
				pc.execute();
				ResultSet rs  = (ResultSet)pc.getObject(1);
				while(rs.next()){
					System.out.println(rs.toString());
				}
				return null;
			}
		});
	}
	
	public void testFunc2(){
		
		List<UserInfo> execute = jt.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				String sql = "{? = call testfunc(?,?,?)}"; 
				CallableStatement pc = conn.prepareCall(sql);
				pc.registerOutParameter(1, OracleTypes.CURSOR);
				pc.setInt(2, 111);;
				pc.setInt(3, 22222);
				pc.registerOutParameter(3, OracleTypes.INTEGER);
				pc.registerOutParameter(4, OracleTypes.INTEGER);
				return pc;
			}
		}, new CallableStatementCallback<List<UserInfo>>() {

			@Override
			public List<UserInfo> doInCallableStatement(CallableStatement pc)
					throws SQLException, DataAccessException {
				pc.execute();
				ResultSet rs  = (ResultSet)pc.getObject(1);
				while(rs.next()){
					System.out.println(rs.toString());
				}
				return null;
			}
		});
	}
	
}
