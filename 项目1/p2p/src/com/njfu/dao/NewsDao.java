package com.njfu.dao;

import java.util.List;

import com.njfu.entity.News;
import com.njfu.exception.DataAccessException;
import com.njfu.form.NewsForm;

public interface NewsDao {
	public List<News> selectNews();

	public void insertNews(News news) throws DataAccessException;

	public News selectByNewsId(Integer newsId);

	public void updateNews(News news) throws DataAccessException;

	public void delNews(Integer newsId) throws DataAccessException;

	public List<News> selectByNewsTitle(String newsTitle);

	public List<News> selectByNewsIdAndTitle(Integer newsId, String newsTitle);

	public List<News> selectHeadingNews();

	public List<News> selectCommonNews();
}
