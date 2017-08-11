package com.sims.mvc.model.service;

import java.util.List;

import com.sims.mvc.model.bean.Student;
import com.sims.mvc.model.dao.IStudentFindDAO;
import com.sims.mvc.model.dao.IStudentUpdateDAO;
import com.sims.mvc.model.dao.StudentFindDAOImp;
import com.sims.mvc.model.dao.StudentUpdateDAOImp;

public class StudentServiceImp implements IStudentService{
	private IStudentFindDAO stuFind;
	private IStudentUpdateDAO stuUpdate;
	
	public StudentServiceImp() {
//		stuUpdate = new StudentUpdateDAOImp();
//		stuFind =new StudentFindDAOImp();
	}
	
	public IStudentFindDAO getStuFind() {
		return stuFind;
	}

	public void setStuFind(IStudentFindDAO stuFind) {
		this.stuFind = stuFind;
	}

	public IStudentUpdateDAO getStuUpdate() {
		return stuUpdate;
	}

	public void setStuUpdate(IStudentUpdateDAO stuUpdate) {
		this.stuUpdate = stuUpdate;
	}

	@Override
	public int addStudent(Student student, String pwd) {
		return stuUpdate.addStudent(student, pwd);
	}

	@Override
	public int deleteStuent(Student student) {
		return stuUpdate.deleteStuent(student);
	}

	@Override
	public int modifyStudent(Student student) {
		return stuUpdate.modifyStudent(student);
	}

	@Override
	public Student findStudentById(String id) {
		return stuFind.findStudentById(id);
	}

	@Override
	public List<Student> findStudentByName(String name) {
		return stuFind.findStudentByName(name);
	}

	@Override
	public List<Student> findStudentBySex(String sex) {
		return stuFind.findStudentBySex(sex);
	}

	@Override
	public List<Student> findStudentByAgeRange(int startAge, int endAge) {
		return stuFind.findStudentByAgeRange(startAge, endAge);
	}

	@Override
	public List<Student> findStudentByGradFrom(String gradFrom) {
		return stuFind.findStudentByGradFrom(gradFrom);
	}

	@Override
	public List<Student> findStudentByTelphone(int telephone) {
		return stuFind.findStudentByTelphone(telephone);
	}

	@Override
	public List<Student> findStudentByAddress(String address) {
		return stuFind.findStudentByAddress(address);
	}

	@Override
	public List<Student> findStudentByIDCard(String idCard) {
		return stuFind.findStudentByIDCard(idCard);
	}

	@Override
	public List<Student> findStudentByEmail(String email) {
		return stuFind.findStudentByEmail(email);
	}

	@Override
	public List<Student> findStudentByTeamID(String teamId,String classid) {
		return stuFind.findStudentByTeamID(teamId,classid);
	}

	@Override
	public List<Student> findStudentByClassID(String classId) {
		return stuFind.findStudentByClassID(classId);
	}

	@Override
	public List<Student> findStudentByCondition(String id, String name,
			String sex, int startAge, int endAge, String gradFrom,
			int telephone, String address, String idCard, String email,
			String teamId, String classId) {
		return stuFind.findStudentByCondition(id, name, sex, startAge, endAge,
				gradFrom, telephone, address, idCard, email, teamId, classId);
	}

	@Override
	public int modifyStudentPWD(String id, String pwd) {
		return stuUpdate.modifyStudentPWD(id, pwd);
	}

	@Override
	public List<Student> findStudentAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findStudentByIsAdmin() {
		return stuFind.findStudentByIsAdmin();
	}

}
