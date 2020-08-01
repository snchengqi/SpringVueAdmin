/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : vue_element_admin

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-08-01 10:01:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `orgCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'chengqi', '2019-04-22 09:07:48', '2019-04-22 09:07:53', 'CCO');
INSERT INTO `test` VALUES ('2', 'axia', '2019-04-22 09:08:03', '2019-04-22 09:08:06', 'CCB');
INSERT INTO `test` VALUES ('3', 'penye', '2019-04-22 09:08:18', '2019-04-22 09:08:20', 'CCB');
INSERT INTO `test` VALUES ('4', 'wangren', '2019-04-22 09:08:27', '2019-04-22 09:08:29', 'CCG');
INSERT INTO `test` VALUES ('5', 'youyi', '2019-04-22 09:08:39', '2019-04-22 09:08:41', 'CCO');

-- ----------------------------
-- Table structure for up_backendapi
-- ----------------------------
DROP TABLE IF EXISTS `up_backendapi`;
CREATE TABLE `up_backendapi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'uuid',
  `path` varchar(255) NOT NULL,
  `method` int(11) NOT NULL COMMENT '1：GET 2：POST',
  `name` varchar(32) DEFAULT NULL,
  `createdBy` varchar(32) DEFAULT NULL,
  `modifiedBy` varchar(32) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_backendapi
-- ----------------------------
INSERT INTO `up_backendapi` VALUES ('1', '/userManager/queryUsers', '2', '查询用户', 'super', 'super', '2019-04-09 17:35:17', '2019-04-09 17:35:20');
INSERT INTO `up_backendapi` VALUES ('8', '/userManager/queryAllRoles', '1', '查询所有角色', 'super', 'super', '2019-04-13 15:33:47', '2019-04-13 15:33:47');
INSERT INTO `up_backendapi` VALUES ('9', '/userManager/addUser', '2', '新增用户', 'super', 'super', '2019-04-13 15:34:19', '2019-04-13 15:34:19');
INSERT INTO `up_backendapi` VALUES ('10', '/userManager/editUser', '2', '修改用户信息', 'super', 'super', '2019-04-13 15:34:59', '2019-04-13 15:34:59');
INSERT INTO `up_backendapi` VALUES ('11', '/userManager/deleteUsers', '2', '删除指定所有用户', 'super', 'super', '2019-04-13 15:35:45', '2019-04-13 15:35:45');
INSERT INTO `up_backendapi` VALUES ('12', '/roleManager/queryRoles', '2', '查询角色', 'super', 'super', '2019-04-13 15:36:39', '2019-04-13 15:36:39');
INSERT INTO `up_backendapi` VALUES ('13', '/roleManager/addRole', '2', '新增角色', 'super', 'super', '2019-04-13 15:37:21', '2019-04-13 15:37:21');
INSERT INTO `up_backendapi` VALUES ('14', '/roleManager/editRole', '2', '修改角色信息', 'super', 'super', '2019-04-13 15:37:53', '2019-04-13 15:37:53');
INSERT INTO `up_backendapi` VALUES ('15', '/roleManager/deleteRoles', '2', '删除指定所有角色', 'super', 'super', '2019-04-13 15:38:30', '2019-04-13 15:38:30');
INSERT INTO `up_backendapi` VALUES ('16', '/menuManager/getMenuTree', '1', '获取菜单树', 'super', 'super', '2019-04-13 15:39:35', '2019-04-13 15:39:35');
INSERT INTO `up_backendapi` VALUES ('17', '/menuManager/addMenu', '2', '添加菜单', 'super', 'super', '2019-04-13 15:40:12', '2019-04-13 15:40:12');
INSERT INTO `up_backendapi` VALUES ('18', '/menuManager/editMenu', '2', '修改菜单信息', 'super', 'super', '2019-04-13 15:42:16', '2019-04-13 15:42:16');
INSERT INTO `up_backendapi` VALUES ('19', '/menuManager/deleteMenu', '2', '删除单个菜单', 'super', 'super', '2019-04-13 15:43:14', '2019-04-13 15:43:14');
INSERT INTO `up_backendapi` VALUES ('20', '/menuManager/queryAllBackendApi', '1', '查询所有后端接口', 'super', 'super', '2019-04-13 15:43:55', '2019-04-13 15:43:55');
INSERT INTO `up_backendapi` VALUES ('21', '/backAPiManager/queryBackApis', '2', '查询后端接口', 'super', 'super', '2019-04-13 15:44:46', '2019-04-13 15:44:46');
INSERT INTO `up_backendapi` VALUES ('22', '/backAPiManager/addBackendApi', '2', '新增后端接口', 'super', 'admin', '2019-04-13 15:45:23', '2019-04-15 12:45:07');
INSERT INTO `up_backendapi` VALUES ('23', '/backAPiManager/editBackendApi', '2', '修改后端接口信息', 'super', 'super', '2019-04-13 15:45:56', '2019-04-13 15:45:56');
INSERT INTO `up_backendapi` VALUES ('24', '/backAPiManager/deleteBackendApis', '2', '删除指定所有后端接口', 'super', 'admin', '2019-04-13 15:46:28', '2019-04-15 12:36:32');

