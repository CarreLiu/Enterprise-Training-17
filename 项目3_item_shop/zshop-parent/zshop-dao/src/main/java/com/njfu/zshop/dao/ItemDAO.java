package com.njfu.zshop.dao;

import com.njfu.zshop.entity.Item;

import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-07-01 18:24
 * Description:<描述>
 */
public interface ItemDAO {


    public void insertItem(Item item);

    public List<Item> selectItemsByOrderId(Integer id);

    public void deleteItemsByOrderId(Integer id);
}
