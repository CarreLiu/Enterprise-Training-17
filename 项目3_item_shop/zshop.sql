/*
Navicat MySQL Data Transfer

Source Server         : CarreLiu
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : zshop

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-02 23:09:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `login_name` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '刘家乐', 'carreliu', '123456', '18816210252', '江苏省无锡市滨湖区', '1', '2020-06-21 01:16:10');
INSERT INTO `t_customer` VALUES ('2', '乐乐乐', 'happy', '123456', '18888888888', '江苏省南京市', '0', '2020-07-01 10:29:56');
INSERT INTO `t_customer` VALUES ('3', '刘家乐', 'carreliu2', '123456', '123232', 'fjdkfjdkk', '1', '2020-07-02 15:58:27');

-- ----------------------------
-- Table structure for `t_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `t_item_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`),
  CONSTRAINT `t_item_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_item
-- ----------------------------
INSERT INTO `t_item` VALUES ('1', '1', '2', '3999', '1');
INSERT INTO `t_item` VALUES ('2', '2', '1', '5999', '1');
INSERT INTO `t_item` VALUES ('3', '3', '1', '2399', '1');
INSERT INTO `t_item` VALUES ('4', '4', '1', '4999', '1');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(300) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '20200702193453874', '1', '21395', '2020-07-02 19:34:53');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` double DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `product_type_id` (`product_type_id`),
  CONSTRAINT `t_product_ibfk_1` FOREIGN KEY (`product_type_id`) REFERENCES `t_product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '小米10 5G', '3999', '【向往的生活同款】小米10 双模5G 骁龙865 1亿像素8K电影相机 对称式立体声 8GB+256GB 蜜桃金', 'ftp://carreliu:123456@localhost/images/8/3/2020070105435277.jpg', '3');
INSERT INTO `t_product` VALUES ('2', 'iphone 11', '5999', 'Apple iPhone 11 (A2223) 128GB 绿色 移动联通电信4G手机 双卡双待', 'ftp://carreliu:123456@localhost/images/14/7/2020070105520797.jpg', '3');
INSERT INTO `t_product` VALUES ('3', '红米K30 5G', '2399', 'Redmi K30 5G双模 120Hz流速屏 骁龙765G 前置挖孔双摄 30W快充 8GB+128GB 紫玉幻境', 'ftp://carreliu:123456@localhost/images/8/15/202007010554085.jpg', '3');
INSERT INTO `t_product` VALUES ('4', '小米10pro 5G', '4999', '【向往的生活同款】小米10 Pro 双模5G 骁龙865 1亿像素8K电影相机 50倍变焦 50W快充 8GB+256GB 珍珠白', 'ftp://carreliu:123456@localhost/images/8/7/2020070105563141.jpg', '3');
INSERT INTO `t_product` VALUES ('5', '红米10X 5G', '1799', 'Redmi 10X 5G 天玑820 双5G待机 4800万流光相机 水滴全面屏 6GB+128GB 深海蓝', 'ftp://carreliu:123456@localhost/images/14/15/2020070105584036.jpg', '3');
INSERT INTO `t_product` VALUES ('6', '华为P40 5G', '4188', '华为 HUAWEI P40 麒麟990 5G SoC芯片 5000万超感知徕卡三摄 30倍数字变焦 6GB+128GB晨曦金全网通5G手机', 'ftp://carreliu:123456@localhost/images/3/4/2020070106002866.jpg', '3');
INSERT INTO `t_product` VALUES ('7', '华为nova7 5G', '2999', '华为 HUAWEI nova 7 5G 6400万后置四摄 5G SoC芯片 OLED极点全面屏 8GB+128GB 7号色全网通5G手机', 'ftp://carreliu:123456@localhost/images/6/7/202007010602010.jpg', '3');
INSERT INTO `t_product` VALUES ('8', '荣耀V30 5G', '2489', '荣耀V30 5G 双模 麒麟990 突破性相机矩阵 游戏手机 6GB+128GB 冰岛幻境 移动联通电信5G 双卡双待', 'ftp://carreliu:123456@localhost/images/6/4/2020070106030721.jpg', '3');
INSERT INTO `t_product` VALUES ('9', '三星S20+ 5G', '7999', '三星 Galaxy S20+ 5G (SM-G9860)双模5G 骁龙865 120Hz超感屏 8K视频 游戏手机 12GB+128GB 馥郁红', 'ftp://carreliu:123456@localhost/images/8/8/2020070106070224.jpg', '3');
INSERT INTO `t_product` VALUES ('10', '一加8pro 5G', '5999', '一加 OnePlus 8 Pro 5G旗舰 2K+120Hz 柔性屏 30W无线闪充 高通骁龙865 12GB+256GB 青空 拍照游戏手机', 'ftp://carreliu:123456@localhost/images/9/14/2020070106082435.jpg', '3');

-- ----------------------------
-- Table structure for `t_product_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_type
-- ----------------------------
INSERT INTO `t_product_type` VALUES ('1', '食品', '1');
INSERT INTO `t_product_type` VALUES ('2', '衣服', '0');
INSERT INTO `t_product_type` VALUES ('3', '数码', '1');
INSERT INTO `t_product_type` VALUES ('4', '生活用品', '1');
INSERT INTO `t_product_type` VALUES ('5', '家装', '0');
INSERT INTO `t_product_type` VALUES ('6', '旅游', '1');
INSERT INTO `t_product_type` VALUES ('7', '运动', '1');
INSERT INTO `t_product_type` VALUES ('8', '电器', '1');
INSERT INTO `t_product_type` VALUES ('9', '家居', '0');
INSERT INTO `t_product_type` VALUES ('10', '配饰', '1');
INSERT INTO `t_product_type` VALUES ('11', '裤子', '1');
INSERT INTO `t_product_type` VALUES ('12', '包包', '1');
INSERT INTO `t_product_type` VALUES ('13', '鞋子', '0');
INSERT INTO `t_product_type` VALUES ('14', '内衣', '1');
INSERT INTO `t_product_type` VALUES ('15', '裙子', '1');
INSERT INTO `t_product_type` VALUES ('16', 'aaa', '1');
INSERT INTO `t_product_type` VALUES ('17', 'zz', '1');
INSERT INTO `t_product_type` VALUES ('18', 'hh', '0');
INSERT INTO `t_product_type` VALUES ('19', 'ddd', '1');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '商品专员');
INSERT INTO `t_role` VALUES ('2', '营销经理');
INSERT INTO `t_role` VALUES ('3', '超级管理员');

-- ----------------------------
-- Table structure for `t_sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `t_sysuser`;
CREATE TABLE `t_sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `login_name` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_sysuser_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sysuser
-- ----------------------------
INSERT INTO `t_sysuser` VALUES ('1', 'admin', 'admin', '123', '13988888888', 'admin@zte.com', '0', '2020-05-18 13:56:27', '3');
INSERT INTO `t_sysuser` VALUES ('2', 'hellboy', 'hellboy', '123', '13999999999', 'hellboy@zte.com', '1', '2020-05-18 13:56:27', '1');
INSERT INTO `t_sysuser` VALUES ('3', 'carreliu', 'CarreLiu', '123456', '18816210252', 'CarreLiu@hotmail.com', '1', '2020-06-05 14:50:12', '2');
INSERT INTO `t_sysuser` VALUES ('18', 'carreliu2', 'CarreLiu2', '123456', '18816210252', 'CarreLiu@hotmail.com', '1', '2020-06-10 12:53:49', '3');
INSERT INTO `t_sysuser` VALUES ('19', 'carreliu', 'CarreLiu3', '123456', '18816210252', 'CarreLiu@hotmail.com', '1', '2020-06-10 12:54:28', '2');
INSERT INTO `t_sysuser` VALUES ('20', 'carreliu', 'CarreLiu4', '123456', '18816210252', 'CarreLiu@hotmail.com', '1', '2020-06-10 12:54:45', '3');
