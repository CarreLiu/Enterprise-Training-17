package com.njfu.entity.vo;

public class StudentVO2 {
	
	private Integer sid;
	
	private String username;
	
	private String password;
	
	private String newPass;

	public StudentVO2() {
		super();
	}

	public StudentVO2(Integer sid, String username, String password, String newPass) {
		super();
		this.sid = sid;
		this.username = username;
		this.password = password;
		this.newPass = newPass;
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

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "StudentVO2 [sid=" + sid + ", username=" + username + ", password=" + password + ", newPass=" + newPass
				+ "]";
	}
	
}
