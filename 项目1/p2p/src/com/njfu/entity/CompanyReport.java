package com.njfu.entity;

import java.util.List;

//公司报表对象
public class CompanyReport {
	//公司对象
	private Company company;
	//该公司的融资产品总数
	private int totalCountOfProduct;
	//该公司的总融资目标
	private double totalTargetFinance = 0d;
	//该公司的实际融资数
	private double totalActualFinance = 0d;
	//该公司的佣金
	private double totalMoneyReturn = 0d;
	
	
	public CompanyReport() {
		super();
	}
	
	public CompanyReport(List<CompanyReportDetail> details) {
		if (details != null && !details.isEmpty()) {
			this.company = details.get(0).getProduct().getCompany();
			this.totalCountOfProduct = details.size();
			for (CompanyReportDetail companyReportDetail : details) {
				totalActualFinance += companyReportDetail.getActualFinance();
				totalTargetFinance += companyReportDetail.getTargetFinance();
				totalMoneyReturn += companyReportDetail.getMoneyReturn();
			}
		}
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getTotalCountOfProduct() {
		return totalCountOfProduct;
	}
	public void setTotalCountOfProduct(int totalCountOfProduct) {
		this.totalCountOfProduct = totalCountOfProduct;
	}
	public double getTotalTargetFinance() {
		return totalTargetFinance;
	}
	public void setTotalTargetFinance(double totalTargetFinance) {
		this.totalTargetFinance = totalTargetFinance;
	}
	public double getTotalActualFinance() {
		return totalActualFinance;
	}
	public void setTotalActualFinance(double totalActualFinance) {
		this.totalActualFinance = totalActualFinance;
	}
	public double getTotalMoneyReturn() {
		return totalMoneyReturn;
	}
	public void setTotalMoneyReturn(double totalMoneyReturn) {
		this.totalMoneyReturn = totalMoneyReturn;
	}
	@Override
	public String toString() {
		return "CompanyReport [company=" + company + ", totalCountOfProduct=" + totalCountOfProduct
				+ ", totalTargetFinance=" + totalTargetFinance + ", totalActualFinance=" + totalActualFinance
				+ ", totalMoneyReturn=" + totalMoneyReturn + "]";
	}
	
	
}
