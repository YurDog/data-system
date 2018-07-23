/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : data-system

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-07-22 17:50:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(25) DEFAULT NULL COMMENT '�û����',
  `user_name` varchar(255) DEFAULT NULL COMMENT '�û���',
  `aliases` varchar(255) DEFAULT NULL COMMENT '����',
  `phone` varchar(255) DEFAULT NULL COMMENT '�ֻ���',
  `address` int(11) DEFAULT NULL COMMENT '��ַ',
  `sex` tinyint(2) DEFAULT NULL COMMENT '�Ա�',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) DEFAULT NULL COMMENT '�û�״̬',
  `profile_picture` varchar(255) DEFAULT NULL COMMENT 'ͷ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
