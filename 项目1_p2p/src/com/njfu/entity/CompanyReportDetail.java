package com.njfu.entity;

import java.util.List;

//公司报表列表对象
public class CompanyReportDetail {
	//产品
	private Product product;
	//目标融资额
	private double targetFinance = 0d;
	//实际融资额
	private double actualFinance = 0d;
	//抽成
	private double moneyReturn = 0d;	
	public CompanyReportDetail() {
		super();
	}
	//初始化当前产品的详细信息
	public CompanyReportDetail(Product product, List<Apply> list) {
		this.product = product;
		if (list != null && !list.isEmpty()) {
			for (Apply apply : list) {
				//实际融资额
				this.actualFinance += apply.getApplyNum();
			}
		}
		//抽成
		this.moneyReturn = this.actualFinance * product.getCompany().getFinancingInReturn()/100;
		//目标融资额
		this.targetFinance = product.getFinancingAmountTo();
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getTargetFinance() {
		return targetFinance;
	}
	public void setTargetFinance(double targetFinance) {
		this.targetFinance = targetFinance;
	}
	public double getActualFinance() {
		return actualFinance;
	}
	public void setActualFinance(double actualFinance) {
		this.actualFinance = actualFinance;
	}
	public double getMoneyReturn() {
		return moneyReturn;
	}
	public void setMoneyReturn(double moneyReturn) {
		this.moneyReturn = moneyReturn;
	}
	@Override
	public String toString() {
		return "CompanyReportDetail [product=" + product + ", targetFinance=" + targetFinance + ", actualFinance="
				+ actualFinance + ", moneyReturn=" + moneyReturn + "]";
	}
	
}
