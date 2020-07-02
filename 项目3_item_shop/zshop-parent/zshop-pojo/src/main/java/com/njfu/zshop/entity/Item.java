package com.njfu.zshop.entity;

import java.io.Serializable;

/**
 * Author:CarreLiu
 * Date:2020-07-01 18:13
 * Description:<描述>
 */
public class Item implements Serializable {

    private Integer id;

    private Integer productId;

    private Integer num;

    private Double price;

    private Integer orderId;

    private Product product;

    private Order order;

    public Item() {
    }

    public Item(Integer id, Integer productId, Integer num, Double price, Integer orderId) {
        this.id = id;
        this.productId = productId;
        this.num = num;
        this.price = price;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", productId=" + productId +
                ", num=" + num +
                ", price=" + price +
                ", orderId=" + orderId +
                ", product=" + product +
                ", order=" + order +
                '}';
    }
}
