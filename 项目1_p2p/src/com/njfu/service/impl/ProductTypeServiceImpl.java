package com.njfu.service.impl;

import java.util.List;

import com.njfu.dao.ProductTypeDao;
import com.njfu.entity.ProductType;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ProductTypeForm;
import com.njfu.service.ProductTypeService;

public class ProductTypeServiceImpl implements ProductTypeService {
	public List<ProductType> findProductType() {
		ProductTypeDao productTypeDao = (ProductTypeDao) ObjectFactory.getObject("productTypeDao");
		List<ProductType> list = productTypeDao.selectProductType();
		
		return list;
	}

	public void addProductType(ProductTypeForm productTypeForm) throws Exception {
		ProductTypeDao productTypeDao = (ProductTypeDao) ObjectFactory.getObject("productTypeDao");
		productTypeDao.insertProductType(productTypeForm);
	}

	public void modifyProductType(ProductTypeForm productTypeForm) throws Exception {
		ProductTypeDao productTypeDao = (ProductTypeDao) ObjectFactory.getObject("productTypeDao");
		productTypeDao.updateProductType(productTypeForm);
	}

	public void modifyProductTypeStatus(ProductType productType) throws Exception {
		ProductTypeDao productTypeDao = (ProductTypeDao) ObjectFactory.getObject("productTypeDao");
		productTypeDao.updateProductTypeStatus(productType);
	}

	public List<ProductType> findByTypeName(String productTypeName) {
		ProductTypeDao productTypeDao = (ProductTypeDao) ObjectFactory.getObject("productTypeDao");
		List<ProductType> productTypeList = productTypeDao.selectByTypeName(productTypeName);
		
		return productTypeList;
	}

	public List<ProductType> findByTypeIdAndName(Integer productTypeId, String productTypeName) {
		ProductTypeDao productTypeDao = (ProductTypeDao) ObjectFactory.getObject("productTypeDao");
		List<ProductType> productTypeList = productTypeDao.selectByTypeIdAndName(productTypeId, productTypeName);
		
		return productTypeList;
	}
	
}
