package com.njfu.zshop.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Author:CarreLiu
 * Date:2020-06-30 20:11
 * Description:<描述>
 */
public class Order implements Serializable {

    private Integer id;

    private String no;

    private Customer customer;

    private  Double price;

    private Date createDate;

    public Order() {
    }

    public Order(Integer id, String no, Customer customer, Double price, Date createDate) {
        this.id = id;
        this.no = no;
        this.customer = customer;
        this.price = price;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", customer=" + customer +
                ", price=" + price +
                ", createDate=" + createDate +
                '}';
    }
}
