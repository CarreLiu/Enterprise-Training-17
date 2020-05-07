package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Course;

public interface CourseDao {

	List<Course> selectAll();

	Integer selectIdByName(String stringCellValue);
	
	public Course selectByCoursename(String cname);
	
	public void insertCourse(Course course);
	
	public Course selectById(Integer cid);

	public void updateCourse(Course course);

	public void deleteCourse(Integer cid);

	public void updateCourseState(Course course);

	public List<Course> selectCoursesByCondition(Course course);
}
