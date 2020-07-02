package com.njfu.zshop.service;

import com.njfu.zshop.entity.Order;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-30 20:20
 * Description:<描述>
 */
public interface OrderService {
    public void addOrder(Order order);

    public List<Order> findOrdersByCustomerId(Integer customerId);

    public void removeOrder(Integer id);

    public Order findOrderById(Integer id);
}
