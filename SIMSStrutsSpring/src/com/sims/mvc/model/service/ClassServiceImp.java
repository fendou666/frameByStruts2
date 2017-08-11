package com.sims.mvc.model.service;

import java.util.List;

import com.sims.mvc.model.bean.CClass;
import com.sims.mvc.model.dao.ClassDAOImp;
import com.sims.mvc.model.dao.IClassDAO;

public class ClassServiceImp implements IClassService {
	IClassDAO icd;
	
	public ClassServiceImp(){
		//资源准备
	}
	public IClassDAO getIcd() {
		return icd;
	}
	public void setIcd(IClassDAO icd) {
		this.icd = icd;
	}

	@Override
	public CClass findClassById(String id) {
		// TODO Auto-generated method stub
		return icd.findClassById(id);
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
		// TODO Auto-generated method stub
		return icd.addClass(cclass);
	}

	@Override
	public int modifyClass(CClass cclass) {
		// TODO Auto-generated method stub
		return icd.modifyClass(cclass);
	}

	@Override
	public int deleteClass(String cclassId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CClass> getClassesAll() {
		// TODO Auto-generated method stub
		return icd.getClassesAll();
	}

}
