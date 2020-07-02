package com.njfu.zshop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njfu.zshop.constants.Constant;
import com.njfu.zshop.entity.Role;
import com.njfu.zshop.entity.Sysuser;
import com.njfu.zshop.exception.SysuserNotExistException;
import com.njfu.zshop.params.SysuserParam;
import com.njfu.zshop.service.RoleService;
import com.njfu.zshop.service.SysuserService;
import com.njfu.zshop.service.vo.SysuserVO;
import com.njfu.zshop.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:CarreLiu
 * Date:2020-05-19 14:46
 * Description:<描述>
 */
@Controller
@RequestMapping("/backend/sysuser")
public class SysuserController {

    @Autowired
    private SysuserService sysuserService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> loadRoles() {
        List<Role> roles = roleService.findAll();

        return roles;
    }

    @RequestMapping("/toLogin")
    public String toMain() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session, Model model) {
        try {
            Sysuser sysuser = sysuserService.login(loginName, password);
            session.setAttribute("sysuser", sysuser);
            //返回视图名
            return "main";
        } catch (SysuserNotExistException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "login";
        }
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Sysuser> sysusers = sysuserService.findAll();
        PageInfo<Sysuser> pageInfo = new PageInfo<>(sysusers);
        model.addAttribute("pageInfo", pageInfo);

        return "sysuserManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(SysuserVO sysuserVO) {
        try {
            sysuserService.add(sysuserVO);
            return ResponseResult.success("添加成功");
        } catch (Exception e) {
            return ResponseResult.fail("添加失败");
        }
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String loginName) {
        Map<String, Object> map = new HashMap<>();
        boolean res = sysuserService.checkName(loginName);
        if (res) {
            map.put("valid", true);
        }
        else {
            map.put("valid", false);
            map.put("message", "账号("+loginName+")已存在");
        }

        return map;
    }

    //按条件查询
    @RequestMapping("/findByParams")
    public String findByParams(SysuserParam sysuserParam, Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = Constant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
        List<Sysuser> sysusers = sysuserService.findByParams(sysuserParam);
        PageInfo<Sysuser> pageInfo = new PageInfo<>(sysusers);
        model.addAttribute("pageInfo", pageInfo);
        //设置数据回显
        model.addAttribute("sysuserParam", sysuserParam);

        return "sysuserManager";
    }

    //修改用户状态
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id) {
        try {
            sysuserService.modifyStatus(id);
            return ResponseResult.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.fail("更新失败");
        }
    }

    //退出登录
    @RequestMapping("loginOut")
    @ResponseBody
    public ResponseResult loginOut(HttpSession session) {
        session.removeAttribute("sysuser");
        return ResponseResult.success("退出成功");
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        Sysuser sysuser = sysuserService.findById(id);
        return ResponseResult.success(sysuser);
    }

    @RequestMapping("/modify")
    public String modify(SysuserVO sysuserVO, Integer pageNum, HttpSession session, Model model) {
        try {
            sysuserService.modify(sysuserVO);
            model.addAttribute("successMsg", "修改成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }

        //转发到系统用户列表
        return "forward:findAll?pageNum" + pageNum;
    }
}
