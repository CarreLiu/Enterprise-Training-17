package com.njfu.zshop.service;

import com.github.pagehelper.PageInfo;
import com.njfu.zshop.cart.ShoppingCart;
import com.njfu.zshop.entity.Product;
import com.njfu.zshop.params.ProductParam;
import com.njfu.zshop.service.dto.ProductDto;
import org.apache.commons.fileupload.FileUploadException;

import java.io.OutputStream;
import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-05-26 22:43
 * Description:<描述>
 */
public interface ProductService {
    public void add(ProductDto productDto) throws FileUploadException;

    public boolean checkName(String name);

    public PageInfo<Product> findAll(Integer pageNum, Integer pageSize);

    public Product findById(Integer id);

    public void modifyProduct(ProductDto productDto) throws FileUploadException;

    public void removeProduct(Integer id);

    public void getImage(String path, OutputStream out);

    public List<Product> findByParams(ProductParam productParam);

    public boolean addToCart(int id, ShoppingCart sc);

    public void modifyItemQuantity(ShoppingCart sc, int id, int quantity);

    public void clearShoppingCart(ShoppingCart sc);

    public void removeItemsFromShoppingCart(ShoppingCart sc, int[] ids);
}
