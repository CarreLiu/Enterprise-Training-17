package com.njfu.service.impl;

import java.util.List;

import com.njfu.dao.GradeDao;
import com.njfu.entity.Grade;
import com.njfu.exception.GradenameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.GradeService;

public class GradeServiceImpl implements GradeService {

	@Override
	public List<Grade> findAll() {
		GradeDao gradeDao = (GradeDao)ObjectFactory.getObject("gradeDao");
		List<Grade> gradeList = gradeDao.selectAll();
		
		return gradeList;
	}
	
	@Override
	public Grade findByGradename(String gname) throws GradenameExistException {
		GradeDao gradeDao = (GradeDao) ObjectFactory.getObject("gradeDao");
		Grade grade = gradeDao.selectByGradename(gname);
		if (grade != null) {
			throw new GradenameExistException("班级名称(" + gname + ")已经被占用");
		}
		
		return grade;
	}
	
	@Override
	public void addGrade(Grade grade) {
		GradeDao gradeDao = (GradeDao) ObjectFactory.getObject("gradeDao");
		gradeDao.insertGrade(grade);
	}
	
	@Override
	public Grade findById(Integer gid) {
		GradeDao gradeDao = (GradeDao) ObjectFactory.getObject("gradeDao");
		Grade grade = gradeDao.selectById(gid);
		
		return grade;
	}
	
	@Override
	public void modifyGrade(Grade grade) {
		GradeDao gradeDao = (GradeDao)ObjectFactory.getObject("gradeDao");
		gradeDao.updateGrade(grade);
	}

	@Override
	public void removeById(Integer gid) {
		GradeDao gradeDao = (GradeDao)ObjectFactory.getObject("gradeDao");
		gradeDao.deleteGrade(gid);
	}

	@Override
	public void modifyGradeState(Grade grade) {
		GradeDao gradeDao = (GradeDao)ObjectFactory.getObject("gradeDao");
		gradeDao.updateGradeState(grade);
	}

	@Override
	public List<Grade> findGradesByCondition(Grade grade) {
		GradeDao gradeDao = (GradeDao) ObjectFactory.getObject("gradeDao");
		List<Grade> grades = gradeDao.selectGradesByCondition(grade);
		
		return grades;
	}

}
