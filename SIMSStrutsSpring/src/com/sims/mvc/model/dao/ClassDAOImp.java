package com.sims.mvc.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import oracle.jdbc.OracleTypes;

import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.bean.Student;
import com.sims.mvc.tools.PageManager;

public class ClassDAOImp implements IClassDAO {
	private JdbcTemplate jt;
	
	//һ���ҳ��ѯ����Ҫ�����ԣ��������ϲ�ѯ�����Կ����Լ�ƴ�ӣ�ƴ��ʱע���һ��','�ţ��μ�
	private final String CONDITION = " c_id,c_name,c_monitor,c_man_teacher,c_teac_teacher ";
		
	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	public ClassDAOImp(){
		
	}

	@Override
	public CClass findClassById(String id) {
		//����sql
		String sql="select " + CONDITION + " from SIMS_CLASS  where c_id='"+id+"'";
		return jt.query(sql, new ResultSetExtractor<CClass>(){

			@Override
			public CClass extractData(ResultSet rs) throws SQLException,
					DataAccessException {
		
					if(rs.next()){
              		  return new CClass(
              				rs.getString("c_id"),
              				  rs.getString("c_name"),
              				  rs.getString("c_monitor"),
              				  rs.getString("c_man_teacher"),
              				  rs.getString("c_teac_teacher"));
                           }                        
				return null;
			}});
		/*System.out.println(sql);
		CClass cc=null;
		ResultSet rs=dbutil.query(sql);
		try {
			if(rs.next()){
				cc = createClass(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cc;*/
	}

	@Override
	public CClass findClassesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CClass findClassesByMonitorId(String monitorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CClass findClassesByManTeacherId(String manTeacherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CClass findClassesByTeacTeacherId(String teacTeacherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addClass(CClass cclass) {
		String sql="insert into SIMS_CLASS values('"+cclass.getId()+"','"+cclass.getName()+"','"
					+ (cclass.getMonitorID()==null ? "" : cclass.getMonitorID()) +
				"','"+cclass.getManTeacherID()+"','"+cclass.getTeacTeacherID()+"')";
		return jt.update(sql);
	}

	@Override
	public int modifyClass(CClass cclass) {
		String sql="update SIMS_CLASS s "+
				"set s.c_id = '"+cclass.getId()+
				"', s.c_name = '"+cclass.getName()+
				"', s.c_monitor='"+(cclass.getMonitorID()==null?"":cclass.getMonitorID())+
				"', s.c_man_teacher='"+(cclass.getManTeacherID()==null?"":cclass.getManTeacherID())+
				"', s.c_teac_teacher='"+(cclass.getTeacTeacherID()==null?"":cclass.getTeacTeacherID())+
				"' where s.c_id='"+cclass.getId() + "'";
		System.out.println("�༶id===="+cclass.getMonitorID());
		
		return jt.update(sql);
	}

	@Override
	public int deleteClass(String cclassId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CClass> getClassesAll() {
		List<CClass> list=null;
		
		//��Ҫ��ѯ�������ַ���
		String attrStr = CONDITION;
		
		//��Ҫ��ѯ�ı�������ַ���
		String conditionStr = " SIMS_CLASS ";
		
		
		//��Ҫ��ѯ��¼������id
		String conditionId = " c_id ";
		list = getQueryPage(attrStr, conditionStr, conditionId);
		
		return list;
	}
	
	
	 private CClass createClass(ResultSet rs){
		CClass cc = null;
		
		try {
			cc=new CClass(
				rs.getString("c_id"), 
				rs.getString("c_name"), 
				rs.getString("c_monitor"), 
				rs.getString("c_man_teacher"), 
				rs.getString("c_teac_teacher"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cc;
	}
	
	//���з�ҳ��ѯ
	private List<CClass> getQueryPage(String attrStr, String conditionStr, String conditionId){
		List<CClass> list = null;
		
		String sql = "{?= call getSimsPageRows(?,?,?,?,?,?,?)}";
		
		list = (List<CClass>) jt.execute(   
		         new CallableStatementCreator() {   
		            public CallableStatement createCallableStatement(Connection con) throws SQLException { 
			CallableStatement prepareCall = con.prepareCall(sql);
			prepareCall.registerOutParameter(1, OracleTypes.CURSOR);
			prepareCall.registerOutParameter(7, OracleTypes.INTEGER);
			prepareCall.registerOutParameter(8, OracleTypes.VARCHAR);
			
			if(!PageManager.initFlag){
				PageManager.PageManagerInit(8, 1);
				PageManager.initFlag = true;
			}
			
			prepareCall.setInt(2, PageManager.getMaxPageRows());   //ÿҳ�������
			prepareCall.setInt(3, PageManager.getCurPageNo());	//��ǰ��ѯҳ
			prepareCall.setString(4, attrStr);   //��Ҫ��ѯ�������ַ���
			prepareCall.setString(5, conditionStr);   //��Ҫ��ѯ�ı�������ַ���
			prepareCall.setString(6, conditionId);   //��Ҫ��ѯ��¼������id
			return prepareCall;   
		  }   
		}, new CallableStatementCallback<List<CClass>>() {   
            public List<CClass> doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {   
            List<CClass> resultsList = new ArrayList<CClass>(); 
			cs.execute();
			
//			PageManager.setMaxRowNumber((int)(prepareCall.getObject(7)));
			
			ResultSet rs = (ResultSet)cs.getObject(1);
			CClass cla = null;
			while(rs.next()){
				cla = createClass(rs);
				if(rs.isFirst()){
					resultsList=new ArrayList<CClass>();
				}
				resultsList.add(cla);
			}			
				rs.close(); 
				return resultsList;   
            } 
            });   
	     return list;
   }
	
	  public static void main(String[] args) {
		List<CClass> list = new ClassDAOImp().getClassesAll();
		System.out.println(list.size());
	}

}
