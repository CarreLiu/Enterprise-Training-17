package com.njfu.entity;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
	
	private Integer bid;
	
	private String title;
	
	private String content;
	
	private Date createDate;
	
	private Integer sid;
	
	private Student student;

	public Blog() {
		super();
	}

	public Blog(Integer bid, String title, String content, Date createDate, Integer sid) {
		super();
		this.bid = bid;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.sid = sid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Blog [bid=" + bid + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", sid=" + sid + ", student=" + student + "]";
	}
	
}
