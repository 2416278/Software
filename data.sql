<<<<<<< HEAD
/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.16 : Database - store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`store` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `store`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `img` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `price` double DEFAULT '0' COMMENT '购买价格',
  `amount` int(11) DEFAULT '1' COMMENT '数量',
  `isPay` tinyint(1) DEFAULT '0' COMMENT '是否已支付',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '加入时间',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`img`,`name`,`price`,`amount`,`isPay`,`createtime`,`username`) values 
(97,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',169,6,1,'2023-09-17 17:51:55','ssssss'),
(115,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,3,1,'2023-09-17 16:50:30','hhhhhh'),
(116,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,2,0,'2023-09-17 16:51:40','hhhhhh'),
(125,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,2,1,'2023-09-17 17:51:57','ssssss'),
(145,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',169,2,1,'2023-09-17 18:17:03','ssssss'),
(146,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,3,1,'2023-09-17 18:17:11','ssssss');

/*Table structure for table `sys_goods` */

DROP TABLE IF EXISTS `sys_goods`;

CREATE TABLE `sys_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `make` varchar(32) DEFAULT NULL COMMENT '生产厂家',
  `maketime` date DEFAULT NULL COMMENT '生产日期',
  `size` varchar(32) DEFAULT NULL COMMENT '型号',
  `buyprice` double DEFAULT '0' COMMENT '进货价',
  `sellprice` double DEFAULT '0' COMMENT '售价',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '封面图片',
  `type_id` int(11) DEFAULT NULL COMMENT '类目名',
  `quantity` int(11) DEFAULT '0' COMMENT '购买数量',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `sys_goods_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `sys_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `sys_goods` */

insert  into `sys_goods`(`id`,`name`,`make`,`maketime`,`size`,`buyprice`,`sellprice`,`count`,`img`,`type_id`,`quantity`) values 
(2,'苏泊尔电饭煲家用3L升迷你智能小型煮饭锅','浙江绍兴苏泊尔家居用品','2023-08-07','CFXB30FC82960',100,169,0,'1693061847158.jpg',2,0),
(3,'格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖','格力','2023-08-15','KFR35GWD1N1',3244,3599,0,'1692795266987.jpg',2,0),
(4,'三星触屏手机','三星','2022-02-18','DH4389850W1',1000,1299,0,'1693327751672.jpg',1,0),
(22,'天丝针织开衫女短款上衣坎肩','织小姐','2023-08-01','',50,69,16,'1693571422140.png',3,0),
(23,'日系简约vintage潮牌可束脚阔腿裤','','2023-08-31','TY789221DY',50,69,12,'1694523115043.jpg',3,0);

/*Table structure for table `sys_goodstype` */

DROP TABLE IF EXISTS `sys_goodstype`;

CREATE TABLE `sys_goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `notes` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_goodstype` */

insert  into `sys_goodstype`(`id`,`name`,`notes`) values 
(1,'电子产品',NULL),
(2,'家用电器',NULL),
(3,'服饰',NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址',
  `parent_id` int(11) DEFAULT '-1' COMMENT '父菜单编号 1表示一级菜单',
  `seq` int(11) DEFAULT NULL COMMENT '排序字段',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parent_id`,`seq`,`create_time`) values 
(1,'系统管理','',-1,10,'2023-08-08 18:36:15'),
(2,'用户管理','/sys/userServlet?action=list',1,11,'2023-08-08 18:36:19'),
(3,'基础管理','',-1,20,'2023-08-09 18:03:30'),
(4,'菜单管理','/sys/menuServlet?action=list',1,12,'2023-08-08 18:36:24'),
(5,'购物车','/store/cartServlet?action=list',3,21,'2023-08-23 22:29:34'),
(6,'角色管理','/sys/roleServlet?action=list',1,13,'2023-08-09 17:19:44'),
(8,'商品展示','/store/showServlet?action=list',3,22,'2023-08-25 22:46:35'),
(9,'订单查询','/store/orderServlet?action=list',3,22,'2023-08-25 16:47:35'),
(12,'商品管理','/sys/goodsServlet?action=list ',1,14,'2023-08-09 18:03:14');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `notes` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '铜牌用户' COMMENT '角色',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `login_error_count` int(11) DEFAULT '0' COMMENT '密码错误的次数',
  `last_login_error_time` datetime DEFAULT NULL COMMENT '最后一次登陆错误时间',
  `isLocked` int(11) DEFAULT '0' COMMENT '是否锁定中',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`password`,`notes`,`time`,`login_error_count`,`last_login_error_time`,`isLocked`) values 
