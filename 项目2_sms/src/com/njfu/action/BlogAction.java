package com.njfu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.njfu.constant.Constant;
import com.njfu.entity.Blog;
import com.njfu.entity.Student;
import com.njfu.entity.vo.PageInfo;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.BlogService;

public class BlogAction {
	public String toBlogManager(HttpServletRequest req, HttpServletResponse resp) {
		return "toBlogManager";
	}
	
	public void findAllBlog(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
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
		List<Blog> blogs = blogProxy.findAll();
		PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
	
	//管理员的日志详情
	public void blogDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
		Integer bid = Integer.valueOf(req.getParameter("bid"));
		Blog blog = blogProxy.findById(bid);
		
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(blog));
	}
	
	//删除日志
	public String deleteBlog(HttpServletRequest req, HttpServletResponse resp) {
		Integer bid = Integer.valueOf(req.getParameter("bid"));

		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
		try {
			blogProxy.removeById(bid);
			req.setAttribute("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", "删除失败");
		}

		return "toBlogManager";
	}
	
	//学生用户的日志
	public String toStudentBlog(HttpServletRequest req, HttpServletResponse resp) {
		
		return "toStudentBlog";
	}
	
	//删除日志
	public String deleteBlog2(HttpServletRequest req, HttpServletResponse resp) {
		Integer bid = Integer.valueOf(req.getParameter("bid"));

		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
		try {
			blogProxy.removeById(bid);
			req.setAttribute("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", "删除失败");
		}

		return "toBlogManager2";
	}
	
	public void findBlogByStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Student student = (Student) req.getSession().getAttribute("user");
		
		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
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
		List<Blog> blogs = blogProxy.findBlogByStudent(student.getSid());
		PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(pageInfo));
	}
	
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
		Integer bid = Integer.valueOf(req.getParameter("bid"));
		
		Blog blog = blogProxy.findById(bid);
		resp.setContentType(Constant.CONTENT_TYPE);
		resp.getWriter().print(JSON.toJSON(blog));
	}
	
	public String modifyBlog(HttpServletRequest req, HttpServletResponse resp) {
		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
		Integer bid = Integer.valueOf(req.getParameter("bid"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Blog blog = new Blog();
		blog.setBid(bid);
		blog.setTitle(title);
		blog.setContent(content);
		
		blogProxy.modifyBlog(blog);
		
		return "toStudentBlog";
	}
	
	public String toNewBlog(HttpServletRequest req, HttpServletResponse resp) {
		
		return "toNewBlog";
	}
	
	public String addBlog(HttpServletRequest req, HttpServletResponse resp){
		BlogService blogProxy = (BlogService) ObjectFactory.getObject("blogProxy");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Student student = (Student) req.getSession().getAttribute("user");
		Integer sid = student.getSid();
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setContent(content);
		blog.setSid(sid);
		blogProxy.addBlog(blog);

		return "toStudentBlog";
	}
	
	
}
