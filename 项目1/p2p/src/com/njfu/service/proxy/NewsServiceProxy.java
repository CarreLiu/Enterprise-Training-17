package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.News;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.NewsForm;
import com.njfu.service.NewsService;
import com.njfu.transaction.TransactionManager;

public class NewsServiceProxy implements NewsService {
	
	public List<News> findNews() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		List<News> list = null;
		try {
			tran.beginTransaction();
			list = newsService.findNews();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}

	public void addNews(News news) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		try {
			tran.beginTransaction();
			newsService.addNews(news);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public News findByNewsId(Integer newsId) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		News news = null;
		try {
			tran.beginTransaction();
			news = newsService.findByNewsId(newsId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return news;
	}

	public void modifyNews(News news) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		try {
			tran.beginTransaction();
			newsService.modifyNews(news);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}
	
	public void removeNews(Integer newsId) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		try {
			tran.beginTransaction();
			newsService.removeNews(newsId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException("新闻删除异常");
		}
	}

	public List<News> findByNewsTitle(String newsTitle) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		List<News> newsList = null;
		try {
			tran.beginTransaction();
			newsList = newsService.findByNewsTitle(newsTitle);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return newsList;
	}

	public List<News> findByNewsIdAndTitle(Integer newsId, String newsTitle) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		List<News> newsList = null;
		try {
			tran.beginTransaction();
			newsList = newsService.findByNewsIdAndTitle(newsId, newsTitle);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return newsList;
	}

	public List<News> findHeadingNews() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		List<News> headingNewsList = null;
		try {
			tran.beginTransaction();
			headingNewsList = newsService.findHeadingNews();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return headingNewsList;
	}

	public List<News> findCommonNews() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		NewsService	newsService=(NewsService) ObjectFactory.getObject("newsService");
		List<News> commonNewsList = null;
		try {
			tran.beginTransaction();
			commonNewsList = newsService.findCommonNews();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return commonNewsList;
	}

}
