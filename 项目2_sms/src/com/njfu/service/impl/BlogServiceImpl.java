package com.njfu.service.impl;

import java.util.Date;
import java.util.List;

import com.njfu.dao.BlogDao;
import com.njfu.entity.Blog;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.BlogService;

public class BlogServiceImpl implements BlogService {
	
	@Override
	public List<Blog> findAll() {
		BlogDao blogDao = (BlogDao)ObjectFactory.getObject("blogDao");
		List<Blog> blogList = blogDao.selectAll();
		
		return blogList;
	}
	
	@Override
	public Blog findById(Integer bid) {
		BlogDao blogDao = (BlogDao) ObjectFactory.getObject("blogDao");
		Blog blog = blogDao.selectById(bid);
		
		return blog;
	}
	
	@Override
	public void removeById(Integer bid) {
		BlogDao blogDao = (BlogDao)ObjectFactory.getObject("blogDao");
		blogDao.deleteBlog(bid);
	}

	@Override
	public List<Blog> findBlogByStudent(Integer sid) {
		BlogDao blogDao = (BlogDao)ObjectFactory.getObject("blogDao");
		List<Blog> blogList = blogDao.selectBlogByStudent(sid);
		
		return blogList;
	}
	
	@Override
	public void modifyBlog(Blog blog) {
		BlogDao blogDao = (BlogDao)ObjectFactory.getObject("blogDao");
		blogDao.updateBlog(blog);
	}
	
	@Override
	public void addBlog(Blog blog) {
		BlogDao blogDao = (BlogDao)ObjectFactory.getObject("blogDao");
		blog.setCreateDate(new Date());
		blogDao.insertBlog(blog);
	}

}
