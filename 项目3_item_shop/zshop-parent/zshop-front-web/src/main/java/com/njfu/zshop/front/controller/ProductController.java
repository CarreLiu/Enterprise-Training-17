package com.njfu.zshop.front.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.entity.Product;
import com.njfu.zshop.entity.ProductType;
import com.njfu.zshop.params.ProductParam;
import com.njfu.zshop.service.ProductService;
import com.njfu.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Author:CarreLiu
 * Date:2020-06-12 09:45
 * Description:<描述>
 */
@Controller
@RequestMapping("/front/product")
public class ProductController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    //初始化种类下拉列表
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes() {
        List<ProductType> productTypes = productTypeService.findEnable(Constant.PRODUCT_TYPE_ENABLE);
        return productTypes;
    }

    @RequestMapping("/main")
    public String main(ProductParam productParam, Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE_FRONT);
        List<Product> products = productService.findByParams(productParam);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);

        //设置数据回显
        model.addAttribute("productParam", productParam);

        return "main";
    }

    @RequestMapping("/showPic")
    //将image路径对应的图片写回输出流(页面)
    public void showPic(String image, OutputStream out) throws IOException {
        //把http请求读取流
        URL url = new URL(image);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        //建立缓冲池
        byte [] data = new byte[4096];
        int size = 0;
        size = is.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = is.read(data);
        }
        is.close();
        bos.flush();
        bos.close();
    }

}
