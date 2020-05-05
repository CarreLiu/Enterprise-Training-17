package com.njfu.entity;

import java.io.Serializable;

public class Student implements Serializable {
	
	private Integer sid;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private Integer gender;
	
	private Integer age;
	
	private Integer gid;
	
	private Integer cid;
	
	private Grade grade;
	
	private Course course;

	public Student() {
		super();
	}

	public Student(Integer sid, String username, String password, String name, Integer gender, Integer age, Integer gid,
			Integer cid) {
		super();
		this.sid = sid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.gid = gid;
		this.cid = cid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", username=" + username + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", age=" + age + ", gid=" + gid + ", cid=" + cid + ", grade=" + grade + ", course=" + course
				+ "]";
	}
	
	
}
