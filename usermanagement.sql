/*
 Navicat MySQL Data Transfer

 Source Server         : Architecture
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : localhost
 Source Database       : usermanagement

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : utf-8

 Date: 03/07/2015 23:05:18 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user_account`
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `user_id` int(10) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `user_account`
-- ----------------------------
BEGIN;
INSERT INTO `user_account` VALUES ('1', 'song', '123456'), ('2', 'gunveer', '123456'), ('3', 'liz', '123456'), ('4', 'prachi', '123456');
COMMIT;

-- ----------------------------
--  Table structure for `user_activities`
-- ----------------------------
DROP TABLE IF EXISTS `user_activities`;
CREATE TABLE `user_activities` (
  `user_activity_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `login_time` varchar(30) DEFAULT NULL,
  `logout_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_activity_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `uid` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `user_activities`
-- ----------------------------
BEGIN;
INSERT INTO `user_activities` VALUES ('7', '3', '2015/03/07 23:03:18', '2015/03/07 23:03:21'), ('8', '1', '2015/03/07 23:03:51', null);
COMMIT;

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` varchar(20) NOT NULL,
  `user_role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `user_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES ('INV_MAN', '\"Inventory Manager\"'), ('ORD_EMP', '\"Order Employee\"'), ('SHI_EMP', '\"Shipping Employee\"');
COMMIT;

-- ----------------------------
--  Table structure for `user_role_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `user_id` int(10) NOT NULL,
  `user_role_id` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`user_role_id`),
  KEY `user_role_id` (`user_role_id`),
  CONSTRAINT `uid_FK` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `urid_FK` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`user_role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `user_role_relation`
-- ----------------------------
BEGIN;
INSERT INTO `user_role_relation` VALUES ('1', 'INV_MAN'), ('1', 'ORD_EMP'), ('2', 'ORD_EMP'), ('3', 'SHI_EMP');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
