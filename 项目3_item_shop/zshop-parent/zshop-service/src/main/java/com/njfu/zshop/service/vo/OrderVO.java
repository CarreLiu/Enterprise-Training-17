package com.njfu.zshop.service.vo;

import java.util.Date;

/**
 * Author:CarreLiu
 * Date:2020-06-30 20:17
 * Description:<描述>
 */
public class OrderVO {

    private Integer id;

    private String no;

    private Integer customerId;

    private Double price;

    private Date createDate;

    public OrderVO() {
    }

    public OrderVO(Integer id, String no, Integer customerId, Double price, Date createDate) {
        this.id = id;
        this.no = no;
        this.customerId = customerId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
}
