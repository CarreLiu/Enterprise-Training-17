package com.njfu.service.proxy;

import java.util.List;

import com.njfu.entity.Product;
import com.njfu.exception.DataAccessException;
import com.njfu.exception.ServiceException;
import com.njfu.factory.ObjectFactory;
import com.njfu.form.ProductForm;
import com.njfu.service.ProductService;
import com.njfu.transaction.TransactionManager;

public class ProductServiceProxy implements ProductService {

	public List<Product> findProduct() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		List<Product> list = null;
		try {
			tran.beginTransaction();
			list = productService.findProduct();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}
	
	public List<Product> findByComIdAndProName(Integer companyId, String productName) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		List<Product> list = null;
		try {
			tran.beginTransaction();
			list = productService.findByComIdAndProName(companyId, productName);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return list;
	}
	
	public void addProduct(ProductForm productForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		try {
			tran.beginTransaction();
			productService.addProduct(productForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		
	}
	
	public void modifyProduct(ProductForm productForm) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		try {
			tran.beginTransaction();
			productService.modifyProduct(productForm);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException("修改商品异常");
		}
		
	}
	
	public Product findByProductId(int productId) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		Product product = null;
		try {
			tran.beginTransaction();
			product = productService.findByProductId(productId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException(e);
		}
		return product;
	}
	
	public void removeProduct(int productId) throws Exception {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		try {
			tran.beginTransaction();
			productService.removeProduct(productId);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException("商品删除异常");
		}
	}

	public List<Product> findFrontProducts() {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		List<Product> products = null;
		try {
			tran.beginTransaction();
			products = productService.findFrontProducts();
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException("查询产品列表异常");
		}
		
		return products;
	}

	public List<Product> findFrontProductByQueryVO(Product pro) {
		TransactionManager tran=(TransactionManager) ObjectFactory.getObject("transaction");
		ProductService	productService=(ProductService) ObjectFactory.getObject("productService");
		List<Product> products = null;
		try {
			tran.beginTransaction();
			products = productService.findFrontProductByQueryVO(pro);
			tran.commit();
		} catch (DataAccessException e) {
			tran.rollback();
			throw new ServiceException("根据条件查询产品列表异常");
		}
		
		return products;
	}
}
