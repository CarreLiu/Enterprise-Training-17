package com.njfu.zshop.backend.controller;

import com.github.pagehelper.PageInfo;
import com.njfu.zshop.backend.vo.ProductVO;
import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.entity.Product;
import com.njfu.zshop.entity.ProductType;
import com.njfu.zshop.service.ProductService;
import com.njfu.zshop.service.ProductTypeService;
import com.njfu.zshop.service.dto.ProductDto;
import com.njfu.zshop.utils.ResponseResult;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:CarreLiu
 * Date:2020-05-26 22:28
 * Description:<描述>
 */
@Controller
@RequestMapping("/backend/product")
public class ProductController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("productTypes")
    //执行该路径下所有请求，都会先执行ModelAttribute对应的方法，返回值会赋给ModelAttribute对应的key
    public List<ProductType> loadProductTypes() {
        List<ProductType> productTypes = productTypeService.findEnable(Constant.PRODUCT_TYPE_ENABLE);
        return productTypes;
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        PageInfo<Product> pageInfo = productService.findAll(pageNum, Constant.PAGE_SIZE);
        model.addAttribute("data", pageInfo);

        return "productManager";
    }

    @RequestMapping("/add")
    //将图片上传至ftp服务器
    public String add(ProductVO productVO, Integer pageNum, HttpSession session, Model model) {

        //将VO转换成dto
        ProductDto productDto = new ProductDto();
        //上传路径
        //注意：这里在物理路径上必须有该目录
        //String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            //将vo中的值拷贝到dto，这里只有属性名称一致的才能被拷贝
            PropertyUtils.copyProperties(productDto, productVO);
            productDto.setFileName(productVO.getFile().getOriginalFilename());
            productDto.setInputStream(productVO.getFile().getInputStream());
            //productDto.setUploadPath(uploadPath);

            productService.add(productDto);
            model.addAttribute("successMsg", "添加成功");

        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }

        //转发到产品列表
        return "forward:findAll?pageNum" + pageNum;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String name) {

        Map<String, Object> map = new HashMap<>();
        boolean res = productService.checkName(name);
        //如果名字不存在，可用
        if (res) {
            map.put("valid", true);
        }
        else {
            //不可用,需要设置valid和message两个属性，remote.js会自动读取这两个值，当valid值为false时，输出message所对应的值
            map.put("valid", false);
            map.put("message", "商品(" + name + ")已经存在");
        }

        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        Product product = productService.findById(id);
        return ResponseResult.success(product);
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(Integer id) {
        try {
            productService.removeProduct(id);
        } catch (Exception e) {
            return ResponseResult.fail("删除失败");
        }
        return ResponseResult.success("删除成功");
    }

    @RequestMapping("/getImg")
    //显示预览
    //思路：将路径对应的图片写到输出流，放入src属性，使用img图像标签显示图像
    public void getImg(String path, OutputStream out) {
        productService.getImage(path, out);
    }

    @RequestMapping("/modify")
    //修改产品
    public String modify(ProductVO productVO, Integer pageNum, HttpSession session, Model model) {

        //将VO转换成dto
        ProductDto productDto = new ProductDto();
        //上传路径
        //注意：这里在物理路径上必须有该目录
        //String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            //将vo中的值拷贝到dto，这里只有属性名称一致的才能被拷贝
            PropertyUtils.copyProperties(productDto, productVO);
            if (!"".equals(productVO.getFile().getOriginalFilename())) {
                productDto.setFileName(productVO.getFile().getOriginalFilename());
                productDto.setInputStream(productVO.getFile().getInputStream());
                //productDto.setUploadPath(uploadPath);
            }
            productService.modifyProduct(productDto);
            model.addAttribute("successMsg", "修改成功");

        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }

        //转发到产品列表
        return "forward:findAll?pageNum" + pageNum;
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
