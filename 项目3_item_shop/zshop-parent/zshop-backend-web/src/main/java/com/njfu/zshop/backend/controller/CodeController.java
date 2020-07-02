package com.njfu.zshop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author:CarreLiu
 * Date:2020-06-09 20:05
 * Description:<描述>
 */
@Controller
@RequestMapping("/backend/code")
public class CodeController {

    @RequestMapping("/image")
    //该方法用以生成一张图片，字符包括：a-zA-Z0-9
    public void image(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //创建一个图像缓冲区
        BufferedImage bi = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        //通过缓冲区创建一个画布
        Graphics g = bi.getGraphics();
        //创建颜色
        Color c = new Color(200,150,150);
        g.setColor(c);
        //绘制矩形框
        g.fillRect(0,0,68,22);

        //随机获取a-zA-Z0-9的四个字符
        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random r = new Random();
        int len = ch.length;
        int index;
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<4;i++){
            //获取0到len-1之间的任意整数，作为数组的索引
            index= r.nextInt(len);
            //设置颜色
            g.setColor(new Color(0,0,0));
            //将这4个字符写入图片
            g.drawString(ch[index]+"",(i*15)+3,15);
            sb.append(ch[index]);
        }
        //将数字保留到session作用域，以便前端进行校验
        req.getSession().setAttribute("piccode",sb.toString());
        //将该图像以jpg格式输出到输出流（页面）
        ImageIO.write(bi,"jpg",resp.getOutputStream());
    }

    //校验验证码
    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String, Object> checkCode(String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String piccode = (String)session.getAttribute("piccode");
        if (piccode.equalsIgnoreCase(code)) {
            map.put("valid", true);
        }
        else {
            map.put("valid", false);
        }

        return map;
    }
}
