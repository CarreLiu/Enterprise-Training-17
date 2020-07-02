package com.njfu.zshop.dao;

import com.njfu.zshop.entity.ProductType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-05-19 15:16
 * Description:<描述>
 */
@Repository
public interface ProductTypeDAO {

    public List<ProductType> selectAll();

    public ProductType selectByName(String name);

    public void insert(@Param("name") String name, @Param("status") Integer status);

    public ProductType selectById(Integer id);

    public void updateName(@Param("id") Integer id, @Param("name") String name);

    public void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    public void deleteById(Integer id);

    public List<ProductType> selectByStatus(Integer status);
}
