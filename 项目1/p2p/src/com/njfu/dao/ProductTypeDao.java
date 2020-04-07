package com.njfu.dao;

import java.util.List;

import com.njfu.entity.ProductType;
import com.njfu.exception.DataAccessException;
import com.njfu.form.ProductTypeForm;

public interface ProductTypeDao {
	public List<ProductType> selectProductType();

	public void insertProductType(ProductTypeForm productTypeForm) throws DataAccessException;

	public void updateProductType(ProductTypeForm productTypeForm) throws DataAccessException;

	public void updateProductTypeStatus(ProductType productType) throws DataAccessException;

	public List<ProductType> selectByTypeName(String productTypeName);

	public List<ProductType> selectByTypeIdAndName(Integer productTypeId, String productTypeName);
}
