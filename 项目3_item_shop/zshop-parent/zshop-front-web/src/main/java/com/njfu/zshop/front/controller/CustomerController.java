package com.njfu.zshop.front.controller;

import com.njfu.zshop.entity.Customer;
import com.njfu.zshop.exception.LoginErrorException;
import com.njfu.zshop.service.CustomerService;
import com.njfu.zshop.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:CarreLiu
 * Date:2020-06-21 0:08
 * Description:<描述>
 */
@Controller
@RequestMapping("/front/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/loginByAccount")
    @ResponseBody
    public ResponseResult login(String loginName, String password, HttpSession session) {

        try {
            Customer customer = customerService.login(loginName, password);
            session.setAttribute("customer", customer);
            return ResponseResult.success(customer);
        } catch (LoginErrorException e) {
            return ResponseResult.fail("登录失败");
        }
    }

    //退出登录
    @RequestMapping("/loginOut")
    @ResponseBody
    public ResponseResult loginOut(HttpSession session){
        //session.invalidate();
        session.removeAttribute("customer");
        return ResponseResult.success("退出成功");

    }

    @RequestMapping("/toCenter")
    public String toCenter(){
        return "center";
    }

    @RequestMapping("/modifyCustomer")
    public String modifyCustomer(Customer customer, HttpServletRequest request) {
        try {
            customerService.modifyCustomer(customer);
            Customer customerModify = customerService.findCustomerById(customer.getId());
            request.getSession().removeAttribute("customer");
            request.getSession().setAttribute("customer", customerModify);
            request.setAttribute("successMsg", "修改信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "修改信息失败");
        }

        return "center";
    }

    //注册
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(Customer customer) {
        try {
            customerService.add(customer);
            return ResponseResult.success("注册成功");
        } catch (Exception e) {
            return ResponseResult.fail("注册失败");
        }
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String loginName) {
        Map<String, Object> map = new HashMap<>();
        boolean res = customerService.checkName(loginName);
        if (res) {
            map.put("valid", true);
        }
        else {
            map.put("valid", false);
            map.put("message", "账号("+loginName+")已存在");
        }

        return map;
    }

    //修改密码
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public ResponseResult modifyPassword(Integer id, String password, String oldPassword, HttpSession session) {
        try {
            customerService.modifyPassword(id, password, oldPassword);
            session.removeAttribute("customer");
            return ResponseResult.success("修改密码成功");
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

}
