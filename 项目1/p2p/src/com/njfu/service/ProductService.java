package com.njfu.service;

import java.util.List;

import com.njfu.entity.Product;
import com.njfu.form.ProductForm;

public interface ProductService {
	public List<Product> findProduct();
	
	public List<Product> findByComIdAndProName(Integer companyId, String productName);

	public void addProduct(ProductForm productForm) throws Exception;
	
	public void modifyProduct(ProductForm productForm) throws Exception;
	
	public Product findByProductId(int productId) throws Exception;
	
	public void removeProduct(int productId) throws Exception;

	public List<Product> findFrontProducts();

	public List<Product> findFrontProductByQueryVO(Product pro);
}
