package com.njfu.dao;

import java.util.List;

import com.njfu.entity.Product;
import com.njfu.exception.DataAccessException;
import com.njfu.form.ProductForm;

public interface ProductDao {
	public List<Product> selectProduct();
	
	public List<Product> selectByComIdAndProName(Integer companyId, String productName);
	
	public void insertProduct(ProductForm productForm) throws DataAccessException;
	
	public void updateProduct(ProductForm productForm) throws DataAccessException;
	
	public Product selectByProductId(int productId);
	
	public void delProduct(int productId) throws DataAccessException;

	public List<Product> selectFrontProducts();

	public List<Product> selectFrontProductByQueryVO(Product pro);
}
