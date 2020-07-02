package com.njfu.zshop.front.controller;

import com.njfu.zshop.cart.ShoppingCartItem;
import com.njfu.zshop.entity.Customer;
import com.njfu.zshop.entity.Item;
import com.njfu.zshop.entity.MyOrder;
import com.njfu.zshop.entity.Order;
import com.njfu.zshop.front.cart.ShoppingCartUtils;
import com.njfu.zshop.service.ItemService;
import com.njfu.zshop.service.OrderService;
import com.njfu.zshop.service.vo.OrderVO;
import com.njfu.zshop.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Author:CarreLiu
 * Date:2020-06-30 20:32
 * Description:<描述>
 */
@Controller
@RequestMapping("/front/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    //显示订单
    @RequestMapping("/showOrder")
    public String showOrder() {
        return "order";
    }

    //生成订单
    @RequestMapping("/generateOrder")
    @ResponseBody
    public ResponseResult generateOrder(OrderVO orderVO, HttpSession session) {
        try {
            Order order = new Order();
            String no = ShoppingCartUtils.getOrderIdByTime();
            order.setNo(no);
            Customer customer = new Customer();
            customer.setId(orderVO.getCustomerId());
            order.setCustomer(customer);
            order.setPrice(orderVO.getPrice());
            order.setCreateDate(new Date());
            orderService.addOrder(order);
            Integer orderId = order.getId();  //mapper中进行了设置，可以返回插入的order的id值

            Map<Integer, ShoppingCartItem> products = ShoppingCartUtils.getShoppingCart(session).getProducts();
            Iterator<Map.Entry<Integer, ShoppingCartItem>> entries = products.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<Integer, ShoppingCartItem> entry = entries.next();
                Integer key = entry.getKey();
                ShoppingCartItem value = entry.getValue();
                Integer productId = value.getProduct().getId();
                Integer num = value.getQuantity();
                Double price = value.getProduct().getPrice();
                Item item = new Item(null, productId, num, price, orderId);
                itemService.addItem(item);
            }

            return ResponseResult.success(order);
        } catch (Exception e) {
            return ResponseResult.fail("生成订单失败");
        }
    }

    //显示订单详情
    @RequestMapping("/showOrderDetail")
    public String showOrderDetail() {
        return "orderDetail";
    }

    //转到我的订单页
    @RequestMapping("/toOrder")
    public String toOrder(HttpSession session, Model model){
        if (session != null && session.getAttribute("customer") != null) {
            Customer customer = (Customer) session.getAttribute("customer");
            Integer customerId = customer.getId();
            List<Order> orders = orderService.findOrdersByCustomerId(customerId);

            List<MyOrder> myOrders = new LinkedList<MyOrder>();
            for (Order order : orders) {
                MyOrder myOrder = new MyOrder();
                myOrder.setOrder(order);
                List<Item> items = itemService.findItemsByOrderId(order.getId());
                myOrder.setItemList(items);
                myOrders.add(myOrder);
            }

            model.addAttribute("myOrders", myOrders);
        }

        return "myOrders";
    }

    @RequestMapping("/removeOrder")
    @ResponseBody
    public ResponseResult removeOrder(Integer id) {
        try {
            itemService.removeItemsByOrderId(id);
            orderService.removeOrder(id);
            return ResponseResult.success("删除成功");
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/toOrderDetail")
    public String toOrderDetail(Integer id, HttpSession session, Model model) {
        if (session != null && session.getAttribute("customer") != null) {
            MyOrder myOrder = new MyOrder();
            Order order = orderService.findOrderById(id);
            myOrder.setOrder(order);
            List<Item> items = itemService.findItemsByOrderId(id);
            myOrder.setItemList(items);
            model.addAttribute("myOrder", myOrder);
        }

        return "orderDetail";
    }

}
