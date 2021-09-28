/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : ots

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-09-27 14:30:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `aId` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `aUserid` int NOT NULL COMMENT '用户id',
  `aAddress` varchar(80) NOT NULL COMMENT '详细地址',
  `aPostcode` varchar(6) NOT NULL COMMENT '邮编',
  `aConsignee` varchar(20) NOT NULL COMMENT '收货人',
  `aPhone` varchar(11) NOT NULL COMMENT '联系电话',
  `isDefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为默认地址',
  PRIMARY KEY (`aId`),
  KEY `FK_Reference_19` (`aUserid`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`aUserid`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('2', '4', '北京市西城区给对方各个地方电饭锅', '600000', '李浪', '13408754545', '0');
INSERT INTO `address` VALUES ('7', '4', '北京市朝阳区规划的法规和方法规划', '638300', '李浪', '13408267279', '0');
INSERT INTO `address` VALUES ('8', '4', '北京市西城区李浪城北徐公森岛帆高', '638300', '李浪', '13408267279', '0');
INSERT INTO `address` VALUES ('9', '4', '北京市西城区发更大的发鬼地方个', '638300', '李浪', '13408267279', '0');
INSERT INTO `address` VALUES ('11', '4', '天津市河西区洒水大多所545254', '638300', '李浪', '13408267279', '1');
INSERT INTO `address` VALUES ('15', '9', '四川省成都市郫县发生的胡椒粉电视剧', '638300', '李浪', '13408267279', '1');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aID` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `aName` varchar(20) NOT NULL COMMENT '管理员账号',
  `aPsaaWord` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`aID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cId` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `cName` varchar(20) NOT NULL COMMENT '类别名',
  `cState` varchar(1) NOT NULL COMMENT '类别状态',
  `Belong` int NOT NULL COMMENT '归属',
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('2', '女装', '1', '0');
INSERT INTO `category` VALUES ('3', '内衣', '1', '0');
INSERT INTO `category` VALUES ('4', '家居', '1', '0');
INSERT INTO `category` VALUES ('5', '女鞋', '1', '0');
INSERT INTO `category` VALUES ('6', '男鞋', '1', '0');
INSERT INTO `category` VALUES ('7', '箱包', '1', '0');
INSERT INTO `category` VALUES ('8', '母婴', '1', '0');
INSERT INTO `category` VALUES ('9', '童装', '1', '0');
INSERT INTO `category` VALUES ('10', '玩具', '1', '0');
INSERT INTO `category` VALUES ('11', '男装', '1', '0');
INSERT INTO `category` VALUES ('12', '美妆', '1', '0');
INSERT INTO `category` VALUES ('13', '手机', '1', '0');
INSERT INTO `category` VALUES ('14', '数码', '1', '0');
INSERT INTO `category` VALUES ('15', '零食', '1', '0');
INSERT INTO `category` VALUES ('16', '厨具', '1', '0');
INSERT INTO `category` VALUES ('17', '家纺', '1', '0');
INSERT INTO `category` VALUES ('18', '图书音像', '1', '0');
INSERT INTO `category` VALUES ('19', '文具', '1', '0');

