package com.njfu.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/*
 * 定义一个PageInfo类，用于支持bootstrap分页， bootstrap分页需要得到两个值：
 * 1：total
 * 2: rows
 */
public class PageInfo<T> implements Serializable {
	
	//总记录数
	private long total;
	
	//每一页的数据
	private List<T> rows;
	
	public PageInfo() {
		// TODO Auto-generated constructor stub
	}
	//包装Page对象
	public PageInfo(List<T> list) {
		// TODO Auto-generated constructor stub
		Page  page=(Page)list;
		this.total = page.getTotal();
		this.rows=page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	

}
