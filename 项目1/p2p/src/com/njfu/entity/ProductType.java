package com.njfu.entity;

import java.io.Serializable;

public class ProductType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productTypeId;
	private String productTypeName;
	private Integer productTypeStatus;
	
	public ProductType() {
		super();
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public Integer getProductTypeStatus() {
		return productTypeStatus;
	}
	public void setProductTypeStatus(Integer productTypeStatus) {
		this.productTypeStatus = productTypeStatus;
	}
	@Override
	public String toString() {
		return "ProductType [productTypeId=" + productTypeId + ", productTypeName=" + productTypeName
				+ ", productTypeStatus=" + productTypeStatus + "]";
	}
	
	
}
