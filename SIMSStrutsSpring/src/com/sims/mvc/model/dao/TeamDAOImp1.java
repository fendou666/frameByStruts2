package com.sims.mvc.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.bean.Team;
import com.sims.mvc.tools.PageManager;

public class TeamDAOImp1 implements ITeamDAO {
    private JdbcTemplate jt;
	List<Team> list=null;
	Team team=null;
	private final String CONDITION = " t_id,t_name,t_leader,class_id ";
	
	public TeamDAOImp1() {
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public Team findTeamById(String id) {		
		String sql="SELECT s.t_id,s.t_name,s.t_leader,s.class_id FROM sims_team s WHERE s.t_id='"+id+"'";
		return jt.query(sql, new ResultSetExtractor<Team>(){
			@Override
			public Team extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
						return new Team(
						rs.getString("t_id"),
						rs.getString("t_name"),
						rs.getString("t_leader"),
						rs.getString("class_id"));	
				}
				return null;
			}}); 
	}
	
	
	

	@Override
	public List<Team> findTeamsByName(String name) {
		// TODO Auto-generated method teamb
		return null;
	}

	@Override
	public List<Team> findTeamsByLeaderID(String leaderID) {
		// TODO Auto-generated method teamb
		return null;
	}
	@Override
	public int addTeam(Team team) {
		String sql="insert into sims_team values('";
			   sql+=team.getId()+"','";
			   sql+=team.getName()+"','";
			   sql+=team.getLeaderID()+"','";
			   sql+=team.getClassID()+"')";
		return jt.update(sql);
	}

	@Override
	public int modifyTeam(Team team) {
		String sql="update SIMS_TEAM t "+
				"set t.t_name = '"+team.getName()+
				"', t.t_leader = '"+team.getLeaderID()+
				"', t.class_id= '"+team.getClassID()+
				"' where t.t_id='"+team.getId() + "'";
		return jt.update(sql);
	}

	@Override
	public int deleteTeam(String teamID) {
		// TODO Auto-generated method teamb
		return 0;
	}
	
//	@Override
//	public List<Team> findTeamsByClassID(String classID) {		
//		String sql="SELECT s.t_id,s.t_name,s.t_leader,s.class_id FROM sims_team s WHERE s.class_id='"+classID+"'";
//		System.out.println("sql"+sql);
//		return jt.query(sql,new RowMapper<Team>(){
//			@Override
//			public Team mapRow(ResultSet rs, int index) throws SQLException {
//				if(rs.next()){
//				
//						return new Team(
//						rs.getString("t_id"),				
//						rs.getString("t_name"),
//						rs.getString("t_leader"),
//						rs.getString("class_id"));
//				}
//				return null;
//			}});
//	}
	
	private Team createTeam(ResultSet rs){
		Team ta=null;
		try {
			ta=new Team(
					rs.getString("t_id"),				
					rs.getString("t_name"),
					rs.getString("t_leader"),
					rs.getString("class_id")
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ta;
		
	}
	
	@Override
	public List<Student> findStudentByTeam(String classid) {
		String sql=null;
		sql="select s.s_id,s.s_name,s.s_sex,s.s_age,s.s_gradfrom,s.s_tel,s.s_addr,s.s_idcard,s.s_email,s.s_teamid from sims_student s where s.s_team is null and s.s_classid='"+classid+"'";
		return jt.query(sql, new RowMapper<Student>(){
			@Override
			public Student mapRow(ResultSet rs, int index) throws SQLException {
				if(rs.next()){
					return new Student(

							rs.getString("s_id"),
							rs.getString("s_name"),
					        rs.getString("s_sex"),
							rs.getInt("s_age"),
						    rs.getString("s_gradFrom"),
						    rs.getLong("s_tel"),
							rs.getString("s_addr"),
							rs.getString("s_idcard"),
							rs.getString("s_email"),
							rs.getString("s_teamid"),
							rs.getString("s_classid")
							);
				}
				return null;
			}});
	}
	@Override
	public int updateTeam(String teamId, String id) {
		String sql=null;
		sql="update sims_student  set s_teamid='"+teamId+"' where s_id='"+id+"'";
		return jt.update(sql);
	}
	@Test
	public void testRun(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ITeamDAO bean = ac.getBean("teamDAOImp1", ITeamDAO.class);
		System.out.println(bean.findStudentByTeam("1"));
	}
	@Override
	public List<Team> findTeamsByClassID(String classID){
		List<Team> list=null;
		//需要查询的属性字符串
				String attrStr = CONDITION;
				
				//需要查询的表和条件字符串
				String conditionStr = " sims_team ";
				
				
				//需要查询记录条数的id
				String conditionId = " class_id ";
				list = getQueryPage(attrStr, conditionStr, conditionId);
				
				return list;
		
	}
	private List<Team> getQueryPage(String attrStr, String conditionStr, String conditionId){
		List<Team> list=null;
		String sql="{?=call getSimPageRows(?,?,?,?,?,?,?)}";
		list=(List<Team>)jt.execute(
				new CallableStatementCreator() {
					
					@Override
					public CallableStatement createCallableStatement(Connection con)
							throws SQLException {
					           CallableStatement prepareCall = con.prepareCall(sql);
					           prepareCall.registerOutParameter(1, OracleTypes.CURSOR);
					           prepareCall.registerOutParameter(7, OracleTypes.INTEGER);
								prepareCall.registerOutParameter(8, OracleTypes.VARCHAR);
					          if(!PageManager.initFlag){
					        	  PageManager.PageManagerInit(8, 1);
									PageManager.initFlag = true; 
					          }
					          prepareCall.setInt(2, PageManager.getMaxPageRows());   //每页最大行数
								prepareCall.setInt(3, PageManager.getCurPageNo());	//当前查询页
								prepareCall.setString(4, attrStr);   //需要查询的属性字符串
								prepareCall.setString(5, conditionStr);   //需要查询的表和条件字符串
								prepareCall.setString(6, conditionId);   //需要查询记录条数的id
								return prepareCall;   
					}
				},new CallableStatementCallback<List<Team>>() {

					@Override
					public List<Team> doInCallableStatement(
							CallableStatement ts) throws SQLException,
							DataAccessException {
						 List<Team> resultsList = new ArrayList<Team>(); 
							ts.execute();
							ResultSet rs=(ResultSet)ts.getObject(1);
						     Team team=null;
						     while(rs.isFirst()){
						    	 team=createTeam(rs);
						    	 if(rs.isFirst()){
						    		 resultsList=new ArrayList<Team>(); 
						    	 }
						    	 resultsList.add(team);
						     }
						     rs.close(); 
							return resultsList;
					}
				});
		System.out.println("list============="+list);
		return list;
		
	}

}
