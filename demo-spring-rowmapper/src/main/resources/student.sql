/*
Navicat MySQL Data Transfer

Source Server         : 11.13mysql
Source Server Version : 50704
Source Host           : 172.16.11.13:3306
Source Database       : zhtest

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2017-11-02 14:09:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
