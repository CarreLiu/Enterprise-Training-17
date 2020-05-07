package com.njfu.service;

import java.util.List;

import com.njfu.entity.ProductType;
import com.njfu.form.ProductTypeForm;

public interface ProductTypeService {
	//productType
	public List<ProductType> findProductType();

	public void addProductType(ProductTypeForm productTypeForm) throws Exception;

	public void modifyProductType(ProductTypeForm productTypeForm) throws Exception;

	public void modifyProductTypeStatus(ProductType productType) throws Exception;

	public List<ProductType> findByTypeName(String productTypeName);

	public List<ProductType> findByTypeIdAndName(Integer productTypeId, String productTypeName);
}
