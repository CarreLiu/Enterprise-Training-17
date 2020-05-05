/*
Navicat MySQL Data Transfer

Source Server         : CarreLiu
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-05-06 02:29:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `content` longtext,
  `createDate` date DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `sid` (`sid`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '慎选程序设计语言', '初学者选择程序设计语言需要谨慎对待。软件开发不仅仅是掌握一门编程语言了事，它还需要其他很多方面的背景知识。软件开发也不仅仅局限于某几个领域，而是已经渗透到了各行各业几乎每一个角落。', '2020-05-04', '1');
INSERT INTO `blog` VALUES ('2', '多读好书', '比尔盖茨是一个饱读群书的人。虽然没有读完大学，但九岁的时候比尔盖茨就已经读完了所有的百科全书，所以他精通天文、历史、地理等等各类学科，可以说比尔?茨不仅是当今世界上金钱的首富，而且也可以称得上是知识的巨富。', '2020-05-04', '1');
INSERT INTO `blog` VALUES ('3', '使用合适的工具', '工欲善其事必先利其器。软件开发包含各种各样的活动，需求收集分析、建立用例模型、建立分析设计模型、编程实现、调试程序、自动化测试、持续集成等等，没有工具帮忙可以说是寸步难行。工具可以提高开发效率，使软件的质量更高BUG更少。', '2020-05-04', '2');
INSERT INTO `blog` VALUES ('4', '多参考程序代码  ', '程序代码是软件开发最重要的成果之一，其中渗透了程序员的思想与灵魂。许多人被《仙剑奇侠传》中凄美的爱情故事感动,悲剧的结局更有一种缺憾美。为什么要以悲剧结尾？据说是因为写《仙剑奇侠传》的程序员失恋而安排了这样的结局，他把自己的感觉融入到游戏中，却让众多的仙剑迷扼腕叹息。', '2020-05-04', '2');
INSERT INTO `blog` VALUES ('5', '加强英文阅读能力', '对学习编程来说，不要求英语, 但不能一点不会,。最起码像Java API文档(参考文献[4.4])这些东西还是要能看懂的,连猜带懵都可以；旁边再开启一个\"金山词霸\"。看多了就会越来越熟练。在学Java的同时学习英文，一箭双雕多好。另外好多软件需要到英文网站下载，你要能够找到它们，这些是最基本的要求。', '2020-05-04', '3');
INSERT INTO `blog` VALUES ('6', '避免返回匿名类，局部类或者内部类 ', 'Swing开发者大概有很多快捷键来为他们成百上千个匿名类创建代码。在很多情况下，创建这些代码并不难因为你可以将他们依附于接口，而不用自找麻烦来思考整个SPI子类的生命周期。', '2020-05-04', '3');
INSERT INTO `blog` VALUES ('7', 'equals()的捷径', '这是一个简单易用的东西。在大型对象图表中，如果你所有的对象的equals()方法都先使用”dirt cheaply” 比较一下他们的身份类型，那么你可以获得很大的性能提升。', '2020-05-04', '4');
INSERT INTO `blog` VALUES ('8', 'Java中的多态性理解', 'Java中除了static方法和final方法（private方法本质上属于final方法，因为不能被子类访问）之外，其它所有的方法都是动态绑定，这意味着通常情况下，我们不必判定是否应该进行动态绑定—它会自动发生。', '2020-05-04', '4');
INSERT INTO `blog` VALUES ('9', 'is-a关系和is-like-a关系', 'is-a关系属于纯继承，即只有在基类中已经建立的方法才可以在子类中被覆盖，is-like-a关系：子类扩展了基类接口。它有着相同的基本接口，但是他还具有由额外方法实现的其他特性。', '2020-05-04', '5');
INSERT INTO `blog` VALUES ('10', '动态代理', 'Java的动态代理比代理的思想更向前迈进了一步，因为它可以动态地创建代理并动态地处理对所代理方法的调用。', '2020-05-04', '6');
INSERT INTO `blog` VALUES ('12', 'jkjkkk', '金风科技酸辣粉就开始大劫案垃圾分类肯德基阿克苏加', '2020-05-05', '2');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) NOT NULL,
  `cdesc` varchar(500) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cname` (`cname`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'Java', 'a、JAVA程序设计</br>\nb、SQL SERVER 高级应用\n</br>c、jQuery高级编程\n</br>d、HTML5与CSS3开发</br>\ne、Oracle数据库应用', '1');
INSERT INTO `course` VALUES ('2', 'Web前端', 'a、HTML网页基础\n</br>b、CSS语言\n</br>c、PS设计\n</br>d、UI设计', '1');
INSERT INTO `course` VALUES ('3', 'Android', 'a、JAVA开发基础</br>\nb、Android基础</br>\nc、Android高级技术\n</br>d、UI设计', '1');
INSERT INTO `course` VALUES ('4', 'Phython', 'a、JAVA程序设计\n</br>b、SQL SERVER 高级应用\n</br>c、jQuery高级编程\n</br>d、HTML5与CSS3开发\n</br>e、Oracle数据库应用', '1');
INSERT INTO `course` VALUES ('5', '大数据', 'a、JAVA程序设计\n</br>b、SQL SERVER 高级应用\n</br>c、jQuery高级编程\n<br/>d、HTML5与CSS3开发</br>\ne、Oracle数据库应用', '1');
INSERT INTO `course` VALUES ('6', 'C++', 'a、JAVA程序设计\n</br>b、SQL SERVER 高级应用\n</br>c、jQuery高级编程</br>\nd、HTML5与CSS3开发</br>\ne、Oracle数据库应用', '1');

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(20) NOT NULL,
  `gdesc` varchar(500) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `gname` (`gname`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', 'WBS17091', '中兴软件WBS17091班', '1');
INSERT INTO `grade` VALUES ('2', 'WBS17081', '中兴软件WBS17081班', '1');
INSERT INTO `grade` VALUES ('3', 'WBS17071', '中兴软件WBS17071班', '0');
INSERT INTO `grade` VALUES ('4', 'WBS17072', '中兴软件WBS17072班', '0');
INSERT INTO `grade` VALUES ('5', 'WBS17082', '中兴软件WBS17082班', '1');
INSERT INTO `grade` VALUES ('6', 'WBS17101', '中兴软件WBS17101班', '1');
INSERT INTO `grade` VALUES ('7', 'WBS17102', '中兴软件WBS17102班', '1');
INSERT INTO `grade` VALUES ('8', 'WBS17111', '中兴软件WBS17111班', '1');
INSERT INTO `grade` VALUES ('9', 'WBS17112', '中兴软件WBS17112班', '1');
INSERT INTO `grade` VALUES ('10', 'WBS17092', '中兴软件WBS17092班', '1');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `username` (`username`),
  KEY `gid` (`gid`),
  KEY `cid` (`cid`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `grade` (`gid`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'aaa', '123', 'aaa', '0', '20', '1', '2');
INSERT INTO `student` VALUES ('2', 'bbb', '123', 'bbb', '1', '20', '2', '1');
INSERT INTO `student` VALUES ('3', 'ccc', '123', 'ccc', '0', '20', '1', '2');
INSERT INTO `student` VALUES ('4', 'ddd', '123', 'ddd', '1', '20', '1', '1');
INSERT INTO `student` VALUES ('5', 'eee', '123', 'eee', '0', '20', '2', '2');
INSERT INTO `student` VALUES ('6', 'fff', '123', 'fff', '1', '20', '1', '2');
INSERT INTO `student` VALUES ('55', 'Kris', '202cb962ac59075b964b07152d234b70', '吴亦凡', '0', '30', '2', '1');
INSERT INTO `student` VALUES ('56', 'Z.TAO', '202cb962ac59075b964b07152d234b70', '黄子韬', '0', '27', '2', '1');
INSERT INTO `student` VALUES ('57', 'Lu Han', '202cb962ac59075b964b07152d234b70', '鹿晗', '0', '30', '2', '1');
INSERT INTO `student` VALUES ('58', 'Lil Ghost', '202cb962ac59075b964b07152d234b70', '王琳凯', '0', '21', '2', '1');
INSERT INTO `student` VALUES ('59', 'Roy', '202cb962ac59075b964b07152d234b70', '王源', '0', '20', '2', '1');
INSERT INTO `student` VALUES ('60', 'Jackson', '202cb962ac59075b964b07152d234b70', '易烊千玺', '0', '20', '2', '1');
INSERT INTO `student` VALUES ('61', 'Karry', '202cb962ac59075b964b07152d234b70', '王俊凯', '0', '21', '2', '1');
INSERT INTO `student` VALUES ('62', 'Kiku', '202cb962ac59075b964b07152d234b70', '鞠婧祎', '1', '26', '2', '1');
INSERT INTO `student` VALUES ('63', 'Reyi', '202cb962ac59075b964b07152d234b70', '刘人语', '1', '19', '2', '1');
INSERT INTO `student` VALUES ('64', 'Fair Xing', '202cb962ac59075b964b07152d234b70', '邢菲', '1', '26', '2', '1');
INSERT INTO `student` VALUES ('65', 'Esther', '202cb962ac59075b964b07152d234b70', '虞书欣', '1', '25', '2', '1');

-- ----------------------------
-- Table structure for `sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('1', 'carreliu', '123456', '1');
INSERT INTO `sysuser` VALUES ('2', 'fdsf', '123', '1');
