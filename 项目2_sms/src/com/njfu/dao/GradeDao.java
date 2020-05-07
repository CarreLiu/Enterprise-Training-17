package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Grade;

public interface GradeDao {

	List<Grade> selectAll();

	Integer selectIdByName(String stringCellValue);

	public Grade selectByGradename(String gname);
	
	public void insertGrade(Grade grade);
	
	public Grade selectById(Integer gid);

	public void updateGrade(Grade grade);

	public void deleteGrade(Integer gid);

	public void updateGradeState(Grade grade);

	public List<Grade> selectGradesByCondition(Grade grade);

}
