package com.njfu.service.impl;

import java.util.Date;
import java.util.List;

import com.njfu.dao.NewsDao;
import com.njfu.entity.News;
import com.njfu.exception.NewsInUseException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.NewsForm;
import com.njfu.service.NewsService;

public class NewsServiceImpl implements NewsService {
	public List<News> findNews() {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		List<News> list = newsDao.selectNews();
		for (News news : list) {
			String newsTitle = news.getNewsTitle();
			if (newsTitle != null && newsTitle.length() > 10) {
				news.setNewsTitle(newsTitle.substring(0, 10) + "...");
			}
		}
		
		return list;
	}

	public void addNews(News news) throws Exception {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		news.setCreateAt(new Date());
		newsDao.insertNews(news);
	}

	public News findByNewsId(Integer newsId) {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		News news = newsDao.selectByNewsId(newsId);
		
		return news;
	}

	public void modifyNews(News news) throws Exception {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		news.setCreateAt(new Date());
		newsDao.updateNews(news);
	}

	public void removeNews(Integer newsId) throws Exception {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		try {
			newsDao.delNews(newsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NewsInUseException();
		}
	}

	public List<News> findByNewsTitle(String newsTitle) {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		List<News> newsList = newsDao.selectByNewsTitle(newsTitle);
		
		return newsList;
	}

	public List<News> findByNewsIdAndTitle(Integer newsId, String newsTitle) {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		List<News> newsList = newsDao.selectByNewsIdAndTitle(newsId, newsTitle);
		
		return newsList;
	}

	public List<News> findHeadingNews() {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		List<News> headingNewsList = newsDao.selectHeadingNews();
		return headingNewsList;
	}

	public List<News> findCommonNews() {
		NewsDao newsDao = (NewsDao) ObjectFactory.getObject("newsDao");
		List<News> commonNewsList = newsDao.selectCommonNews();
		return commonNewsList;
	}

}
