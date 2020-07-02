package com.njfu.zshop.service;

import com.njfu.zshop.entity.Customer;
import com.njfu.zshop.exception.CustomerOldPassErrorException;
import com.njfu.zshop.exception.LoginErrorException;
import com.njfu.zshop.params.CustomerParam;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-21 0:13
 * Description:<描述>
 */
public interface CustomerService {

    public Customer login(String loginName, String password) throws LoginErrorException;

    public Customer findCustomerById(Integer id);

    public void modifyCustomer(Customer customer);

    public List<Customer> findAll();

    public Customer findById(int id);

    public void add(Customer customer);

    public boolean checkName(String loginName);

    public List<Customer> findByParams(CustomerParam customerParam);

    public void modify(Customer customer);

    public void modifyStatus(int id);

    public void modifyPassword(Integer id, String password, String oldPassword) throws CustomerOldPassErrorException;
}
