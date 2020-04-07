package com.njfu.entity;

import java.io.Serializable;

public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer companyId;
	private String companyName;
	private Double financingInReturn;
	private String companyDetail;
	
	public Company() {
		super();
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getFinancingInReturn() {
		return financingInReturn;
	}
	public void setFinancingInReturn(Double financingInReturn) {
		this.financingInReturn = financingInReturn;
	}
	public String getCompanyDetail() {
		return companyDetail;
	}
	public void setCompanyDetail(String companyDetail) {
		this.companyDetail = companyDetail;
	}
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", financingInReturn="
				+ financingInReturn + ", companyDetail=" + companyDetail + "]";
	}
	
	
}