-- ----------------------------
-- Table structure for up_menu
-- ----------------------------
DROP TABLE IF EXISTS `up_menu`;
CREATE TABLE `up_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentId` int(11) NOT NULL DEFAULT '-1' COMMENT '父键',
  `name` varchar(64) NOT NULL COMMENT '菜单code',
  `menuName` varchar(64) NOT NULL COMMENT '菜单名称',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `path` varchar(64) DEFAULT NULL COMMENT '路由路径',
  `alwaysShow` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '是否总是显示跟节点：0否，1是',
  `hidden` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '是否隐藏菜单：0否，1是',
  `noCache` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '是否缓存：0不，1是',
  `level` tinyint(4) NOT NULL COMMENT '菜单等级：1子菜单 2菜单项',
  `orderNo` int(11) NOT NULL COMMENT '排序',
  `createdBy` varchar(32) DEFAULT NULL,
  `modifiedBy` varchar(32) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_menu
-- ----------------------------
INSERT INTO `up_menu` VALUES ('1', '-1', 'PermissionManager', '权限管理', 'lock', '', '0', '-1', '-1', '1', '0', null, 'super', '2019-04-09 17:32:02', '2019-04-16 15:37:12');
INSERT INTO `up_menu` VALUES ('2', '1', 'UserManager', '用户管理', 'user', 'userManager', '-1', '0', '0', '2', '1', null, 'super', '2019-04-09 17:33:01', '2019-04-15 14:29:13');
INSERT INTO `up_menu` VALUES ('3', '1', 'CreateUser', '创建用户', null, 'createUser', '-1', '1', '0', '2', '2', null, null, '2019-04-10 16:24:55', '2019-04-13 15:51:50');
INSERT INTO `up_menu` VALUES ('4', '1', 'EditUser', '编辑用户', null, 'editUser', '-1', '1', '1', '2', '3', null, null, '2019-04-10 16:26:03', '2019-04-13 15:52:14');
INSERT INTO `up_menu` VALUES ('5', '1', 'RoleManager', '角色管理', 'peoples', 'roleManager', '-1', '0', '0', '2', '4', null, 'super', '2019-04-10 16:27:17', '2019-04-15 14:28:13');
INSERT INTO `up_menu` VALUES ('6', '1', 'CreateRole', '创建角色', null, 'createRole', '-1', '1', '0', '2', '5', null, null, '2019-04-10 16:28:14', '2019-04-13 15:53:10');
INSERT INTO `up_menu` VALUES ('7', '1', 'EditRole', '编辑角色', null, 'editRole', '-1', '1', '1', '2', '6', null, null, '2019-04-10 16:29:21', '2019-04-13 15:53:32');
INSERT INTO `up_menu` VALUES ('8', '1', 'MenuManager', '菜单设置', 'menu', 'menuManager', '-1', '0', '0', '2', '7', null, 'super', '2019-04-10 16:30:35', '2019-04-15 14:32:29');
INSERT INTO `up_menu` VALUES ('12', '1', 'BackendApiManager', '接口管理', 'api', 'backendApiManager', '-1', '0', '0', '2', '8', null, 'super', '2019-04-13 12:34:25', '2019-04-15 14:41:37');
INSERT INTO `up_menu` VALUES ('13', '1', 'CreateApi', '创建接口', null, 'createApi', '-1', '1', '0', '2', '9', null, null, '2019-04-13 14:06:01', '2019-04-13 15:55:02');
INSERT INTO `up_menu` VALUES ('14', '1', 'EditApi', '编辑接口', null, 'editApi', '-1', '1', '1', '2', '10', null, null, '2019-04-13 15:02:37', '2019-04-13 15:55:17');
INSERT INTO `up_menu` VALUES ('15', '-1', 'OrderManager', '订单管理', null, null, '0', '-1', '-1', '1', '2', 'super', 'super', '2019-04-16 14:23:43', '2019-04-16 14:37:04');
INSERT INTO `up_menu` VALUES ('16', '15', 'QueryOrder', '订单查询', null, 'queryOrder', '-1', '0', '0', '2', '0', 'super', 'super', '2019-04-16 14:24:35', '2019-04-16 14:24:35');
INSERT INTO `up_menu` VALUES ('17', '15', 'CreateOrder', '新增订单', null, 'CreateOrder', '-1', '0', '0', '2', '1', 'super', 'super', '2019-04-16 14:25:10', '2019-04-16 14:25:10');
INSERT INTO `up_menu` VALUES ('18', '15', 'ReviewOrder', '审核订单', null, 'ReviewOrder', '-1', '0', '0', '2', '2', 'super', 'super', '2019-04-16 14:26:13', '2019-04-16 14:26:13');
INSERT INTO `up_menu` VALUES ('19', '15', 'InvalidOrder', '作废订单', null, 'InvalidOrder', '-1', '0', '0', '2', '3', 'super', 'super', '2019-04-16 14:27:02', '2019-04-16 14:27:02');
INSERT INTO `up_menu` VALUES ('20', '-1', 'SystemManager', '系统管理', null, null, '0', '-1', '-1', '1', '1', 'super', 'super', '2019-04-16 14:30:56', '2019-04-16 14:36:59');
INSERT INTO `up_menu` VALUES ('21', '20', 'HardMonitor', '硬件监控', null, 'HardMonitor', '-1', '0', '0', '2', '0', 'super', 'super', '2019-04-16 14:31:55', '2019-04-16 14:31:55');
INSERT INTO `up_menu` VALUES ('22', '20', 'SoftMonitor', '软件监控', null, 'SoftMonitor', '-1', '0', '0', '2', '1', 'super', 'super', '2019-04-16 14:32:32', '2019-04-16 14:32:32');
INSERT INTO `up_menu` VALUES ('23', '20', 'DataCount', '数据统计', null, 'DataCount', '-1', '0', '0', '2', '2', 'super', 'super', '2019-04-16 14:33:20', '2019-04-16 14:33:20');
INSERT INTO `up_menu` VALUES ('24', '20', 'ServiceHeart', '服务心跳', 'ServiceHeart', 'ServiceHeart', '-1', '0', '0', '2', '0', 'super', 'super', '2019-04-16 14:34:01', '2019-04-16 14:34:01');
INSERT INTO `up_menu` VALUES ('25', '-1', 'StockManager', '库存管理', null, null, '0', '-1', '-1', '1', '3', 'super', 'super', '2019-04-16 14:36:36', '2019-04-16 14:37:08');
INSERT INTO `up_menu` VALUES ('26', '25', 'StockQuery', '库存查询', null, 'StockQuery', '-1', '0', '0', '2', '0', 'super', 'super', '2019-04-16 14:38:07', '2019-04-16 15:36:59');
INSERT INTO `up_menu` VALUES ('28', '27', 'Test', '测试菜单1', null, 'Test', '-1', '0', '0', '2', '0', 'super', 'super', '2019-04-16 15:35:15', '2019-04-16 15:35:15');

