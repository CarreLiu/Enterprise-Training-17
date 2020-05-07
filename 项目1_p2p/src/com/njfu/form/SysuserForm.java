package com.njfu.form;

import org.apache.struts.action.ActionForm;

import com.njfu.entity.Sysuser;

public class SysuserForm extends ActionForm {
	private Integer id;
	private String userName;
	private String loginName;
	private String loginPassword;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	@Override
	public String toString() {
		return "SysuserForm [id=" + id + ", userName=" + userName + ", loginName=" + loginName + ", loginPassword="
				+ loginPassword + "]";
	}
	
	
}
