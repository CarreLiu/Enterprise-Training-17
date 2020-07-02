package com.njfu.zshop.service.dto;

import java.io.InputStream;

/**
 * Author:CarreLiu
 * Date:2020-05-29 16:36
 * Description:<描述>
 * 该类是在service使用的数据对象
 */
public class ProductDto {
    private Integer id;
    private String name;
    private Double price;
    private Integer productTypeId;
    private String info;

    //将VO中的file对象转成service层中可以处理的对象

    //文件的输入流
    private InputStream inputStream;

    //文件名称
    private String fileName;

    //文件的上传路径
    private String uploadPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

}
