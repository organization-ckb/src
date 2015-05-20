/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : cccrm

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2015-05-04 10:45:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) DEFAULT NULL,
  `customer_man` varchar(10) DEFAULT NULL,
  `customer_sex` smallint(6) DEFAULT NULL,
  `customer_qq` varchar(10) DEFAULT NULL,
  `customer_status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(20) DEFAULT NULL,
  `department_header` varchar(10) DEFAULT NULL,
  `department_qq` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for `development_log`
-- ----------------------------
DROP TABLE IF EXISTS `development_log`;
CREATE TABLE `development_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` date DEFAULT NULL,
  `programmer_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `log_content` text,
  `log_viod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of development_log
-- ----------------------------

-- ----------------------------
-- Table structure for `programmer`
-- ----------------------------
DROP TABLE IF EXISTS `programmer`;
CREATE TABLE `programmer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `programmer_name` varchar(10) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `programmer_sex` smallint(6) DEFAULT NULL,
  `programmer_qq` varchar(15) DEFAULT NULL,
  `programmer_level` smallint(6) DEFAULT NULL,
  `programmer_status` smallint(6) DEFAULT NULL,
  `position` varchar(30) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of programmer
-- ----------------------------
INSERT INTO `programmer` VALUES ('79', '快快快', '0', '0', '9655423', '0', '0', null);
INSERT INTO `programmer` VALUES ('80', '网络', '1', '1', '956223', '1', '0', null);
INSERT INTO `programmer` VALUES ('81', '网络', '1', '1', '956223', '1', null, null);
INSERT INTO `programmer` VALUES ('82', '小五', '3', '0', '32', '1', null, null);
INSERT INTO `programmer` VALUES ('83', '乌卡卡', '0', '0', '2111', '2', '0', null);
INSERT INTO `programmer` VALUES ('84', '小三', '2', '1', '9696', '0', '0', null);
INSERT INTO `programmer` VALUES ('85', '小白', '3', '0', '986969', '0', '1', null);
INSERT INTO `programmer` VALUES ('86', '胡坚', '0', '0', '250433627', '2', '1', null);
INSERT INTO `programmer` VALUES ('87', 'hjjj', '0', '0', '12333243', '0', null, null);
INSERT INTO `programmer` VALUES ('88', 'hj', '0', '0', '250433627', '0', '0', '');
INSERT INTO `programmer` VALUES ('89', 'hj', '1', '0', '250433625', '3', '0', '');
INSERT INTO `programmer` VALUES ('90', 'hj', '1', '0', '250433627', '3', '0', '');
INSERT INTO `programmer` VALUES ('91', 'hj', '2', '0', '12121', '0', '0', '');
INSERT INTO `programmer` VALUES ('92', 'hj', '3', '0', '32222', '0', '0', '');
INSERT INTO `programmer` VALUES ('93', 'fff', '0', '0', '231', '0', '0', '');
INSERT INTO `programmer` VALUES ('94', 'dawa', '1', '0', '231', '0', '0', '');
INSERT INTO `programmer` VALUES ('95', 'hjee', '0', '0', '12132313', '0', '0', '');
INSERT INTO `programmer` VALUES ('96', 'hjee', '0', '0', '12132313', '0', '0', '');
INSERT INTO `programmer` VALUES ('97', 'hjee', '0', '0', '12132313', '0', '0', '');

-- ----------------------------
-- Table structure for `programmer_project`
-- ----------------------------
DROP TABLE IF EXISTS `programmer_project`;
CREATE TABLE `programmer_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `programmer_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `begin_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of programmer_project
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(100) DEFAULT NULL,
  `pro_contract_no` varchar(100) DEFAULT NULL,
  `pro_contract_pic` varchar(255) DEFAULT NULL,
  `pro_begin_time` date DEFAULT NULL,
  `pro_end_time` date DEFAULT NULL,
  `pro_status` smallint(6) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_function`
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `id` int(11) NOT NULL,
  `fun_name` varchar(20) DEFAULT NULL,
  `fun_code` varchar(20) DEFAULT NULL,
  `fun_url` varchar(255) DEFAULT NULL,
  `fun_parent_id` int(11) DEFAULT NULL,
  `fun_type` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_function
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(20) DEFAULT NULL,
  `role_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_function`;
CREATE TABLE `sys_role_function` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `fun_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_function
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) DEFAULT NULL,
  `login_pwd` varchar(20) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_mobile` varchar(15) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` smallint(6) DEFAULT NULL COMMENT '0:管理员；1：程序员；2：客户',
  `user_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', null, null, '1', '0', null);
INSERT INTO `sys_user` VALUES ('50', '快快快', '1221', null, '121231', '79', '1', null);
INSERT INTO `sys_user` VALUES ('51', '123@looi.com', '123', '123@looi.com', '15990887332', '81', '1', null);
INSERT INTO `sys_user` VALUES ('52', 'Vincent@127.com', '123456', 'Vincent@127.com', '18098789875', '82', '1', null);
INSERT INTO `sys_user` VALUES ('53', '5@qq.com', 'asda', null, '212', '83', '1', null);
INSERT INTO `sys_user` VALUES ('54', '77@op.cn', '777', null, '6969', '84', '1', null);
INSERT INTO `sys_user` VALUES ('55', 'xb@qq.cn', '7896', null, '123659', '85', '1', null);
INSERT INTO `sys_user` VALUES ('56', 'autumnhu@looip.cn', '123456', null, '18094253590', '86', '1', null);
INSERT INTO `sys_user` VALUES ('57', 'test@126.com', '123333', 'test@126.com', '18098789875', '87', '1', null);
INSERT INTO `sys_user` VALUES ('58', 'hj_030225@126.com', '123', 'hj_030225@126.com', '18098789875', '95', '1', null);
INSERT INTO `sys_user` VALUES ('59', 'hj_030225@126.com', '123', 'hj_030225@126.com', '18098789875', '96', '1', null);
INSERT INTO `sys_user` VALUES ('60', 'hj_030225@126.com', '123', 'hj_030225@126.com', '18098789875', '97', '1', null);
