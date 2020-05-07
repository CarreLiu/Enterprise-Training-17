package com.njfu.service.impl;

import java.util.List;

import com.njfu.dao.CourseDao;
import com.njfu.entity.Course;
import com.njfu.exception.CoursenameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.CourseService;

public class CourseServiceImpl implements CourseService {

	@Override
	public List<Course> findAll() {
		CourseDao courseDao  = (CourseDao)ObjectFactory.getObject("courseDao");
		List<Course> courseList = courseDao.selectAll();
		
		return courseList;
	}

	@Override
	public Course findByCoursename(String cname) throws CoursenameExistException {
		CourseDao courseDao = (CourseDao) ObjectFactory.getObject("courseDao");
		Course course = courseDao.selectByCoursename(cname);
		if (course != null) {
			throw new CoursenameExistException("课程名称(" + cname + ")已经被占用");
		}
		
		return course;
	}
	
	@Override
	public void addCourse(Course course) {
		CourseDao courseDao = (CourseDao) ObjectFactory.getObject("courseDao");
		courseDao.insertCourse(course);
	}
	
	@Override
	public Course findById(Integer cid) {
		CourseDao courseDao = (CourseDao) ObjectFactory.getObject("courseDao");
		Course course = courseDao.selectById(cid);
		
		return course;
	}
	
	@Override
	public void modifyCourse(Course course) {
		CourseDao courseDao = (CourseDao)ObjectFactory.getObject("courseDao");
		courseDao.updateCourse(course);
	}

	@Override
	public void removeById(Integer cid) {
		CourseDao courseDao = (CourseDao)ObjectFactory.getObject("courseDao");
		courseDao.deleteCourse(cid);
	}

	@Override
	public void modifyCourseState(Course course) {
		CourseDao courseDao = (CourseDao)ObjectFactory.getObject("courseDao");
		courseDao.updateCourseState(course);
	}

	@Override
	public List<Course> findCoursesByCondition(Course course) {
		CourseDao courseDao = (CourseDao) ObjectFactory.getObject("courseDao");
		List<Course> courses = courseDao.selectCoursesByCondition(course);
		
		return courses;
	}
}
