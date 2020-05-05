package com.njfu.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.njfu.constant.Constant;
import com.njfu.entity.Course;
import com.njfu.entity.vo.PageInfo;
import com.njfu.exception.CoursenameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.CourseService;

public class CourseAction {
	public String toCourseManager(HttpServletRequest req, HttpServletResponse resp) {
		return "toCourseManager";
	}
	
	public void findAllCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		String pageNoStr = req.getParameter("pageNo");
		String pageSizeStr = req.getParameter("pageSize");
		int pageNo = 0;
		int pageSize = 0;
		if (pageNoStr == null) {
			pageNo = Constant.PAGE_NO;
		}
		else {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		else {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		PageHelper.startPage(pageNo, pageSize);
		List<Course> courses = courseProxy.findAll();
		PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
	
	//校验课程名称是否已经存在
	public void findByCoursename(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		String cname = req.getParameter("cname");
		String primeCname = req.getParameter("primeCname");
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//为防止修改课程信息的时候,课程名称不变
			if (primeCname == null || !primeCname.equals(cname)) {
				courseProxy.findByCoursename(cname);
			}
			map.put("valid", true);//设置valid属性,在false时,输出message所对应的值
		} catch (CoursenameExistException e) {
			// TODO Auto-generated catch block
			map.put("valid", false);
			map.put("message", e.getMessage());
		}
		//返回2个值:message,是否输出该消息:valid
		resp.getWriter().print(JSON.toJSON(map));
	}
	
	//添加新学员
	public String addCourse(HttpServletRequest req, HttpServletResponse resp) {
		String cname = req.getParameter("cname");
		String cdesc = req.getParameter("cdesc");
		Integer state = 1;	//默认启用
		
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		Course course = new Course(null, cname, cdesc, state);
		courseProxy.addCourse(course);
		
		return "toCourseManager";
	}
	
	//显示修改课程页面
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		Integer cid = Integer.valueOf(req.getParameter("cid"));
		Course course = courseProxy.findById(cid);
		resp.getWriter().print(JSON.toJSON(course));
	}
	
	// 修改课程
	public String modifyCourse(HttpServletRequest req, HttpServletResponse resp) {
		Integer cid = Integer.valueOf(req.getParameter("cid"));
		String cname = req.getParameter("cname");
		String cdesc = req.getParameter("cdesc");
		
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		Course course = new Course(cid, cname, cdesc, null);
		courseProxy.modifyCourse(course);

		return "toCourseManager";
	}
	
	//删除课程
	public String deleteCourse(HttpServletRequest req, HttpServletResponse resp) {
		Integer cid = Integer.valueOf(req.getParameter("cid"));

		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		try {
			courseProxy.removeById(cid);
			req.setAttribute("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", "删除失败");
		}

		return "toCourseManager";
	}
	
	//修改课程状态
	public void modifyCourseState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer cid = Integer.valueOf(req.getParameter("cid"));
		Integer state = Integer.valueOf(req.getParameter("state"));
		CourseService courseProxy = (CourseService) ObjectFactory.getObject("courseProxy");
		Course course = new Course(cid, null, null, state);
		courseProxy.modifyCourseState(course);
	}
}
