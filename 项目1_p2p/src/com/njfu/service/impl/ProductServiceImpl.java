package com.njfu.service.impl;

import java.util.Date;
import java.util.List;

import com.njfu.dao.ProductDao;
import com.njfu.entity.Product;
import com.njfu.exception.ProductInUseException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ProductForm;
import com.njfu.service.ProductService;

public class ProductServiceImpl implements ProductService {
	public List<Product> findProduct() {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		List<Product> list = productDao.selectProduct();
		
		return list;
	}
	
	public List<Product> findByComIdAndProName(Integer companyId, String productName) {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		
		return productDao.selectByComIdAndProName(companyId, productName);
	}

	public void addProduct(ProductForm productForm) throws Exception {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		productForm.setCreateTime(new Date());
		productDao.insertProduct(productForm);
	}
	
	public void modifyProduct(ProductForm productForm) throws Exception {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		productForm.setCreateTime(new Date());
		productDao.updateProduct(productForm);
	}
	
	public Product findByProductId(int productId) throws Exception {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		Product product = productDao.selectByProductId(productId);
		
		return product;
	}
	
	public void removeProduct(int productId) throws Exception {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		try {
			productDao.delProduct(productId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductInUseException();
		}
	}

	public List<Product> findFrontProducts() {
		// TODO Auto-generated method stub
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		List<Product> products = productDao.selectFrontProducts();
		return products;
	}

	public List<Product> findFrontProductByQueryVO(Product pro) {
		// TODO Auto-generated method stub
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		List<Product> products = productDao.selectFrontProductByQueryVO(pro);
		return products;
	}
	
}
