package com.chinasofti.eecuser.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.model.javabean.SqlDataPage;
import com.chinasofti.eecuser.model.javabean.TableSqlNeed;
import com.chinasofti.eecuser.model.javabean.ClassInfo;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class AdminClassDAOImp implements IAdminClassDAO {

	
	
//	@Override
//	public List<ClassInfo> queryClassInfoByCondition(int class_id, SqlDataPage classPage) {
//		List<ClassInfo> classList = null;
//		String sql = "{?=call eecClassInfoPageRows(?,?,?)}";
//		
//		try {
//			// �����洢����ͨ�����з�ʽ����
//			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
//			pc.registerOutParameter(1, OracleTypes.CURSOR);
//			pc.setObject(2, classPage.getPageMaxRows());
//			pc.setObject(3, classPage.getCurrentPage());
//			pc.registerOutParameter(4, OracleTypes.INTEGER);
//			pc.execute();
//			
//			ResultSet rs = (ResultSet)pc.getObject(1);
//			if(rs!=null){
//				// �����ܼ�¼����
//				classPage.setAllRows((int)pc.getObject(5));
//				// �������ҳ��
//				classPage.setMaxPageIndexByAllRows();
//				classList = new ArrayList<ClassInfo>();
//				
//				while(rs.next()){
//					classList.add(new ClassInfo(
//							Integer.parseInt(rs.getString("class_Id")),
//							rs.getString("describe"),
//							rs.getString("monitor_name"),
//							rs.getString("javateacher_name"),
//							rs.getString("classteacher_name")
//					));
//				}
//			}
//			System.out.println("��ȡ��sql������" + pc.getObject(5));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if (classList!=null && classList.size() == 0){
//			classList = null;
//		}
//		return classList;  
//		
//	}

	@Override
	public boolean createClass(ClassInfo newClass) {
		String sql = "INSERT CLASS(class_id,monitor_id,javateacher_id,classteacher_id, describe) VALUES(?,?,?,?,?)";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(newClass.getClassId());
		objList.add(newClass.getMonitorId());
		objList.add(newClass.getTheTeacherId());
		objList.add(newClass.getHeadTeacherId());
		objList.add(newClass.getDescribe());
		int insertRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(insertRows == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean deleteClass(int classId) {
		String sql = "DELETE FROM CLASS WHERE class_id=?";
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(20170207);
		int delRows = DBUtil.getJDBC().updateSql(sql, objList);
		if(delRows == 0){
			return false;
		}else{
			return true;
		}
	}

	
	
//	 TODO ���﹤����Ҫ���� �ֶ������� ��, ������Բ����ԶԱ�����ո�
	@Override
	public List<ClassInfo> queryClassInfoByCondition(int class_id,
			String headTeacherName, 
			SqlDataPage classPage) {
		// ��Ҫ��ѯ�ı��б�
		String [] tableList 		= {"eecclass"};
		// ���ı���
		String [] aliasTableList 	= {"c"};
		// ÿ����������Ӧ��Ҫ���ҵ��ֶ�
		HashMap<String, String[]> tablesNeedFilds	= new HashMap<String, String[]>();
		// TODO ��������û����Ҫ��ѯ���ֶΣ� Ӧ���Ǵ�����飿����
		// ������Ҫ�����ֶθ������������ �����ֱ�Ӹ������� �ֶα��������ݿ��Ӧ����Ȼ�����ҵ�����
		tablesNeedFilds.put("c", new String[]{"class_id", "monitor_id", 
				"theTeacher_id", "headTeacher_id", "describe"});
		// �Զ��������ֶ�
		String [] customFilds = {
			"(SELECT u.eec_name FROM eecuser u WHERE u.eec_id=c.monitor_id) monitor_name",
			"(SELECT u.eec_name FROM eecuser u WHERE u.eec_id=c.theTeacher_id) theTeacher_name",
			"(SELECT u.eec_name FROM eecuser u WHERE u.eec_id=c.headTeacher_id) headTeacher_name"
		};
		
		// ͳ��������ʹ�õ��Ǹ��ֶ�
		String sqlCountFild 		= "c.class_id";	
		// ������ѯ������ֶ�
		String sqlOrderByFild 		= "c.class_id"; 
		// ����࣬�������ֶν���ƴ�ӣ� ��������ֱ�ӵ���
		TableSqlNeed test = new TableSqlNeed();
		test.initAllSqlStrData(tableList, aliasTableList, tablesNeedFilds, customFilds, sqlCountFild, sqlOrderByFild);

		// where ��Ҫ���������
		String conditionStr = "";
		test.executeSqlStr(conditionStr, 10, 1);
		// ���ص��û��б�
		List<ClassInfo> classList = null;
		String sql = "{?=call eecQueryPageRows(?,?,?,?,?,?,?,?,?,?)}";
		try {
			// �����洢����ͨ�����з�ʽ����
			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
			pc.registerOutParameter(1, OracleTypes.CURSOR);
			// ÿҳ10��
			pc.setObject(2, 10);
			// ��ǰҳ��Ϊ��һҳ
			pc.setObject(3, 1);
			pc.setObject(4, test.getSqlCountFild());
			pc.setObject(5, test.getSqlOrderByFild());
			pc.setObject(6, conditionStr);
			pc.setObject(7, test.getSqlAliasNeedFilds());
			System.out.println("AliasNeedFilds" + test.getSqlAliasNeedFilds());
			pc.setObject(8, test.getSqlAliasTableStr());
			System.out.println("AliasTableStr" + test.getSqlAliasTableStr());
			pc.setObject(9, test.getSqlNeedFilds());
			System.out.println("NeedFilds" + test.getSqlNeedFilds());
			pc.setObject(10, test.getSqlTableStr());
			System.out.println("TableStr" + test.getSqlTableStr());
			pc.registerOutParameter(11, OracleTypes.INTEGER);
			pc.execute();
			
			ResultSet rs = (ResultSet)pc.getObject(1);
			if(rs!=null){
				// �����ܼ�¼����
				int recordAll = ((int)pc.getObject(11));
				System.out.println("��ѯ�������� ������" + recordAll);
				// �������ҳ��
				classList = new ArrayList<ClassInfo>();
				
				while(rs.next()){
					if(rs.isFirst()){
						System.out.println("���ݿⷵ�ض������ݵ�һ��ִ��");
					}
					classList.add(new ClassInfo(
							Integer.parseInt(rs.getString("class_id")),
							Integer.parseInt(rs.getString("monitor_id")),
							Integer.parseInt(rs.getString("theTeacher_id")),
							Integer.parseInt(rs.getString("headTeacher_id")),
							rs.getString("describe"),
							rs.getString("monitor_name"),
							rs.getString("theTeacher_name"),
							rs.getString("headTeacher_name")
					));
				}
			}
			System.out.println("��ȡ��sql������" + pc.getObject(11));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (classList!=null && classList.size() == 0){
			classList = null;
		}
		return classList;  
	}
	public static void main(String[] args) {
		AdminClassDAOImp adClas = new AdminClassDAOImp();
		List<ClassInfo> classList = adClas.queryClassInfoByCondition(9, null, null);
		System.out.println(classList);
	}
}