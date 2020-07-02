package com.njfu.zshop.front.controller;

import com.njfu.zshop.cart.ShoppingCart;
import com.njfu.zshop.front.cart.ShoppingCartUtils;
import com.njfu.zshop.service.ProductService;
import com.njfu.zshop.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:CarreLiu
 * Date:2020-07-01 16:47
 * Description:<描述>
 */
@Controller
@RequestMapping("/front/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/toCart")
    public String toCart(){
        return "cart";
    }

    @RequestMapping("/addToCart")
    @ResponseBody
    public ResponseResult addToCart(int id, HttpSession session){
        if (session == null || session.getAttribute("customer") == null) {
            return ResponseResult.fail("请先登录，再进行购物");
        }
        boolean flag = false;
        //获取购物车对象，从session中获取，如果没有，创建购物车
        ShoppingCart sc = ShoppingCartUtils.getShoppingCart(session);
        //完成将商品添加到购物车
        flag = productService.addToCart(id, sc);
        if (flag){
            return ResponseResult.success("放入购物车成功");
        }

        return ResponseResult.fail("放入购物车失败");
    }

    //更新商品数量
    @RequestMapping("/updateItemQuantity")
    @ResponseBody
    public Map<String,Object> updateItemQuantity(int id, int quantity, HttpSession session){
        //获取购物车对象
        ShoppingCart sc = ShoppingCartUtils.getShoppingCart(session);
        productService.modifyItemQuantity(sc,id,quantity);
        Map<String,Object> result = new HashMap<>();
        //获取单个商品总价
        result.put("itemMoney",sc.getProducts().get(id).getItemMoney());
        //获取购物车所有商品总价
        result.put("totalMoney",sc.getTotalMoney());
        return result;
    }

    //清空购物车
    @RequestMapping("/clear")
    @ResponseBody
    public ResponseResult clear(HttpSession session){
        //获取购物车对象
        ShoppingCart sc= ShoppingCartUtils.getShoppingCart(session);
        productService.clearShoppingCart(sc);
        return ResponseResult.success("清空购物车成功");
    }

    //单个删除和批量删除都用
    @RequestMapping("/removeItemByIds")
    @ResponseBody
    public ResponseResult removeItemByIds(int[] ids, HttpSession session){
        //获取购物车
        ShoppingCart sc = ShoppingCartUtils.getShoppingCart(session);
        productService.removeItemsFromShoppingCart(sc, ids);
        if (sc.isEmpty()){
            return ResponseResult.fail("购物车已空");
        }

        float totalMoney = sc.getTotalMoney();
        return ResponseResult.success("删除成功", totalMoney);
    }
}
