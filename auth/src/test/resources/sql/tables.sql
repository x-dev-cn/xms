/*
Navicat MySQL Data Transfer

Source Server         : mysql.x-dev.cn
Source Server Version : 50173
Source Host           : x-dev.cn:3306
Source Database       : ParkingService

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-05-25 21:50:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creator_id` int DEFAULT NULL,
  `column_id` int DEFAULT NULL,
  `article_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `article_property` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pic_title` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `body_short` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `body` text COLLATE utf8_bin,
  `tags` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `modify_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `publish_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `on_top` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `on_force` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_no` int DEFAULT NULL,
  `hits_base` int DEFAULT NULL,
  `hits_count` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `page_title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `page_description` text COLLATE utf8_bin,
  `page_keywords` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `str_field01` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `str_field02` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `str_field03` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `str_field04` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `str_field05` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `int_field01` int DEFAULT NULL,
  `int_field02` int DEFAULT NULL,
  `int_field03` int DEFAULT NULL,
  `int_field04` int DEFAULT NULL,
  `int_field05` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;


-- ----------------------------
-- Table structure for sessions
-- ----------------------------
DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions` (
  `id` varchar(200) COLLATE utf8_bin NOT NULL,
  `session` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sms_send_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_send_log`;
CREATE TABLE `sms_send_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wechat_openid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `sms_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '消息类型',
  `sms_template` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '消息类型',
  `sms_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '消息验证码',
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建日期',
  `resp_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '状态码',
  `resp_msg` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '状态码的描述',
  `biz_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送回执ID',

  `query_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '查询状态码',
  `query_msg` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '查询状态码的描述',
  `send_status` int DEFAULT NULL COMMENT '发送状态 ',
  `err_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '运营商短信错误码',
  `send_date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送日期',
  `receive_date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送日期',
  `content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '消息内容',

  `status` int DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `app_key` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `app_secret` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_app_app_key` (`app_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '组织机构名称',
  `parent_id` int DEFAULT NULL COMMENT '父id',
  `parent_ids` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '父id集合',
  `position` int DEFAULT NULL COMMENT '排序编号',
  `available` tinyint(1) DEFAULT NULL COMMENT '有效状态',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_sys_organization_parent_ids` (`parent_ids`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `parent_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `permission` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_sys_resource_parent_ids` (`parent_ids`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `organization_id` int DEFAULT NULL,
  `username` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_user_username` (`username`) USING BTREE,
  KEY `idx_sys_user_organization_id` (`organization_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_user_app_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_app_roles`;
CREATE TABLE `sys_user_app_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `app_id` int DEFAULT NULL,
  `role_ids` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_user_app_roles_user_id_app_id` (`user_id`,`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


-- ----------------------------
-- Table structure for sys_vip
-- ----------------------------
DROP TABLE IF EXISTS `sys_vip`;
CREATE TABLE `sys_vip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mobile` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_vip_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `cms_msg`;
CREATE TABLE `cms_msg` (
	`id` int NOT NULL AUTO_INCREMENT,
	`openid` varchar(255),
	`msg` varchar(255),
	`create_time` varchar(255),
	`pic` varchar(255),
	`status` int,
	PRIMARY KEY (`id`)
) COMMENT='';


DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `type` int,
  `start_time` varchar(255),
  `end_time` varchar(255),
  `status` int,
  `create_time` varchar(255),
  `modify_time` varchar(255),
  PRIMARY KEY (`id`)
) COMMENT='';

DROP TABLE IF EXISTS `vote_option`;
CREATE TABLE `vote_option` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `content` varchar(255),
  `vote_id` int,
  `create_time` varchar(255),
  `modify_time` varchar(255),
  `vote_num` bigint,
  `status` int,
  PRIMARY KEY (`id`)
) COMMENT='';

DROP TABLE IF EXISTS `vote_record`;
CREATE TABLE `vote_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255),
  `option_id` int,
  `status` int,
  `create_time` varchar(255),
  PRIMARY KEY (`id`)
) COMMENT='';