package com.njfu.zshop.service.impl;

import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.dao.CustomerDAO;
import com.njfu.zshop.entity.Customer;
import com.njfu.zshop.exception.CustomerOldPassErrorException;
import com.njfu.zshop.exception.LoginErrorException;
import com.njfu.zshop.params.CustomerParam;
import com.njfu.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-21 0:44
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer login(String loginName, String password) throws LoginErrorException {

        Customer customer = customerDAO.selectByLoginNameAndPass(loginName, password, Constant.SYSUSER_VALID);
        if (customer == null) {
            throw new LoginErrorException("登录失败，用户名或者密码不正确");
        }

        return customer;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer findCustomerById(Integer id) {
        Customer customer = customerDAO.selectCustomerById(id);
        return customer;
    }

    @Override
    public void modifyCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Customer> findAll() {
        return customerDAO.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer findById(int id) {
        return customerDAO.selectById(id);
    }

    @Override
    public void add(Customer customer) {
        customer.setIsValid(Constant.CUSTOMER_VALID);
        customer.setRegistDate(new Date());
        customerDAO.insert(customer);
    }

    @Override
    public boolean checkName(String loginName) {
        Customer customer = customerDAO.selectByName(loginName);
        if (customer != null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Customer> findByParams(CustomerParam customerParam) {
        return customerDAO.selectByParams(customerParam);
    }

    @Override
    public void modify(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void modifyStatus(int id) {
        Customer customer = customerDAO.selectById(id);
        Integer isValid = customer.getIsValid();
        if (isValid == Constant.CUSTOMER_VALID) {
            isValid = Constant.CUSTOMER_INVALID;
        }
        else {
            isValid = Constant.CUSTOMER_VALID;
        }
        customerDAO.updateStatus(id, isValid);
    }

    @Override
    public void modifyPassword(Integer id, String password, String oldPassword) throws CustomerOldPassErrorException {
        Customer customer = customerDAO.selectCustomerByIdAndPassword(id, oldPassword);
        if (customer == null) {
            throw new CustomerOldPassErrorException("旧密码错误");
        }
        customerDAO.updatePassword(id, password);
    }
}
