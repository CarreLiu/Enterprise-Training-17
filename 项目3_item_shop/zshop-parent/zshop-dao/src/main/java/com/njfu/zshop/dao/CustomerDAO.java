package com.njfu.zshop.dao;

import com.njfu.zshop.entity.Customer;
import com.njfu.zshop.params.CustomerParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-21 0:47
 * Description:<描述>
 */
@Repository
public interface CustomerDAO {

    public Customer selectByLoginNameAndPass(@Param("loginName") String loginName, @Param("password") String password, @Param("isValid") Integer isValid);

    public Customer selectCustomerById(Integer id);

    public void updateCustomer(Customer customer);

    public List<Customer> selectAll();

    public Customer selectById(int id);

    public void insert(Customer customer);

    public Customer selectByName(String loginName);

    public List<Customer> selectByParams(CustomerParam customerParam);

    public void update(Customer customer);

    public void updateStatus(@Param("id")int id, @Param("isValid")int isValid);

    public Customer selectCustomerByIdAndPassword(@Param("id") Integer id, @Param("password") String oldPassword);

    public void updatePassword(@Param("id") Integer id, @Param("password") String password);
}
