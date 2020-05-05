package com.njfu.entity;

import java.io.Serializable;

public class Course implements Serializable {
	
	private Integer cid;
	
	private String cname;
	
	private String cdesc;
	
	private Integer state;

	public Course() {
		super();
	}

	public Course(Integer cid, String cname, String cdesc, Integer state) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cdesc = cdesc;
		this.state = state;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Course [cid=" + cid + ", cname=" + cname + ", cdesc=" + cdesc + ", state=" + state + "]";
	}
	
	
}
