package com.njfu.zshop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-07-01 20:39
 * Description:<描述>
 */
public class MyOrder implements Serializable {

    private Order order;

    private List<Item> itemList;

    public MyOrder() {
    }

    public MyOrder(Order order, List<Item> itemList) {
        this.order = order;
        this.itemList = itemList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "order=" + order +
                ", itemList=" + itemList +
                '}';
    }
}
