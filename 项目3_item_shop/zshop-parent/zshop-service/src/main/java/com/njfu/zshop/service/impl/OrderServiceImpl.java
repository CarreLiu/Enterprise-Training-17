package com.njfu.zshop.service.impl;

import com.njfu.zshop.dao.OrderDAO;
import com.njfu.zshop.entity.Order;
import com.njfu.zshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-30 20:24
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Override
    public void addOrder(Order order) {
            orderDAO.insertOrder(order);
    }

    @Override
    public List<Order> findOrdersByCustomerId(Integer customerId) {
        return orderDAO.selectOrdersByCustomerId(customerId);
    }

    @Override
    public void removeOrder(Integer id) {
        orderDAO.deleteOrder(id);
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderDAO.selectOrderById(id);
    }
}
