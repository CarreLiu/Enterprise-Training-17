/*
Navicat MySQL Data Transfer

Source Server         : CarreLiu
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : iting

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-05-07 10:00:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apply`
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `applyId` int(11) NOT NULL AUTO_INCREMENT,
  `applyPerson` varchar(10) DEFAULT NULL,
  `applyPersonIdCard` varchar(18) DEFAULT NULL,
  `applyNum` int(11) DEFAULT NULL,
  `applyDate` date DEFAULT NULL,
  `applyProductName` varchar(20) DEFAULT NULL,
  `applyProductId` int(11) DEFAULT NULL,
  PRIMARY KEY (`applyId`),
  KEY `FK_APPLY_PRODUCT` (`applyProductId`),
  CONSTRAINT `FK_APPLY_PRODUCT` FOREIGN KEY (`applyProductId`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('29', '许丹', '321084199309043222', '50', '2017-12-06', '腾讯公益基金', '1');
INSERT INTO `apply` VALUES ('30', '许丹', '321084199309043222', '55', '2017-12-06', '腾讯公益基金', '1');
INSERT INTO `apply` VALUES ('31', '笑笑', '321084199209033211', '50', '2017-12-06', '腾讯公益基金', '1');
INSERT INTO `apply` VALUES ('32', '张松', '22222222222222222X', '60', '2017-12-06', '腾讯公益基金', '1');
INSERT INTO `apply` VALUES ('33', '许丹', '321084199309043222', '30', '2017-12-06', '淘宝天天花', '4');
INSERT INTO `apply` VALUES ('35', '许丹', '321084199309043222', '50', '2017-12-06', '京东家电', '5');
INSERT INTO `apply` VALUES ('36', '许丹', '321084199309043222', '30', '2017-12-06', '京东家电', '5');

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyId` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(20) DEFAULT NULL,
  `financingInReturn` double DEFAULT NULL,
  `companyDetail` text,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '腾讯', '5', '这是一个大公司');
INSERT INTO `company` VALUES ('5', '万达', '3', '房产');
INSERT INTO `company` VALUES ('6', ' 中国人民银行', '3', '国有银行');
INSERT INTO `company` VALUES ('28', 'adfasdfa3', '12', 'adfasdfaf');

-- ----------------------------
-- Table structure for `lendingperiod`
-- ----------------------------
DROP TABLE IF EXISTS `lendingperiod`;
CREATE TABLE `lendingperiod` (
  `lendingPeriodId` int(11) NOT NULL AUTO_INCREMENT,
  `period` varchar(20) DEFAULT NULL,
  `periodStatus` int(2) NOT NULL,
  PRIMARY KEY (`lendingPeriodId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lendingperiod
-- ----------------------------
INSERT INTO `lendingperiod` VALUES ('1', '6个月', '1');
INSERT INTO `lendingperiod` VALUES ('2', '12', '1');
INSERT INTO `lendingperiod` VALUES ('3', '8', '1');
INSERT INTO `lendingperiod` VALUES ('4', '18', '1');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(50) NOT NULL,
  `content` longtext NOT NULL,
  `createAt` date DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `heading` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('15', '融动紫金网大创E社区首次创业公开课成功举办', '融动紫金网大创E社区首次创业公开课成功举办，南京市金融办、人社局、财政局相关领导出席会议，大学生创业企业家参会并且参与互动讨论。', '2020-04-07', 'upload/images/1586250023166.jpg', '0');
INSERT INTO `news` VALUES ('16', '联交所成功承办中国（南京）软件谷 321人才....', '2014年4月19日下午，“中国（南京）软件谷321人才暨投融资对接会”在联交所312会议室成功举行。本次活动由中国（南京）软件谷管委会主办，南京联交所、软件谷科技人才局承办，紫金科创、市创投协会协办。市科技创业家联合会、红杉资本、达晨创投、深创投、九鼎投资、苏商投资、苏豪投资、紫金科创、市高风投等十余家创投机构投资专家及三十余家企业代表参加了本次对接活动。', '2020-01-01', 'upload/images/1586250522430.jpg', '0');
INSERT INTO `news` VALUES ('17', '投贷保联盟•融动紫金•麒麟科技创新园专场投融资对接会', '2014年5月23日下午，“投贷保联盟•融动紫金•麒麟科技创新园专场投融资对接会”在南京联交所成功举行。本次活动由南京市金融办、南京市麒麟科技创新园管委会主办，南京联交所承办。南京市金融办副主任范慧娟、南京麒麟科技创新园管委会副主任李振楚出席活动，赛富投资基金、九鼎投资、毅达资本、苏商资本等知名创投机构和市投贷保联盟二十多家成员企业参会。', '2020-02-06', 'upload/images/1586250552805.png', '0');
INSERT INTO `news` VALUES ('18', '助推小微企业发展融资模式专场推介会', '7月23日上午，由市金融办、南京金融发展促进会联合主办、南京联交所协办的“创新金融服务，落实普惠金融”助推小微企业发展融资模式推介会在联交所一楼交易大厅召开。市政协主席沈健、市委常委、常务副市长刘以安、省银监局副局长丁灿、市金融促进会会长白世春、紫金投资集团董事长王海涛、副总经理管毅等领导出席会议，市金融办主任刘永彪主持会议...', '2020-04-06', 'upload/images/1586250592015.jpg', '1');
INSERT INTO `news` VALUES ('37', '交流交流', '拉进来看', '2020-04-03', 'upload/images/1586254149891.jpg', '0');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(20) DEFAULT NULL,
  `primeLendingRateFrom` double DEFAULT NULL,
  `primeLendingRateTo` double DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `financingAmountFrom` int(11) DEFAULT NULL,
  `financingAmountTo` int(11) DEFAULT NULL,
  `productTypeId` int(11) DEFAULT NULL,
  `lendingPeriodId` int(11) DEFAULT NULL,
  `linkMan` varchar(20) DEFAULT NULL,
  `ownedBank` varchar(20) DEFAULT NULL,
  `bankIcon` varchar(100) DEFAULT NULL,
  `productDescription` text,
  `createTime` date DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `FK_Pro_company` (`companyId`),
  KEY `FK_PRO_PROTYPE` (`productTypeId`),
  KEY `FK_PRO_LEADINGPRI` (`lendingPeriodId`),
  CONSTRAINT `FK_PRO_LEADINGPRI` FOREIGN KEY (`lendingPeriodId`) REFERENCES `lendingperiod` (`lendingPeriodId`),
  CONSTRAINT `FK_PRO_PROTYPE` FOREIGN KEY (`productTypeId`) REFERENCES `producttype` (`productTypeId`),
  CONSTRAINT `FK_Pro_company` FOREIGN KEY (`companyId`) REFERENCES `company` (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '腾讯公益基金', '14', '19', '1', '45', '60', '1', '1', '马先生', '中国银行', null, '<i><b>给年轻人一个创业的机会</b></i>', '2020-04-04');
INSERT INTO `product` VALUES ('4', '淘宝天天花', '12', '18', '5', '25', '45', '4', '1', '王先生', 'icbc', null, 'llllllllll', '2017-12-06');
INSERT INTO `product` VALUES ('5', '京东家电', '14', '15', '6', '0', '50', '2', '1', 'aaaaa', ' 中国人民银行', null, 'aaaaaaaaaaaaaaaaaaaaaaaadfffdddaadffdadfadfasdfdf<br>', '2017-12-06');
INSERT INTO `product` VALUES ('7', '啊啊啊啊', '3', '4', '1', '0', '50', '2', '1', '发达', '通风管道', null, '第三方啊', '2017-12-07');
INSERT INTO `product` VALUES ('29', '看链接离开家', '1', '3', '5', '210', '250', '3', '3', '发送', 'daffodil', null, '聚隆科技看链接', '2020-03-30');
INSERT INTO `product` VALUES ('31', '百度金融', '0', '1', '1', '210', '250', '1', '1', '李彦宏', '百度银行', null, '解决了空间大幅打发', '2020-04-03');

-- ----------------------------
-- Table structure for `producttype`
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `productTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `productTypeName` varchar(20) DEFAULT NULL,
  `productTypeStatus` int(2) NOT NULL,
  PRIMARY KEY (`productTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '信用贷', '0');
INSERT INTO `producttype` VALUES ('2', '抵押贷', '1');
INSERT INTO `producttype` VALUES ('3', '随便贷', '1');
INSERT INTO `producttype` VALUES ('4', '天天贷', '1');

-- ----------------------------
-- Table structure for `sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `loginName` varchar(20) NOT NULL,
  `loginPassword` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('1', '超级管理员', 'mike', '123');
INSERT INTO `sysuser` VALUES ('2', '管理员', 'admin', 'aaaa');
INSERT INTO `sysuser` VALUES ('3', '张三', 'zhangsan', '12345');
INSERT INTO `sysuser` VALUES ('4', '刘家乐', 'carreliu', '123456');
