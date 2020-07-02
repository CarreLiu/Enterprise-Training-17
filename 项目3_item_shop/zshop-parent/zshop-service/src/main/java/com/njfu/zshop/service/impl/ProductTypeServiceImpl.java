package com.njfu.zshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.dao.ProductTypeDAO;
import com.njfu.zshop.entity.ProductType;
import com.njfu.zshop.exception.ProductTypeExistException;
import com.njfu.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-05-19 16:05
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDAO productTypeDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageInfo<ProductType> findAll(Integer page, Integer rows) {
        //设置分页
        PageHelper.startPage(page, rows);
        List<ProductType> productTypes = productTypeDAO.selectAll();
        //包装成PageInfo对象
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        return pageInfo;
    }

    @Override
    public void add(String name) throws ProductTypeExistException {
        ProductType productType = productTypeDAO.selectByName(name);
        if (productType != null) {
            throw new ProductTypeExistException("该商品类型已经存在");
        }
        productTypeDAO.insert(name, Constant.PRODUCT_TYPE_ENABLE);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ProductType findById(Integer id) {
        return productTypeDAO.selectById(id);
    }

    @Override
    public void modifyName(Integer id, String name) throws ProductTypeExistException {
        ProductType productType = productTypeDAO.selectByName(name);
        if (productType != null) {
            throw new ProductTypeExistException("商品类型已经存在");
        }
        productTypeDAO.updateName(id, name);
    }

    @Override
    public void removeById(Integer id) {
        productTypeDAO.deleteById(id);
    }

    @Override
    public void modifyStatus(Integer id) {
        ProductType productType = productTypeDAO.selectById(id);
        Integer status = productType.getStatus();
        if (status == Constant.PRODUCT_TYPE_ENABLE) {
            status = Constant.PRODUCT_TYPE_DISABLE;
        }
        else if (status == Constant.PRODUCT_TYPE_DISABLE) {
            status = Constant.PRODUCT_TYPE_ENABLE;
        }
        productTypeDAO.updateStatus(id, status);
    }

    @Override
    public List<ProductType> findEnable(Integer status) {
        return productTypeDAO.selectByStatus(status);
    }
}
