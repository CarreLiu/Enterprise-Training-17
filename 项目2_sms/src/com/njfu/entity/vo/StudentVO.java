package com.njfu.entity.vo;

import java.io.Serializable;

//该类用来封装传递过来的表单数据,类似于formbean
public class StudentVO implements Serializable {
	private String name;
	private Integer minAge;
	private Integer maxAge;
	private Integer gender;
	private Integer gid;
	private Integer cid;
	public StudentVO() {
		super();
	}
	public StudentVO(String name, Integer minAge, Integer maxAge, Integer gender, Integer gid, Integer cid) {
		super();
		this.name = name;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.gender = gender;
		this.gid = gid;
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "StudentVO [name=" + name + ", minAge=" + minAge + ", maxAge=" + maxAge + ", gender=" + gender + ", gid="
				+ gid + ", cid=" + cid + "]";
	}
	
}