(1,'admin','123@Admin','超级管理员','2023-09-28 20:05:56',0,'2023-09-28 00:00:00',0),
(8,'xiaoming','456User@','铜牌客户','2023-09-17 14:27:01',0,'2023-09-17 14:21:52',1),
(9,'wangwu','123User@','银牌客户','2023-09-17 14:29:42',0,'2023-09-17 14:29:43',1),
(22,'hhhhhh','456User@','金牌客户','2023-09-17 16:26:47',0,'2023-09-17 00:00:00',0),
(27,'xiaohong','123User@','铜牌客户','2023-09-12 20:33:28',0,NULL,0),
(28,'ssssss','456User@','铜牌用户','2023-09-17 14:26:22',0,NULL,0),
(29,'admin1','1a#L5675',NULL,'2023-09-17 16:44:38',0,NULL,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`,`create_time`) values 
(25,2,2,'2023-08-09 17:04:59'),
(26,2,4,'2023-08-09 17:04:59'),
(27,2,6,'2023-08-09 17:04:59'),
(28,2,12,'2023-08-09 17:04:59'),
(29,10,5,'2023-08-10 21:26:44'),
(30,10,8,'2023-08-10 21:26:44'),
(31,10,9,'2023-08-10 21:26:44'),
(46,4,5,'2023-08-29 23:02:27'),
(47,4,8,'2023-08-29 23:02:27'),
(48,4,9,'2023-08-29 23:02:27'),
(72,7,5,'2023-09-01 20:32:50'),
(73,7,8,'2023-09-01 20:32:50'),
(74,7,9,'2023-09-01 20:32:50'),
(88,1,2,'2023-09-12 20:38:02'),
(89,1,4,'2023-09-12 20:38:02'),
(90,1,6,'2023-09-12 20:38:02'),
(91,1,12,'2023-09-12 20:38:02'),
(92,1,8,'2023-09-12 20:38:02'),
(93,8,5,'2023-09-12 20:39:02'),
(94,8,8,'2023-09-12 20:39:02'),
(95,8,9,'2023-09-12 20:39:02'),
(96,9,5,'2023-09-17 14:18:20'),
(97,9,8,'2023-09-17 14:18:20'),
(98,9,9,'2023-09-17 14:18:20'),
(99,22,5,'2023-09-17 14:18:25'),
(100,22,8,'2023-09-17 14:18:25'),
(101,22,9,'2023-09-17 14:18:25'),
(102,27,5,'2023-09-17 14:18:38'),
(103,27,8,'2023-09-17 14:18:38'),
(104,27,9,'2023-09-17 14:18:38'),
(105,28,5,'2023-09-17 14:18:44'),
(106,28,8,'2023-09-17 14:18:44'),
(107,28,9,'2023-09-17 14:18:44');

/*Table structure for table `sys_type` */

DROP TABLE IF EXISTS `sys_type`;

CREATE TABLE `sys_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_type` */

insert  into `sys_type`(`id`,`name`) values 
(1,'电子产品'),
(2,'家用电器'),
(3,'服饰');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `usename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `grade` varchar(32) DEFAULT NULL COMMENT '用户级别 金牌银牌铜牌',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话(收货电话）',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `total` double DEFAULT '0' COMMENT '消费总金额',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`uid`,`usename`,`grade`,`phone`,`email`,`create_time`,`total`) values 
(5,'hhhhhh','金牌客户','13245678841','13080759571@163.com','2023-07-10',232411.1),
(6,'wangwu','银牌客户','13545678901','2416273879@qq.com','2023-06-27',2356),
(15,'xiaoming','铜牌客户','13056423685','13080759571@163.com','2023-08-12',154666),
(41,'xiaohong','银牌客户','15945856392','2416273879@qq.com','2023-09-05',123.4),
(42,'ssssss','铜牌客户','13052647891','2416273879@qq.com','2023-08-15',87.9),
(43,'admin1',NULL,NULL,'2416273879@qq.com',NULL,0);

/*Table structure for table `user_order` */

DROP TABLE IF EXISTS `user_order`;

CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `img` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `goodsName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片',
  `total` double DEFAULT '0' COMMENT '总价',
  `amount` int(11) DEFAULT NULL COMMENT '商品数量',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `name` varchar(32) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(64) DEFAULT NULL COMMENT '收货电话',
  `address` varchar(64) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

/*Data for the table `user_order` */

insert  into `user_order`(`id`,`img`,`goodsName`,`total`,`amount`,`time`,`name`,`phone`,`address`) values 
(1,'1692795274130.png','小天鹅洗衣机全自动家用10公斤kg滚筒洗烘一体',6198,2,'2023-08-25 13:19:54','ssssss','13025224620',NULL),
(97,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',1014,6,'2023-09-17 15:11:33','hhhhhh',NULL,NULL),
(99,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',10797,3,'2023-09-17 16:50:30','hhhhhh',NULL,NULL),
(102,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',1014,6,'2023-09-17 17:51:55','ssssss',NULL,NULL),
(103,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',7198,2,'2023-09-17 17:51:57','ssssss',NULL,NULL),
(123,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',338,2,'2023-09-17 18:17:03','ssssss',NULL,NULL),
(124,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',10797,3,'2023-09-17 18:17:11','ssssss',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
=======
/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.16 : Database - store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`store` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `store`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `img` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `price` double DEFAULT '0' COMMENT '购买价格',
  `amount` int(11) DEFAULT '1' COMMENT '数量',
  `isPay` tinyint(1) DEFAULT '0' COMMENT '是否已支付',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '加入时间',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`img`,`name`,`price`,`amount`,`isPay`,`createtime`,`username`) values 
(97,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',169,6,1,'2023-09-17 17:51:55','ssssss'),
(115,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,3,1,'2023-09-17 16:50:30','hhhhhh'),
(116,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,2,0,'2023-09-17 16:51:40','hhhhhh'),
(125,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,2,1,'2023-09-17 17:51:57','ssssss'),
(145,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',169,2,1,'2023-09-17 18:17:03','ssssss'),
(146,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',3599,3,1,'2023-09-17 18:17:11','ssssss');

/*Table structure for table `sys_goods` */

DROP TABLE IF EXISTS `sys_goods`;

CREATE TABLE `sys_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `make` varchar(32) DEFAULT NULL COMMENT '生产厂家',
  `maketime` date DEFAULT NULL COMMENT '生产日期',
  `size` varchar(32) DEFAULT NULL COMMENT '型号',
  `buyprice` double DEFAULT '0' COMMENT '进货价',
  `sellprice` double DEFAULT '0' COMMENT '售价',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '封面图片',
  `type_id` int(11) DEFAULT NULL COMMENT '类目名',
  `quantity` int(11) DEFAULT '0' COMMENT '购买数量',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `sys_goods_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `sys_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `sys_goods` */

insert  into `sys_goods`(`id`,`name`,`make`,`maketime`,`size`,`buyprice`,`sellprice`,`count`,`img`,`type_id`,`quantity`) values 
(2,'苏泊尔电饭煲家用3L升迷你智能小型煮饭锅','浙江绍兴苏泊尔家居用品','2023-08-07','CFXB30FC82960',100,169,0,'1693061847158.jpg',2,0),
(3,'格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖','格力','2023-08-15','KFR35GWD1N1',3244,3599,0,'1692795266987.jpg',2,0),
(4,'三星触屏手机','三星','2022-02-18','DH4389850W1',1000,1299,0,'1693327751672.jpg',1,0),
(22,'天丝针织开衫女短款上衣坎肩','织小姐','2023-08-01','',50,69,16,'1693571422140.png',3,0),
(23,'日系简约vintage潮牌可束脚阔腿裤','','2023-08-31','TY789221DY',50,69,12,'1694523115043.jpg',3,0);

/*Table structure for table `sys_goodstype` */

DROP TABLE IF EXISTS `sys_goodstype`;

CREATE TABLE `sys_goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `notes` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_goodstype` */

insert  into `sys_goodstype`(`id`,`name`,`notes`) values 
(1,'电子产品',NULL),
(2,'家用电器',NULL),
(3,'服饰',NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址',
  `parent_id` int(11) DEFAULT '-1' COMMENT '父菜单编号 1表示一级菜单',
  `seq` int(11) DEFAULT NULL COMMENT '排序字段',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parent_id`,`seq`,`create_time`) values 
(1,'系统管理','',-1,10,'2023-08-08 18:36:15'),
(2,'用户管理','/sys/userServlet?action=list',1,11,'2023-08-08 18:36:19'),
(3,'基础管理','',-1,20,'2023-08-09 18:03:30'),
(4,'菜单管理','/sys/menuServlet?action=list',1,12,'2023-08-08 18:36:24'),
(5,'购物车','/store/cartServlet?action=list',3,21,'2023-08-23 22:29:34'),
(6,'角色管理','/sys/roleServlet?action=list',1,13,'2023-08-09 17:19:44'),
(8,'商品展示','/store/showServlet?action=list',3,22,'2023-08-25 22:46:35'),
(9,'订单查询','/store/orderServlet?action=list',3,22,'2023-08-25 16:47:35'),
(12,'商品管理','/sys/goodsServlet?action=list ',1,14,'2023-08-09 18:03:14');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `notes` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '铜牌用户' COMMENT '角色',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `login_error_count` int(11) DEFAULT '0' COMMENT '密码错误的次数',
  `last_login_error_time` datetime DEFAULT NULL COMMENT '最后一次登陆错误时间',
  `isLocked` int(11) DEFAULT '0' COMMENT '是否锁定中',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`password`,`notes`,`time`,`login_error_count`,`last_login_error_time`,`isLocked`) values 
(1,'admin','123@Admin','超级管理员','2023-09-28 20:05:56',0,'2023-09-28 00:00:00',0),
(8,'xiaoming','456User@','铜牌客户','2023-09-17 14:27:01',0,'2023-09-17 14:21:52',1),
(9,'wangwu','123User@','银牌客户','2023-09-17 14:29:42',0,'2023-09-17 14:29:43',1),
(22,'hhhhhh','456User@','金牌客户','2023-09-17 16:26:47',0,'2023-09-17 00:00:00',0),
(27,'xiaohong','123User@','铜牌客户','2023-09-12 20:33:28',0,NULL,0),
(28,'ssssss','456User@','铜牌用户','2023-09-17 14:26:22',0,NULL,0),
(29,'admin1','1a#L5675',NULL,'2023-09-17 16:44:38',0,NULL,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`,`create_time`) values 
(25,2,2,'2023-08-09 17:04:59'),
(26,2,4,'2023-08-09 17:04:59'),
(27,2,6,'2023-08-09 17:04:59'),
(28,2,12,'2023-08-09 17:04:59'),
(29,10,5,'2023-08-10 21:26:44'),
(30,10,8,'2023-08-10 21:26:44'),
(31,10,9,'2023-08-10 21:26:44'),
(46,4,5,'2023-08-29 23:02:27'),
(47,4,8,'2023-08-29 23:02:27'),
(48,4,9,'2023-08-29 23:02:27'),
(72,7,5,'2023-09-01 20:32:50'),
(73,7,8,'2023-09-01 20:32:50'),
(74,7,9,'2023-09-01 20:32:50'),
(88,1,2,'2023-09-12 20:38:02'),
(89,1,4,'2023-09-12 20:38:02'),
(90,1,6,'2023-09-12 20:38:02'),
(91,1,12,'2023-09-12 20:38:02'),
(92,1,8,'2023-09-12 20:38:02'),
(93,8,5,'2023-09-12 20:39:02'),
(94,8,8,'2023-09-12 20:39:02'),
(95,8,9,'2023-09-12 20:39:02'),
(96,9,5,'2023-09-17 14:18:20'),
(97,9,8,'2023-09-17 14:18:20'),
(98,9,9,'2023-09-17 14:18:20'),
(99,22,5,'2023-09-17 14:18:25'),
(100,22,8,'2023-09-17 14:18:25'),
(101,22,9,'2023-09-17 14:18:25'),
(102,27,5,'2023-09-17 14:18:38'),
(103,27,8,'2023-09-17 14:18:38'),
(104,27,9,'2023-09-17 14:18:38'),
(105,28,5,'2023-09-17 14:18:44'),
(106,28,8,'2023-09-17 14:18:44'),
(107,28,9,'2023-09-17 14:18:44');

/*Table structure for table `sys_type` */

DROP TABLE IF EXISTS `sys_type`;

CREATE TABLE `sys_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_type` */

insert  into `sys_type`(`id`,`name`) values 
(1,'电子产品'),
(2,'家用电器'),
(3,'服饰');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `usename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `grade` varchar(32) DEFAULT NULL COMMENT '用户级别 金牌银牌铜牌',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话(收货电话）',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `total` double DEFAULT '0' COMMENT '消费总金额',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`uid`,`usename`,`grade`,`phone`,`email`,`create_time`,`total`) values 
(5,'hhhhhh','金牌客户','13245678841','13080759571@163.com','2023-07-10',232411.1),
(6,'wangwu','银牌客户','13545678901','2416273879@qq.com','2023-06-27',2356),
(15,'xiaoming','铜牌客户','13056423685','13080759571@163.com','2023-08-12',154666),
(41,'xiaohong','银牌客户','15945856392','2416273879@qq.com','2023-09-05',123.4),
(42,'ssssss','铜牌客户','13052647891','2416273879@qq.com','2023-08-15',87.9),
(43,'admin1',NULL,NULL,'2416273879@qq.com',NULL,0);

/*Table structure for table `user_order` */

DROP TABLE IF EXISTS `user_order`;

CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `img` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `goodsName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片',
  `total` double DEFAULT '0' COMMENT '总价',
  `amount` int(11) DEFAULT NULL COMMENT '商品数量',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `name` varchar(32) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(64) DEFAULT NULL COMMENT '收货电话',
  `address` varchar(64) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

/*Data for the table `user_order` */

insert  into `user_order`(`id`,`img`,`goodsName`,`total`,`amount`,`time`,`name`,`phone`,`address`) values 
(1,'1692795274130.png','小天鹅洗衣机全自动家用10公斤kg滚筒洗烘一体',6198,2,'2023-08-25 13:19:54','ssssss','13025224620',NULL),
(97,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',1014,6,'2023-09-17 15:11:33','hhhhhh',NULL,NULL),
(99,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',10797,3,'2023-09-17 16:50:30','hhhhhh',NULL,NULL),
(102,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',1014,6,'2023-09-17 17:51:55','ssssss',NULL,NULL),
(103,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',7198,2,'2023-09-17 17:51:57','ssssss',NULL,NULL),
(123,'1693061847158.jpg','苏泊尔电饭煲家用3L升迷你智能小型煮饭锅',338,2,'2023-09-17 18:17:03','ssssss',NULL,NULL),
(124,'1692795266987.jpg','格力空调云锦Ⅱ新能效2匹圆柱柜机家用空调冷暖',10797,3,'2023-09-17 18:17:11','ssssss',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
>>>>>>> 7135b2758fafe34c3254fdf6001ddcf70b7d1ba8
