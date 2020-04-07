package com.njfu.service;

import java.util.List;

import com.njfu.entity.News;
import com.njfu.form.NewsForm;

public interface NewsService {
	//news
	public List<News> findNews();

	public void addNews(News news) throws Exception;

	public News findByNewsId(Integer newsId);

	public void modifyNews(News news) throws Exception;

	public void removeNews(Integer newsId) throws Exception;

	public List<News> findByNewsTitle(String newsTitle);

	public List<News> findByNewsIdAndTitle(Integer newsId, String newsTitle);

	public List<News> findHeadingNews();

	public List<News> findCommonNews();
	
}
