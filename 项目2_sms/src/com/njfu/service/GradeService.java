package com.njfu.service;

import java.util.List;

import com.njfu.entity.Grade;
import com.njfu.exception.GradenameExistException;

public interface GradeService {

	List<Grade> findAll();
	
	public Grade findByGradename(String gname) throws GradenameExistException;
	
	public void addGrade(Grade grade);
	
	public Grade findById(Integer gid);

	public void modifyGrade(Grade grade);

	public void removeById(Integer gid);

	public void modifyGradeState(Grade grade);

	List<Grade> findGradesByCondition(Grade grade);
	
}
