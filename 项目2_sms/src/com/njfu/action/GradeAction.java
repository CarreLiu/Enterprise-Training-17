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
import com.njfu.entity.Grade;
import com.njfu.entity.vo.PageInfo;
import com.njfu.exception.GradenameExistException;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.GradeService;

public class GradeAction {
	public String toGradeManager(HttpServletRequest req, HttpServletResponse resp) {
		
		return "toGradeManager";
	}
	
	public void findAllGrade(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
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
		List<Grade> grades = gradeProxy.findAll();
		PageInfo<Grade> pageInfo = new PageInfo<Grade>(grades);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
	
	//校验班级名称是否已经存在
	public void findByGradename(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		String gname = req.getParameter("gname");
		String primeGname = req.getParameter("primeGname");
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//为防止修改班级信息的时候,班级名称不变
			if (primeGname == null || !primeGname.equals(gname)) {
				gradeProxy.findByGradename(gname);
			}
			map.put("valid", true);//设置valid属性,在false时,输出message所对应的值
		} catch (GradenameExistException e) {
			// TODO Auto-generated catch block
			map.put("valid", false);
			map.put("message", e.getMessage());
		}
		//返回2个值:message,是否输出该消息:valid
		resp.getWriter().print(JSON.toJSON(map));
	}
	
	//添加新学员
	public String addGrade(HttpServletRequest req, HttpServletResponse resp) {
		String gname = req.getParameter("gname");
		String gdesc = req.getParameter("gdesc");
		Integer state = 1;	//默认启用
		
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		Grade grade = new Grade(null, gname, gdesc, state);
		gradeProxy.addGrade(grade);
		
		return "toGradeManager";
	}
	
	//显示修改班级页面
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		Integer gid = Integer.valueOf(req.getParameter("gid"));
		Grade grade = gradeProxy.findById(gid);
		resp.getWriter().print(JSON.toJSON(grade));
	}
	
	// 修改班级
	public String modifyGrade(HttpServletRequest req, HttpServletResponse resp) {
		Integer gid = Integer.valueOf(req.getParameter("gid"));
		String gname = req.getParameter("gname");
		String gdesc = req.getParameter("gdesc");
		
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		Grade grade = new Grade(gid, gname, gdesc, null);
		gradeProxy.modifyGrade(grade);

		return "toGradeManager";
	}
	
	//删除班级
	public String deleteGrade(HttpServletRequest req, HttpServletResponse resp) {
		Integer gid = Integer.valueOf(req.getParameter("gid"));

		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		try {
			gradeProxy.removeById(gid);
			req.setAttribute("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", "删除失败");
		}

		return "toGradeManager";
	}
	
	//修改班级状态
	public void modifyGradeState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer gid = Integer.valueOf(req.getParameter("gid"));
		Integer state = Integer.valueOf(req.getParameter("state"));
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		Grade grade = new Grade(gid, null, null, state);
		gradeProxy.modifyGradeState(grade);
	}
	
	//按条件查询班级信息
	public void findByCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		
		String gname = req.getParameter("gname");
		
		Grade grade = new Grade();
		if (!"".equals(gname) && gname != null) {
			grade.setGname("%" + gname + "%");
		}
		
		GradeService gradeProxy = (GradeService) ObjectFactory.getObject("gradeProxy");
		PageHelper.startPage(pageNo, pageSize);
		List<Grade> grades = gradeProxy.findGradesByCondition(grade);
		PageInfo<Grade> pageInfo = new PageInfo<Grade>(grades);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
}
