package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.Grade;
import com.njfu.exception.GradenameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.GradeService;
import com.njfu.transaction.TransactionManager;

public class GradeProxy implements GradeService {

	@Override
	public List<Grade> findAll() {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService  gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		List<Grade> gradeList = null;
		try {
			tran.beginTransaction();
			gradeList = gradeService.findAll();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		return gradeList;
	}
	
	@Override
	public Grade findByGradename(String gname) throws GradenameExistException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		Grade grade = null;
		try {
			tran.beginTransaction();
			grade = gradeService.findByGradename(gname);
			tran.commit();
		} catch (GradenameExistException e) {
			throw e;
		}
		
		return grade;
	}
	
	@Override
	public void addGrade(Grade grade) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		try {
			tran.beginTransaction();
			gradeService.addGrade(grade);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}
	
	@Override
	public Grade findById(Integer gid) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		Grade grade = null;
		try {
			tran.beginTransaction();
			grade = gradeService.findById(gid);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return grade;
	}
	
	@Override
	public void modifyGrade(Grade grade) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		try {
			tran.beginTransaction();
			gradeService.modifyGrade(grade);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	@Override
	public void removeById(Integer gid) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		try {
			tran.beginTransaction();
			gradeService.removeById(gid);
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			//需要向外部抛出异常,将异常交给action层处理
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public void modifyGradeState(Grade grade) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		try {
			tran.beginTransaction();
			gradeService.modifyGradeState(grade);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	@Override
	public List<Grade> findGradesByCondition(Grade grade) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		GradeService gradeService = (GradeService) ObjectFactory.getObject("gradeService");
		List<Grade> grades = null;
		try {
			tran.beginTransaction();
			grades = gradeService.findGradesByCondition(grade);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}

		return grades;
	}

}
