package com.njfu.zshop.service.impl;

import com.njfu.zshop.dao.ItemDAO;
import com.njfu.zshop.entity.Item;
import com.njfu.zshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-07-01 18:22
 * Description:<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;


    @Override
    public void addItem(Item item) {
        itemDAO.insertItem(item);
    }

    @Override
    public List<Item> findItemsByOrderId(Integer id) {
        return itemDAO.selectItemsByOrderId(id);
    }

    @Override
    public void removeItemsByOrderId(Integer id) {
        itemDAO.deleteItemsByOrderId(id);
    }
}
