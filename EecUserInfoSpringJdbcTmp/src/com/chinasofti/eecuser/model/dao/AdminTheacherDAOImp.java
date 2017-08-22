package com.chinasofti.eecuser.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.OracleTypes;

import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.TableSqlNeed;
import com.chinasofti.eecuser.model.javabean.UserInfo;

public class AdminTheacherDAOImp implements IAdminTheacherDAO {
	private JdbcTemplate jt;
	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	@Override
	public List<UserInfo> queryEecUserOutClass(int id, String name,
			SqlDataPage AddteacherPage) {
		String conditionStr = " AND u.class_id is null AND u.manager_id is null";
		if(id!=-1){
			conditionStr += " AND u.id="+id;
		}else if(name!=null && !name.equals("all")){
			conditionStr += " AND u.name="+name;
		}
		System.out.println("����ִ�������" + conditionStr);
		// ������붨��Ϊfinal
		final String cdtStr = conditionStr;
		// �����洢����ͨ�����з�ʽ����
		return jt.execute(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection conn)
						throws SQLException {
					String sql = "{?=call eecUserInfoPageRows(?,?,?,?)}";
					CallableStatement pc = conn.prepareCall(sql);
					pc.registerOutParameter(1, OracleTypes.CURSOR);
					pc.setObject(2, AddteacherPage.getPageMaxRows());
					pc.setObject(3, AddteacherPage.getCurrentPage());
					pc.setObject(4, cdtStr);
					pc.registerOutParameter(5, OracleTypes.INTEGER);
					return pc;
				}
			}, new CallableStatementCallback<List<UserInfo>>() {

				@Override
				public List<UserInfo> doInCallableStatement(
						CallableStatement pc) throws SQLException,
						DataAccessException {
					pc.execute();
					System.out.println("��ȡ��sql������" + pc.getObject(5));
					ResultSet rs = (ResultSet)pc.getObject(1);
					List<UserInfo> userList = null;
					if(rs!=null){
							// �����ܼ�¼����
							AddteacherPage.setAllRows((int)pc.getObject(5));
							// �������ҳ��
							AddteacherPage.setMaxPageIndexByAllRows();
							userList = new ArrayList<UserInfo>();
							while(rs.next()){
								System.out.println("id Ϊ "  + rs.getString("eec_id"));
								userList.add(new UserInfo(
										0, // class_idΪnull
										Integer.parseInt(rs.getString("EEC_Id")),
										rs.getString("EEC_Name"),
										rs.getString("sex"),
										Integer.parseInt(rs.getString("age")),
										rs.getString("email"),
										Long.parseLong(rs.getString("telephone")),
										null // role_name Ϊ��
								));
							}
						}
						if (userList!=null && userList.size() == 0){
							userList = null;
						}
						return userList;
					}
				});
	}

	@Override
	public boolean updEecUserOutClass(int id, int classId, int theacherType) {
		String sql = "UPDATE eecuser SET role_id=?, class_id=? WHERE eec_id=?";
		int updRows = jt.update(sql, new Object[]{theacherType, classId, id});
		if(updRows==0){
			return false;
		}else{
			return true;
		}
	}

	// �����������
	private String getConditionString(int roleId, int classId,
			int id, String name){
		
		String conditionStr = "";
		if(roleId!=-1 && roleId!=0){
			conditionStr += " AND u.role_id=" + roleId;
		}else{
			conditionStr += " AND u.role_id>=3004 AND u.role_id<=3005";
		}
		if(classId!=-1 && classId!=0){
			conditionStr += " AND u.class_id=" + classId;
		}
		if(id!=-1){
			conditionStr += " AND u.eec_id=" + id;
		}
		if(name != null){
			conditionStr += " AND u.eec_name LIKE '%" + name +"%'";
		}
		return conditionStr;
	}
		
	@Override
	public List<UserInfo> queryDataByCondition(int roleId, int classId,
			int id, String name, SqlDataPage teacherPage) {
		// where ��Ҫ���������
		String conditionStr = getConditionString(roleId, classId, id, name);
		System.out.println("��������� " + conditionStr);
		// ���ص��û��б�
		// ������붨��Ϊfinal
		final String cdtStr = conditionStr;
		// �����洢����ͨ�����з�ʽ����
		return jt.execute(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection conn)
						throws SQLException {
					String sql = "{?=call eecAdminQueryTeacherPageRows(?,?,?,?)}";
					CallableStatement pc = conn.prepareCall(sql);
					pc.registerOutParameter(1, OracleTypes.CURSOR);
					System.out.println("teacherPage.getPageMaxRows()" + teacherPage.getPageMaxRows());
					pc.setObject(2, teacherPage.getPageMaxRows());
					System.out.println("teacherPage.getCurrentPage()" + teacherPage.getCurrentPage());
					pc.setObject(3, teacherPage.getCurrentPage());
					pc.setObject(4, conditionStr);
					pc.registerOutParameter(5, OracleTypes.INTEGER);
					return pc;
				}
			}, new CallableStatementCallback<List<UserInfo>>() {

				@Override
				public List<UserInfo> doInCallableStatement(
						CallableStatement pc) throws SQLException,
						DataAccessException {
					pc.execute();
					System.out.println("��ȡ��sql������" + pc.getObject(5));
					ResultSet rs = (ResultSet)pc.getObject(1);
					List<UserInfo> userList = null;
					if(rs!=null){
						// �����ܼ�¼����
						teacherPage.setAllRows((int)pc.getObject(5));
						// �������ҳ��
						teacherPage.setMaxPageIndexByAllRows();
						userList = new ArrayList<UserInfo>();
						while(rs.next()){
							if(rs.isFirst()){
								System.out.println("���ݿⷵ�ض������ݵ�һ��ִ��");
							}
							//System.out.println("id Ϊ "  + rs.getString("eec_id"));
							userList.add(new UserInfo(
									Integer.parseInt(rs.getString("class_Id")==null?"0000":rs.getString("class_Id")),
									Integer.parseInt(rs.getString("EEC_Id")),
									rs.getString("EEC_Name"),
									rs.getString("sex"),
									Integer.parseInt(rs.getString("age")),
									rs.getString("email"),
									Long.parseLong(rs.getString("telephone")),
									rs.getString("role_name")
							));
						}
					}
					if (userList!=null && userList.size() == 0){
						userList = null;
					}
					return userList;
				}
			});
 	}

	@Override
	public boolean deleteTheacher(int id) {
		// ���ﲻ����ɾ��sql����
		//String sql = "DELETE FROM eecuser WHERE u.eec_id=?";
		String sql = "UPDATE eecuser SET isdelete=1 WHERE eec_id=?";
		int delRows = jt.update(sql, new Object[]{id});
		if(delRows==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean updateTheacher(UserInfo u) {
		// TODO �����Ƿ���Ҫ�� isdeleteд��?, ��ʼ��ѯ��ʱ���Ѿ���isdelete������ɸѡ
		String sql = "UPDATE eecuser u SET u.role_id=? WHERE u.eec_id=?";
		ArrayList<Object> objList = new ArrayList<Object>();
		int updRows = jt.update(sql, new Object[]{u.getRoleId(), u.getId()});
		if(updRows==0){
			return false;
		}else{
			return true;
		}
	}
}