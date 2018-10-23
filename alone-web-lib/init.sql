/*  hahaha
Navicat MySQL Data Transfer

Source Server         : myself
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : alone_web

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-06-09 17:22:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
  `id` varchar(200) NOT NULL COMMENT '权限id',
  `name` varchar(100) NOT NULL COMMENT '权限名',
  `sort` int(10) NOT NULL COMMENT '排序',
  `url` varchar(100) NOT NULL COMMENT '菜单的连接地址',
  `parent_id` varchar(200) DEFAULT NULL COMMENT '父级菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES ('1', '项目管理', '1', '', null);
INSERT INTO `admin_permission` VALUES ('101', '项目一', '101', '../manager/case', '1');
INSERT INTO `admin_permission` VALUES ('102', '项目二', '102', '../manager/case2', '1');
INSERT INTO `admin_permission` VALUES ('2', '权限管理', '2', '', null);
INSERT INTO `admin_permission` VALUES ('201', '用户管理', '201', '../adminUser/show', '2');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `role_id` varchar(200) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('cd466e8a8f4e6f08f503721e0e97cd04', '专员');
INSERT INTO `admin_role` VALUES ('super', '超级管理员');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `user_id` varchar(200) NOT NULL,
  `role_id` varchar(200) NOT NULL COMMENT '角色id',
  `username` varchar(100) NOT NULL COMMENT '用户登录的用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `salt` varchar(200) NOT NULL COMMENT '盐',
  `locked` int(1) NOT NULL DEFAULT '0' COMMENT '账户的状态 0表示正常 1表示锁住',
  `del_flg` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识 0表示未删除 1表示删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_index` (`username`),
  KEY `admin_role_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('170e68e2c951165db3cd286ced1c6bb1', 'super', 'xiaomei', '002f07928e2feef384b291726377ca04', 'hKLwP5cWgIeMWw7QNYL5JA==', '0', '0');
INSERT INTO `admin_user` VALUES ('375df57ac9da9433247cdc3a4b669901', 'super', 'xiaomei1', 'bcffa1ca2aad72357ae089c01fe31aac', 'X0x8FpX5jcc1dIi/XzA7Bg==', '0', '0');
INSERT INTO `admin_user` VALUES ('4851d9d6b8e9632c5fe5def6f582390d', 'super', 'wangwu', 'decba395ab4c57abb69b05882e8ec9cf', 'WIKVgglz++c4kGJVvBuvHg==', '0', '0');
INSERT INTO `admin_user` VALUES ('6a1d80e4af49c51be055dc4d29fcb180', 'super', 'lisi', '4315139033a60e379fc93d46a8b6e079', '+eQWYt4GVQMLoyF86o/Viw==', '0', '0');
INSERT INTO `admin_user` VALUES ('7b456095457b09a18232be798ace0023', 'super', 'cheng', '231793d554975efbf2119477837d85b1', 'IsxE3KEzGQwhjyo8fZKaRg==', '0', '0');
INSERT INTO `admin_user` VALUES ('9070fe83c429db323f757ff26d8933ca', 'super', 'aini', '3cf7b9d4bc247a9e47757f71e4ce59ea', 'MeKre505l9aACSkxD60LjQ==', '0', '0');
INSERT INTO `admin_user` VALUES ('bca46c62d5f0539b115290d2bd359a40', 'super', 'chengxinjing', 'faf962d518f4d55d17d26dd278131480', 'jZJTgFoXyw9tNKfoTCUJjw==', '0', '0');
INSERT INTO `admin_user` VALUES ('de6d060269986e452cff1c5c180927ae', 'super', 'wangzulan', 'abf8cad2adb9f49b504f756fa4efb5b2', '/n8DLVBKupnduCkDCtkO3Q==', '0', '0');
INSERT INTO `admin_user` VALUES ('eaee229d32da72191b0961997ddbc439', 'super', 'admin', 'd9ebf01f26380fc7967c898e873951a3', '4sbAXmzYQNpyRjiX3G4pRw==', '0', '0');
INSERT INTO `admin_user` VALUES ('ed48ae8d91b53e788053570024dde7c6', 'super', 'zhangsan', '356f840f1d483a43390e0e614dfcb41e', '1yR0bm6IJphwShh8CjYhqw==', '0', '0');
INSERT INTO `admin_user` VALUES ('f48e2ea13ccf95609fe63d019ce7f7a4', 'super', 'huangshan', 'cf6ef90f25581cc446baeb5e574b50f4', 'YkN9RrY2ab9iCbC9aQvfzA==', '0', '0');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` varchar(200) NOT NULL,
  `permission_id` varchar(200) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id_key` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `admin_permission` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('cd466e8a8f4e6f08f503721e0e97cd04', '1');
INSERT INTO `role_permission` VALUES ('super', '1');
INSERT INTO `role_permission` VALUES ('super', '2');
