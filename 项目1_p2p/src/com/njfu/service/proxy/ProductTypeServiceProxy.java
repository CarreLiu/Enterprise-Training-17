package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.ProductType;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ProductTypeForm;
import com.njfu.service.ProductTypeService;
import com.njfu.transaction.TransactionManager;

public class ProductTypeServiceProxy implements ProductTypeService {

	public List<ProductType> findProductType() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductTypeService productTypeService=(ProductTypeService) ObjectFactory.getObject("productTypeService");
		List<ProductType> list = null;
		try {
			tran.beginTransaction();
			list = productTypeService.findProductType();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}
	
	public void addProductType(ProductTypeForm productTypeForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductTypeService	productTypeService=(ProductTypeService) ObjectFactory.getObject("productTypeService");
		try {
			tran.beginTransaction();
			productTypeService.addProductType(productTypeForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public void modifyProductType(ProductTypeForm productTypeForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductTypeService	productTypeService=(ProductTypeService) ObjectFactory.getObject("productTypeService");
		try {
			tran.beginTransaction();
			productTypeService.modifyProductType(productTypeForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public void modifyProductTypeStatus(ProductType productType) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductTypeService	productTypeService=(ProductTypeService) ObjectFactory.getObject("productTypeService");
		try {
			tran.beginTransaction();
			productTypeService.modifyProductTypeStatus(productType);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
	}

	public List<ProductType> findByTypeName(String productTypeName) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductTypeService	productTypeService=(ProductTypeService) ObjectFactory.getObject("productTypeService");
		List<ProductType> productTypeList = null;
		try {
			tran.beginTransaction();
			productTypeList = productTypeService.findByTypeName(productTypeName);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return productTypeList;
	}

	public List<ProductType> findByTypeIdAndName(Integer productTypeId, String productTypeName) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductTypeService	productTypeService=(ProductTypeService) ObjectFactory.getObject("productTypeService");
		List<ProductType> productTypeList = null;
		try {
			tran.beginTransaction();
			productTypeList = productTypeService.findByTypeIdAndName(productTypeId, productTypeName);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return productTypeList;
	}
}
