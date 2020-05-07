package com.njfu.form;

import org.apache.struts.action.ActionForm;

public class CompanyForm extends ActionForm {
	private Integer companyId;
	private String companyName;
	private Double financingInReturn;
	private String companyDetail;
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
		return "CompanyForm [companyId=" + companyId + ", companyName=" + companyName + ", financingInReturn="
				+ financingInReturn + ", companyDetail=" + companyDetail + "]";
	}
	
	
	
}
