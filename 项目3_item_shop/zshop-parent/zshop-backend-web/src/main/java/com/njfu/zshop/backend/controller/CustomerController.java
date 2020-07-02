package com.njfu.zshop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.entity.Customer;
import com.njfu.zshop.params.CustomerParam;
import com.njfu.zshop.service.CustomerService;
import com.njfu.zshop.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:CarreLiu
 * Date:2020-07-01 9:33
 * Description:<描述>
 */
@Controller
@RequestMapping("/backend/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Customer> customers = customerService.findAll();
        PageInfo<Customer> pageInfo = new PageInfo<>(customers);
        model.addAttribute("pageInfo", pageInfo);

        return "customerManager";
    }

    //按条件查询
    @RequestMapping("/findByParams")
    public String findByParams(CustomerParam customerParam, Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Customer> customers = customerService.findByParams(customerParam);
        PageInfo<Customer> pageInfo = new PageInfo<>(customers);
        model.addAttribute("pageInfo", pageInfo);
        //设置数据回显
        model.addAttribute("customerParam", customerParam);

        return "customerManager";
    }

    //修改用户状态
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id) {
        try {
            customerService.modifyStatus(id);
            return ResponseResult.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("更新失败");
        }
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        Customer customer = customerService.findById(id);
        return ResponseResult.success(customer);
    }

    @RequestMapping("/modify")
    public String modify(Customer customer, Integer pageNum, HttpSession session, Model model) {
        try {
            customerService.modify(customer);
            model.addAttribute("successMsg", "修改成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }

        //转发到系统用户列表
        return "forward:findAll?pageNum" + pageNum;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String loginName, String primeLoginName) {

        Map<String, Object> map = new HashMap<>();

        if (loginName.equals(primeLoginName)) {
            map.put("valid", true);
        }
        else {
            boolean res = customerService.checkName(loginName);
            if (res) {
                map.put("valid", true);
            } else {
                map.put("valid", false);
                map.put("message", "账号(" + loginName + ")已存在");
            }
        }

        return map;
    }

}
