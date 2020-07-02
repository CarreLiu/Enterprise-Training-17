package com.njfu.zshop.front.controller;

import com.njfu.zshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:CarreLiu
 * Date:2020-07-01 18:20
 * Description:<描述>
 */
@Controller
@RequestMapping("/front/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


}
