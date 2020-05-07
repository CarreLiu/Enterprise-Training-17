package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.Course;
import com.njfu.exception.CoursenameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.CourseService;
import com.njfu.transaction.TransactionManager;

public class CourseProxy implements CourseService {

	@Override
	public List<Course> findAll() {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService  courseService = (CourseService) ObjectFactory.getObject("courseService");
		List<Course> courseList = null;
		try {
			tran.beginTransaction();
			courseList = courseService.findAll();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return courseList;
	}
	
	@Override
	public Course findByCoursename(String cname) throws CoursenameExistException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		Course course = null;
		try {
			tran.beginTransaction();
			course = courseService.findByCoursename(cname);
			tran.commit();
		} catch (CoursenameExistException e) {
			throw e;
		}
		
		return course;
	}
	
	@Override
	public void addCourse(Course course) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		try {
			tran.beginTransaction();
			courseService.addCourse(course);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}
	
	@Override
	public Course findById(Integer cid) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		Course course = null;
		try {
			tran.beginTransaction();
			course = courseService.findById(cid);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return course;
	}
	
	@Override
	public void modifyCourse(Course course) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		try {
			tran.beginTransaction();
			courseService.modifyCourse(course);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	@Override
	public void removeById(Integer cid) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		try {
			tran.beginTransaction();
			courseService.removeById(cid);
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			//需要向外部抛出异常,将异常交给action层处理
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public void modifyCourseState(Course course) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		try {
			tran.beginTransaction();
			courseService.modifyCourseState(course);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

	@Override
	public List<Course> findCoursesByCondition(Course course) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		CourseService courseService = (CourseService) ObjectFactory.getObject("courseService");
		List<Course> courses = null;
		try {
			tran.beginTransaction();
			courses = courseService.findCoursesByCondition(course);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}

		return courses;
	}

}
