package com.study.mvc.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;

import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.tools.DButil;
import com.study.mvc.tools.PageManager;

public class StudentDAOImp implements IStudentDAO {
	private DButil dbutil;
	
    public StudentDAOImp(){
    	dbutil=new DButil();
    }
    
	@Override
	public StudentInfo getStudentById(int id) {
		StudentInfo stu=null;
		try {
			String sql="select s.id,s.name,s.sex,s.age,s.gradefrom from StudentInfo s where s.id="+id;
			ResultSet rs = dbutil.find(sql);
			if(rs.next()){
				stu=new StudentInfo();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				stu.setGradeFrom(rs.getString("gradefrom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stu;
	}

	@Override
	public List<StudentInfo> getStudentsByName(String name) {
        if(name==null||name.equals("")){
        	return this.getStudentAll();
        }
        
		List<StudentInfo> list=null;
		ResultSet rs = null;
		try {
			String sql="{?=call getPageRows(?,?,?,?)}";
			try {
				CallableStatement prepareCall = dbutil.getConnection2().prepareCall(sql);
				prepareCall.registerOutParameter(1, OracleTypes.CURSOR);
				prepareCall.registerOutParameter(5, OracleTypes.NUMBER);
				//2  最大页面表示Page行数
				prepareCall.setInt(2, PageManager.pageMaxRows);
				//3 当前页码
				prepareCall.setInt(3, PageManager.currentPageNo);				
				//4 姓名
				prepareCall.setString(4, name);
				prepareCall.execute();
				
				if(PageManager.initFlg==false){
					PageManager.initPageInfo(8, 1, prepareCall.getInt(5));
				}
				rs=(ResultSet)prepareCall.getObject(1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			while(rs.next()){
				if(rs.isFirst()){
					list=new ArrayList<StudentInfo>();
				}
				StudentInfo stu=new StudentInfo();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				stu.setGradeFrom(rs.getString("gradefrom"));
				list.add(stu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentInfo> getStudentsBySex(String sex) {
		return null;
	}

	@Override
	public List<StudentInfo> getStudentsByAgeRange(int startAge, int endAge) {
		return null;
	}

	@Override
	public List<StudentInfo> getStudentsByGradeFrom(String gradeFrom) {
		return null;
	}

	@Override
	public List<StudentInfo> getStudentsByCondition(int id, String name,
			String sex, int startAge, int endAge, String gradeFrom) {
		return null;
	}

	@Override
	public List<StudentInfo> getStudentAll() {
		List<StudentInfo> list=null;
		try {
			String sql="select s.id,s.name,s.sex,s.age,s.gradefrom from StudentInfo s order by s.id";
			ResultSet rs = dbutil.find(sql);
			while(rs.next()){
				if(rs.isFirst()){
					list=new ArrayList<StudentInfo>();
				}
				StudentInfo stu=new StudentInfo();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				stu.setGradeFrom(rs.getString("gradefrom"));
				list.add(stu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(StudentInfo stu) {
		String sql="insert into StudentInfo values(";
		sql+=stu.getId()+",'"+stu.getName()+"','"+stu.getSex()+"',"+stu.getAge()+",'"+stu.getGradeFrom()+"'";
		sql+=")";
		return dbutil.executeUpdate(sql);
	}

	@Override
	public int save(StudentInfo stu) {
		String sql="update StudentInfo "
				+ " set name='"+stu.getName()
				+ "',   sex='"+stu.getSex()
				+"',    age="+stu.getAge()
				+",     gradefrom='"+stu.getGradeFrom()+"'"
				+" where id="+stu.getId();
		
		System.out.println("Dao sql000:"+sql);
		return dbutil.executeUpdate(sql);
	}

	@Override
	public int delete(int id) {
		String sql="delete StudentInfo "
				+" where id="+id;
		System.out.println("Dao sql del:"+sql);
		return dbutil.executeUpdate(sql);
	}
	
}
