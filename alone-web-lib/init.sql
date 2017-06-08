/*
Navicat MySQL Data Transfer

Source Server         : myself
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : cheng

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-06-08 17:33:22
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
  KEY `admin_role_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('2182e2a2-3f0c-478c-abbf-0983ed973fdd', '', 'meili', '863c33b1fdc5915e1b75c3c132dd7719', 'a2ba648a0e60ff793e45404e36e420cd', '0', '0');
INSERT INTO `admin_user` VALUES ('29cb7c4e-af64-42ce-aeca-8370a43cf563', '', 'xiaogouzi', '42681de96fe25080e281dcb459aa21d8', '056887c312309f1d3e6b8b0c913662d1', '0', '0');
INSERT INTO `admin_user` VALUES ('4dd37bbd-3d1a-488a-bffe-f43e1c49cc4a', '', 'wulala', 'de39c779b12de92632717f62a49fb024', 'd0d44a29327d1594d8a82d52d13cc4ba', '0', '0');
INSERT INTO `admin_user` VALUES ('4f9c09e9-4232-4baf-aea4-e7bb490aaeb3', '', 'mamama', 'bef1c196eb23a2bf206a46578c64aa2a', '1bda93fb810b525b6284bbeb8ea21978', '0', '0');
INSERT INTO `admin_user` VALUES ('8824873d-c828-45b6-94c7-67e05e109a8e', '', 'chengxinjing123', 'c78202e5d23f51146614564f75398a09', '5f3a42a6d994ea0972d7e93c2db58495', '0', '0');
INSERT INTO `admin_user` VALUES ('89248c28516800faea586d485abdb7b3', 'super', 'mama', 'b92d1387b243c99e4aaee339c495f501', '2e6145e868e86ccee932d2ccb96e4fc5', '0', '0');
INSERT INTO `admin_user` VALUES ('bb9ec410ffcf116199adb52062333d79', 'super', 'admin', '050efdbc80ba2fa9112182fe116be095', 'bdfcde87cd6c597b984db86f6bd9ca47', '0', '0');
INSERT INTO `admin_user` VALUES ('cd466e8a8f4e6f08f503721e0e97cd04', 'cd466e8a8f4e6f08f503721e0e97cd04', 'jiangxi', '0d846c10025993b9290f085e066e4964', '5f2e91f1f61643cb4d94fe70b02653e7', '0', '0');
INSERT INTO `admin_user` VALUES ('d5c0e5ee17904a3248634fc7fab1fddd', '', 'huawei', 'ef28259654af9d6ddfad40eac41cf179', '5fffe429a3f7f0f0f7aa53b7e6bcad60', '0', '0');
INSERT INTO `admin_user` VALUES ('f631d601-a778-4c7a-94ff-36ab07a75125', '', 'chengdaxiao', 'a6b49201ed3bc626617ed02f600e09b0', '0bde8b7783d984b4ec3ca62f05d89578', '0', '0');
INSERT INTO `admin_user` VALUES ('f97ac69d-802a-4879-885c-7d1f45df68c0', '', 'cheng', 'ff7b1b0955415c154555acf267b05c07', '8f136bb8f54bb7880da73a1b9dd14a71', '0', '0');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` varchar(200) NOT NULL,
  `permission_id` varchar(200) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id_key` (`permission_id`),
  CONSTRAINT `permission_id_key` FOREIGN KEY (`permission_id`) REFERENCES `admin_permission` (`id`),
  CONSTRAINT `role_id_key` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('cd466e8a8f4e6f08f503721e0e97cd04', '1');
INSERT INTO `role_permission` VALUES ('super', '1');
INSERT INTO `role_permission` VALUES ('super', '2');
