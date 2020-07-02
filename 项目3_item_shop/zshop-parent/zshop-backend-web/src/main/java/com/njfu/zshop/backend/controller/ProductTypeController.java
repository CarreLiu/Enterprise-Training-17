package com.njfu.zshop.backend.controller;

import com.github.pagehelper.PageInfo;
import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.entity.ProductType;
import com.njfu.zshop.exception.ProductTypeExistException;
import com.njfu.zshop.service.ProductTypeService;
import com.njfu.zshop.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:CarreLiu
 * Date:2020-05-19 15:02
 * Description:<描述>
 */
@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        //查找所有商品类型
        PageInfo<ProductType> pageInfo = productTypeService.findAll(pageNum, Constant.PAGE_SIZE);
        //将集合放入作用域
        model.addAttribute("data", pageInfo);

        return "productTypeManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(@RequestParam("name") String productTypeName) {

        ResponseResult result = new ResponseResult();
        try {
            productTypeService.add(productTypeName);
            result.setStatus(Constant.RESPONSE_STATUS_SUCCESS);
            result.setMessage("添加成功");
        } catch (ProductTypeExistException e) {
            //e.printStackTrace();
            result.setStatus(Constant.RESPONSE_STATUS_FAILURE);
            result.setMessage("商品类型已经存在");
        }
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        ProductType productType = productTypeService.findById(id);
        return ResponseResult.success(productType);
    }

    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult modifyName(Integer id, String name) {
        try {
            productTypeService.modifyName(id, name);
            return ResponseResult.success("修改商品类型成功");
        } catch (ProductTypeExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(Integer id) {
        try {
            productTypeService.removeById(id);
        } catch (Exception e) {
            return ResponseResult.fail("删除失败");
        }
        return ResponseResult.success("删除成功");
    }

    //修改商品类型状态
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(Integer id) {
        try {
            productTypeService.modifyStatus(id);
        } catch (Exception e) {
            ResponseResult.fail("删除状态失败");
        }
        return ResponseResult.success("删除状态成功");
    }

}
