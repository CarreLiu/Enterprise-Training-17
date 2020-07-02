package com.njfu.zshop.dao;

import com.njfu.zshop.entity.Order;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-30 20:25
 * Description:<描述>
 */
public interface OrderDAO {

    public void insertOrder(Order order);

    public List<Order> selectOrdersByCustomerId(Integer customerId);

    public void deleteOrder(Integer id);

    public Order selectOrderById(Integer id);
}
