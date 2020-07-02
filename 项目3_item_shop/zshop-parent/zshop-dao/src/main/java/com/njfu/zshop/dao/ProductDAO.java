package com.njfu.zshop.dao;

import com.njfu.zshop.entity.Product;
import com.njfu.zshop.params.ProductParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-05-26 22:43
 * Description:<描述>
 */
@Repository
public interface ProductDAO {
    public void insert(Product product);

    public Product selectByName(String name);

    public List<Product> selectAll();

    public Product selectById(Integer id);

    public void update(Product product);

    public void deleteById(Integer id);

    public List<Product> selectByParams(ProductParam productParam);
}
