package com.njfu.service;

import java.util.List;

import com.njfu.entity.Blog;

public interface BlogService {
	
	public List<Blog> findAll();
	
	public Blog findById(Integer bid);
	
	public void removeById(Integer bid);

	public List<Blog> findBlogByStudent(Integer sid);
	
	public void modifyBlog(Blog blog);

	public void addBlog(Blog blog);

}
