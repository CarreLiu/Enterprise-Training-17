package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.Blog;
import com.njfu.factory.ObjectFactory;
import com.njfu.service.BlogService;
import com.njfu.transaction.TransactionManager;

public class BlogProxy implements BlogService {
	
	@Override
	public List<Blog> findAll() {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		BlogService  blogService = (BlogService) ObjectFactory.getObject("blogService");
		List<Blog> blogList = null;
		try {
			tran.beginTransaction();
			blogList = blogService.findAll();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return blogList;
	}
	
	@Override
	public Blog findById(Integer bid) {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("transaction");
		BlogService blogService = (BlogService) ObjectFactory.getObject("blogService");
		Blog blog = null;
		try {
			tran.beginTransaction();
			blog = blogService.findById(bid);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return blog;
	}
	
	@Override
	public void removeById(Integer bid) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		BlogService blogService = (BlogService) ObjectFactory.getObject("blogService");
		try {
			tran.beginTransaction();
			blogService.removeById(bid);
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			//需要向外部抛出异常,将异常交给action层处理
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public List<Blog> findBlogByStudent(Integer sid) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		BlogService  blogService = (BlogService) ObjectFactory.getObject("blogService");
		List<Blog> blogList = null;
		try {
			tran.beginTransaction();
			blogList = blogService.findBlogByStudent(sid);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
		
		return blogList;
	}
	
	@Override
	public void modifyBlog(Blog blog) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		BlogService blogService = (BlogService) ObjectFactory.getObject("blogService");
		try {
			tran.beginTransaction();
			blogService.modifyBlog(blog);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}
	
	@Override
	public void addBlog(Blog blog) {
		TransactionManager tran= (TransactionManager) ObjectFactory.getObject("transaction");
		BlogService blogService = (BlogService) ObjectFactory.getObject("blogService");
		try {
			tran.beginTransaction();
			blogService.addBlog(blog);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}
	}

}
