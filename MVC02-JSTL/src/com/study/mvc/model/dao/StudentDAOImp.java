package com.study.mvc.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.tools.DButil;

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
		return null;
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
