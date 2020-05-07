package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Blog;

public interface BlogDao {

	public List<Blog> selectAll();

	public Blog selectById(Integer bid);
	
	public void deleteBlog(Integer bid);

	public List<Blog> selectBlogByStudent(Integer sid);
	
	public void updateBlog(Blog blog);

	public void insertBlog(Blog blog);

}
