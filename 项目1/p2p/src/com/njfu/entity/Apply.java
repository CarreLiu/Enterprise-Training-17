package com.njfu.entity;

import java.io.Serializable;
import java.util.Date;

public class Apply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer applyId;
	private String applyPerson;
	private String applyPersonIdCard;
	private Integer applyNum;
	private Date applyDate;
	private String applyProductName;
	private Integer applyProductId;
	
	public Apply() {
		super();
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	public String getApplyPersonIdCard() {
		return applyPersonIdCard;
	}

	public void setApplyPersonIdCard(String applyPersonIdCard) {
		this.applyPersonIdCard = applyPersonIdCard;
	}

	public Integer getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyProductName() {
		return applyProductName;
	}

	public void setApplyProductName(String applyProductName) {
		this.applyProductName = applyProductName;
	}

	public Integer getApplyProductId() {
		return applyProductId;
	}

	public void setApplyProductId(Integer applyProductId) {
		this.applyProductId = applyProductId;
	}

	@Override
	public String toString() {
		return "Apply [applyId=" + applyId + ", applyPerson=" + applyPerson + ", applyPersonIdCard=" + applyPersonIdCard
				+ ", applyNum=" + applyNum + ", applyDate=" + applyDate + ", applyProductName=" + applyProductName
				+ ", applyProductId=" + applyProductId + "]";
	}
	
	

}
