package com.njfu.entity;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	
	//基本字段属性
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String productName;
	private Double primeLendingRateFrom;
	private Double primeLendingRateTo;
	private Integer financingAmountFrom;
	private Integer financingAmountTo;
	private String linkMan;
	private String ownedBank;
	private String bankIcon;
	private String productDescription;
	private Date createTime;
	//三个外键属性
	private Integer companyId;
	private Integer productTypeId;
	private Integer lendingPeriodId;
	
	//外键关联属性对应的对象
	private Company company;
	private ProductType productType;
	private LendingPeriod lendingPeriod;
	
	public Product() {
		super();
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrimeLendingRateFrom() {
		return primeLendingRateFrom;
	}

	public void setPrimeLendingRateFrom(Double primeLendingRateFrom) {
		this.primeLendingRateFrom = primeLendingRateFrom;
	}

	public Double getPrimeLendingRateTo() {
		return primeLendingRateTo;
	}

	public void setPrimeLendingRateTo(Double primeLendingRateTo) {
		this.primeLendingRateTo = primeLendingRateTo;
	}

	public Integer getFinancingAmountFrom() {
		return financingAmountFrom;
	}

	public void setFinancingAmountFrom(Integer financingAmountFrom) {
		this.financingAmountFrom = financingAmountFrom;
	}

	public Integer getFinancingAmountTo() {
		return financingAmountTo;
	}

	public void setFinancingAmountTo(Integer financingAmountTo) {
		this.financingAmountTo = financingAmountTo;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getOwnedBank() {
		return ownedBank;
	}

	public void setOwnedBank(String ownedBank) {
		this.ownedBank = ownedBank;
	}

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getLendingPeriodId() {
		return lendingPeriodId;
	}

	public void setLendingPeriodId(Integer lendingPeriodId) {
		this.lendingPeriodId = lendingPeriodId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public LendingPeriod getLendingPeriod() {
		return lendingPeriod;
	}

	public void setLendingPeriod(LendingPeriod lendingPeriod) {
		this.lendingPeriod = lendingPeriod;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", primeLendingRateFrom="
				+ primeLendingRateFrom + ", primeLendingRateTo=" + primeLendingRateTo + ", financingAmountFrom="
				+ financingAmountFrom + ", financingAmountTo=" + financingAmountTo + ", linkMan=" + linkMan
				+ ", ownedBank=" + ownedBank + ", bankIcon=" + bankIcon + ", productDescription=" + productDescription
				+ ", createTime=" + createTime + ", companyId=" + companyId + ", productTypeId=" + productTypeId
				+ ", lendingPeriodId=" + lendingPeriodId + ", company=" + company + ", productType=" + productType
				+ ", lendingPeriod=" + lendingPeriod + "]";
	}

	
}
