package com.njfu.zshop.front.cart;

import com.njfu.zshop.cart.ShoppingCart;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Author:CarreLiu
 * Date:2020-06-24 14:35
 * Description:<描述>
 */
public class ShoppingCartUtils {

    //若session中没有该对象，创建一个新的对象，放入到session作用域中，若有，直接返回
    public static ShoppingCart getShoppingCart(HttpSession session){
        ShoppingCart sc = (ShoppingCart)session.getAttribute("shoppingCart");
        if(sc == null){
            sc = new ShoppingCart();
            session.setAttribute("shoppingCart", sc);
        }
        return sc;
    }

    //根据时间随机生成订单号
    public static String getOrderIdByTime(){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++){
            result += random.nextInt(10);
        }
        return  newDate + result;
    }
}
