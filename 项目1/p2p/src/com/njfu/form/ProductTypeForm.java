package com.njfu.form;

import org.apache.struts.action.ActionForm;

public class ProductTypeForm extends ActionForm {
	private Integer productTypeId;
	private String productTypeName;
	private Integer productTypeStatus;
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
		return "ProductTypeForm [productTypeId=" + productTypeId + ", productTypeName=" + productTypeName
				+ ", productTypeStatus=" + productTypeStatus + "]";
	}
	
	
	
}