-- ----------------------------
-- Table structure for up_menu_backendapi
-- ----------------------------
DROP TABLE IF EXISTS `up_menu_backendapi`;
CREATE TABLE `up_menu_backendapi` (
  `menuId` int(11) NOT NULL,
  `backendApiId` int(11) NOT NULL,
  PRIMARY KEY (`menuId`,`backendApiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_menu_backendapi
-- ----------------------------
INSERT INTO `up_menu_backendapi` VALUES ('2', '1');
INSERT INTO `up_menu_backendapi` VALUES ('2', '11');
INSERT INTO `up_menu_backendapi` VALUES ('3', '2');
INSERT INTO `up_menu_backendapi` VALUES ('3', '8');
INSERT INTO `up_menu_backendapi` VALUES ('3', '9');
INSERT INTO `up_menu_backendapi` VALUES ('4', '8');
INSERT INTO `up_menu_backendapi` VALUES ('4', '10');
INSERT INTO `up_menu_backendapi` VALUES ('5', '12');
INSERT INTO `up_menu_backendapi` VALUES ('5', '15');
INSERT INTO `up_menu_backendapi` VALUES ('5', '16');
INSERT INTO `up_menu_backendapi` VALUES ('6', '13');
INSERT INTO `up_menu_backendapi` VALUES ('7', '14');
INSERT INTO `up_menu_backendapi` VALUES ('8', '16');
INSERT INTO `up_menu_backendapi` VALUES ('8', '17');
INSERT INTO `up_menu_backendapi` VALUES ('8', '18');
INSERT INTO `up_menu_backendapi` VALUES ('8', '19');
INSERT INTO `up_menu_backendapi` VALUES ('8', '20');
INSERT INTO `up_menu_backendapi` VALUES ('12', '21');
INSERT INTO `up_menu_backendapi` VALUES ('12', '24');
INSERT INTO `up_menu_backendapi` VALUES ('13', '22');
INSERT INTO `up_menu_backendapi` VALUES ('14', '4');
INSERT INTO `up_menu_backendapi` VALUES ('14', '23');

-- ----------------------------
-- Table structure for up_role
-- ----------------------------
DROP TABLE IF EXISTS `up_role`;
CREATE TABLE `up_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'uuid',
  `roleName` varchar(32) NOT NULL COMMENT '角色名称',
  `name` varchar(32) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createdBy` varchar(32) DEFAULT NULL,
  `modifiedBy` varchar(32) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleName` (`roleName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_role
-- ----------------------------
INSERT INTO `up_role` VALUES ('1', 'admin', '管理员', '拥有所有权限!!!!', null, 'super', '2019-04-09 17:29:02', '2019-04-16 15:55:49');
INSERT INTO `up_role` VALUES ('3', 'generalUser', '普通用户', '普通用户只拥有一般权限...', 'super', 'super', '2019-04-15 12:32:53', '2019-04-15 12:32:53');

-- ----------------------------
-- Table structure for up_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `up_role_menu`;
CREATE TABLE `up_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_role_menu
-- ----------------------------
INSERT INTO `up_role_menu` VALUES ('1', '2');
INSERT INTO `up_role_menu` VALUES ('1', '3');
INSERT INTO `up_role_menu` VALUES ('1', '4');
INSERT INTO `up_role_menu` VALUES ('1', '5');
INSERT INTO `up_role_menu` VALUES ('1', '6');
INSERT INTO `up_role_menu` VALUES ('1', '7');
INSERT INTO `up_role_menu` VALUES ('1', '8');
INSERT INTO `up_role_menu` VALUES ('1', '12');
INSERT INTO `up_role_menu` VALUES ('1', '13');
INSERT INTO `up_role_menu` VALUES ('1', '14');
INSERT INTO `up_role_menu` VALUES ('3', '16');
INSERT INTO `up_role_menu` VALUES ('3', '17');
INSERT INTO `up_role_menu` VALUES ('3', '18');
INSERT INTO `up_role_menu` VALUES ('3', '19');
INSERT INTO `up_role_menu` VALUES ('3', '21');
INSERT INTO `up_role_menu` VALUES ('3', '22');
INSERT INTO `up_role_menu` VALUES ('3', '23');
INSERT INTO `up_role_menu` VALUES ('3', '24');
INSERT INTO `up_role_menu` VALUES ('3', '26');

-- ----------------------------
-- Table structure for up_user
-- ----------------------------
DROP TABLE IF EXISTS `up_user`;
CREATE TABLE `up_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `userName` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `password` varchar(60) NOT NULL,
  `isLock` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否锁定：0未锁定，1锁定',
  `sex` tinyint(4) NOT NULL COMMENT '性别',
  `createdBy` varchar(32) DEFAULT NULL,
  `modifiedBy` varchar(32) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_user
-- ----------------------------
INSERT INTO `up_user` VALUES ('11', 'super', 'super', '$2a$10$852F7dWVNN0l1LtJH.M6ROQE6ttojLxOKgQ2hHdXbDf8OAkDt/.qa', '0', '0', null, 'super', '2019-04-11 15:54:36', '2019-04-16 10:03:15');
INSERT INTO `up_user` VALUES ('23', 'admin', '管理员', '$2a$10$FuT7JbrEPnSf7EvPPXoXyOIJ4l5JdP/oDu9vIHiewZJpDwFtBm/MO', '0', '1', 'super', 'super', '2019-04-15 12:28:21', '2019-04-15 12:28:21');

-- ----------------------------
-- Table structure for up_user_role
-- ----------------------------
DROP TABLE IF EXISTS `up_user_role`;
CREATE TABLE `up_user_role` (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of up_user_role
-- ----------------------------
INSERT INTO `up_user_role` VALUES ('11', '1');
INSERT INTO `up_user_role` VALUES ('23', '1');
INSERT INTO `up_user_role` VALUES ('24', '3');
