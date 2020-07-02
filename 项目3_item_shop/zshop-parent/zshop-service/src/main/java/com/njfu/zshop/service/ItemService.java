package com.njfu.zshop.service;

import com.njfu.zshop.entity.Item;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-07-01 18:21
 * Description:<描述>
 */
public interface ItemService {

    public void addItem(Item item);

    public List<Item> findItemsByOrderId(Integer id);

    public void removeItemsByOrderId(Integer id);
}
