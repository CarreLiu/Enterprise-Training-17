package com.njfu.service;

import java.util.List;

import com.njfu.entity.Course;
import com.njfu.exception.CoursenameExistException;

public interface CourseService {

	List<Course> findAll();

	public Course findByCoursename(String cname) throws CoursenameExistException;
	
	public void addCourse(Course course);
	
	public Course findById(Integer cid);

	public void modifyCourse(Course course);

	public void removeById(Integer cid);

	public void modifyCourseState(Course course);

	List<Course> findCoursesByCondition(Course course);
}