-- ----------------------------
-- Table structure for footprint
-- ----------------------------
DROP TABLE IF EXISTS `footprint`;
CREATE TABLE `footprint` (
  `fId` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `fUserId` int NOT NULL COMMENT '类别名',
  `fGoodsId` int NOT NULL COMMENT '类别状态',
  `fReDate` date NOT NULL COMMENT '归属',
  PRIMARY KEY (`fId`),
  KEY `FK_Reference_28` (`fUserId`),
  KEY `FK_Reference_29` (`fGoodsId`),
  CONSTRAINT `FK_Reference_28` FOREIGN KEY (`fUserId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`fGoodsId`) REFERENCES `goods` (`gId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of footprint
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `gId` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `gStoreId` int NOT NULL COMMENT '商家id',
  `gCatId` int NOT NULL,
  `gName` varchar(50) NOT NULL COMMENT '商品名',
  `gPrice` double NOT NULL COMMENT '商品价格',
  `gImage` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `gParameter` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品参数',
  `gNumber` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`gId`),
  KEY `FK_Reference_21` (`gStoreId`),
  KEY `FK_Relationship_6` (`gCatId`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`gStoreId`) REFERENCES `store` (`sId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_22` FOREIGN KEY (`gCatId`) REFERENCES `category` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('9', '1', '7', 'Samsonite/新秀丽行李箱ins网红拉杆箱登机旅行密码箱男女20寸06Q', '1980', '11.png', '品牌名称：Samsonite/新秀丽', '0');
INSERT INTO `goods` VALUES ('19', '1', '2', '法式轻熟风茶歇裙', '299', '12.png', '品牌: THE BEAUTY MEET', '0');
INSERT INTO `goods` VALUES ('20', '1', '18', '鬼谷子全评 张凌翔 编著', '18.2', '13.png', '产品名称：鬼谷子全评', '0');
INSERT INTO `goods` VALUES ('21', '1', '4', '钢制学校图书馆书架家用双面图书音像货架回本资料架阅览室档案架', '350', '14.png', '品牌: 铭威办公家具', '0');
INSERT INTO `goods` VALUES ('22', '1', '6', 'iphone5', '599', null, 'iphone5', '0');
INSERT INTO `goods` VALUES ('23', '1', '8', 'iphone6', '699', null, 'iphone6', '0');
INSERT INTO `goods` VALUES ('25', '1', '11', 'iphone2', '299', null, 'iphone2', '0');
INSERT INTO `goods` VALUES ('26', '1', '3', 'iphone3', '399', null, 'iphone3', '0');
INSERT INTO `goods` VALUES ('27', '1', '4', 'iphone4', '499', null, 'iphone4', '0');
INSERT INTO `goods` VALUES ('28', '1', '15', 'iphone5', '599', null, 'iphone5', '0');
INSERT INTO `goods` VALUES ('29', '1', '17', 'iphone6', '699', null, 'iphone6', '0');
INSERT INTO `goods` VALUES ('31', '1', '16', 'iphone2', '299', null, 'iphone2', '0');
INSERT INTO `goods` VALUES ('32', '1', '14', 'iphone3', '399', null, 'iphone3', '0');
INSERT INTO `goods` VALUES ('33', '1', '13', 'iphone4', '499', null, 'iphone4', '0');
INSERT INTO `goods` VALUES ('34', '1', '5', 'iphone5', '599', null, 'iphone5', '0');
INSERT INTO `goods` VALUES ('35', '1', '10', 'iphone6', '699', null, 'iphone6', '0');

-- ----------------------------
-- Table structure for indent
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent` (
  `iId` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `iUserId` int NOT NULL COMMENT '用户id',
  `iSotreId` int NOT NULL COMMENT '商家id',
  `iCatId` int NOT NULL COMMENT '商品类别',
  `iGoodsId` int NOT NULL COMMENT '商品id',
  `iAddress` varchar(80) NOT NULL COMMENT '收货地址',
  `iMessage` varchar(100) DEFAULT NULL COMMENT '用户留言',
  `iPrice` double NOT NULL COMMENT '订单金额',
  `iState` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '订单状态',
  `iDate` date NOT NULL,
  PRIMARY KEY (`iId`),
  KEY `FK_Reference_24` (`iSotreId`),
  KEY `FK_Reference_25` (`iUserId`),
  KEY `FK_Reference_26` (`iCatId`),
  KEY `FK_Reference_27` (`iGoodsId`),
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`iSotreId`) REFERENCES `store` (`sId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_25` FOREIGN KEY (`iUserId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`iCatId`) REFERENCES `category` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`iGoodsId`) REFERENCES `goods` (`gId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of indent
-- ----------------------------

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `sId` int NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `sName` varchar(20) NOT NULL COMMENT '商店名',
  `sUserId` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`sId`),
  KEY `FK_Reference_20` (`sUserId`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`sUserId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '李家小店', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uId` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(20) NOT NULL COMMENT '用户账号',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `uPhone` varchar(11) NOT NULL COMMENT '联系手机',
  `uEmail` varchar(30) NOT NULL COMMENT '联系邮箱',
  `uLevel` varchar(1) NOT NULL DEFAULT '1' COMMENT '用户等级',
  `uImage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uId`),
  UNIQUE KEY `userName_message` (`userName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'aaaa', 'a52c9dcadd70c29751558dc2a5e42b94', '13408267279', 'liwuxin54@qq.com', '3', null);
INSERT INTO `user` VALUES ('4', 'liwuxin', 'Lilang545264@', '13408267279', 'liwuxin54@qq.com', '2', null);
INSERT INTO `user` VALUES ('5', 'bbbb', 'a52c9dcadd70c29751558dc2a5e42b94', '13408267279', '1184144908@qq.com', '1', null);
INSERT INTO `user` VALUES ('8', '123456', 'a52c9dcadd70c29751558dc2a5e42b94', '13408267279', '1184144908@qq.com', '3', null);
INSERT INTO `user` VALUES ('9', 'lilang', 'a52c9dcadd70c29751558dc2a5e42b94', '13408267279', '1184144908@qq.com', '1', null);
