package com.study.mvc.model.service;

import java.util.List;

import com.study.mvc.model.bean.StudentInfo;
import com.study.mvc.model.dao.IStudentDAO;
import com.study.mvc.model.dao.StudentDAOImp;

public class StudentServiceImp implements IStudentService {
	private IStudentDAO studentDao;
    public StudentServiceImp(){
    	studentDao=new StudentDAOImp();
    }
	@Override
	public StudentInfo getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	@Override
	public List<StudentInfo> getStudentsByName(String name) {
		return studentDao.getStudentsByName(name);
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
		return studentDao.getStudentAll();
	}

	@Override
	public int insert(StudentInfo stu) {
		return 0;
	}

	@Override
	public int save(StudentInfo stu) {
		return studentDao.save(stu);
	}

	@Override
	public int delete(int id) {
		return studentDao.delete(id);
	}

}
