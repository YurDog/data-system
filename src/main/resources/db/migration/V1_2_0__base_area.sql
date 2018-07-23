/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : data-system

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-07-22 17:50:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_area
-- ----------------------------
DROP TABLE IF EXISTS `base_area`;
CREATE TABLE `base_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_id` varchar(12) DEFAULT NULL,
  `parent_id` varchar(12) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3524 DEFAULT CHARSET=utf8;
