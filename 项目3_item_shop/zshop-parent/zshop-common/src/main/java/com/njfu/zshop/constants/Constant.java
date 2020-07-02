package com.njfu.zshop.constants;

/**
 * Author:CarreLiu
 * Date:2020-05-22 10:48
 * Description:<描述>
 */
public class Constant {

    //当前页：默认第一页
    public static final Integer PAGE_NUM = 1;

    //每页显示的条数，默认5条
    public static final Integer PAGE_SIZE = 5;

    //响应状态码：1 成功
    public static final Integer RESPONSE_STATUS_SUCCESS = 1;

    //响应状态码：2 失败
    public static final Integer RESPONSE_STATUS_FAILURE = 2;

    //响应状态码：3 未授权
    public static final Integer RESPONSE_NO_PERMISSION = 3;

    //商品类型的状态：1 启用 默认值
    public static final Integer PRODUCT_TYPE_ENABLE = 1;

    //商品类型的状态：2 禁用
    public static final Integer PRODUCT_TYPE_DISABLE = 0;

    //系统用户的状态：1 启用 默认值
    public static final Integer SYSUSER_VALID = 1;

    //系统用户的状态：2 禁用
    public static final Integer SYSUSER_INVALID = 0;

    //前台每页记录数
    public static final Integer PAGE_SIZE_FRONT = 8;

    //顾客的状态：1 启用 默认值
    public static final Integer CUSTOMER_VALID = 1;

    //顾客的状态：2 禁用
    public static final Integer CUSTOMER_INVALID = 0;
}
