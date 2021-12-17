/*
 Navicat MySQL Data Transfer

 Source Server         : XuJunBin
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : secondhandbooks

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/12/2021 00:00:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `fromUid` int NOT NULL,
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bookName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postTime` datetime NULL DEFAULT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isPost` int NOT NULL,
  `isComment` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`fromUid`) USING BTREE,
  CONSTRAINT `uid` FOREIGN KEY (`fromUid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 2, '刘慈欣', '三体', NULL, '给岁月以文明而不是给文明以岁月。', 0, 1);
INSERT INTO `book` VALUES (2, 2, '东野圭吾', '白夜行', '2021-12-12 22:17:01', '由于母亲与自己家当铺雇的人在家里偷情，桐原亮司跑到废弃大楼的通风道里玩耍，却看到了父亲对自己的好友雪穗实施侵害的不堪一幕，扭曲的惊惧与愤怒使得11岁的他用长剪刀刺死了自己的父亲，之后西本雪穗的母亲及“母亲的情人”也“意外死亡”，随后她被优雅独居的唐泽礼子收养。没有了完整家庭的少男和少女，在惨剧发生后度过了平静的七年，然而，桐原亮司发现当年的案子还是有人在查，而且，已经开始怀疑到自己和唐泽雪穗身上了。没有家庭温暖的二人，为了不让自己的罪行被发现，用尽各种手段把自己身边的亲人、朋友一一除掉。', 1, 1);
INSERT INTO `book` VALUES (5, 1, '123', '123', '2021-12-16 10:39:04', '123', 1, 1);
INSERT INTO `book` VALUES (6, 2, '123', '123', NULL, '123', 0, 1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentId` int NOT NULL AUTO_INCREMENT,
  `bookId` int NOT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` int NOT NULL,
  `parentId` int NULL DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `bookId`(`bookId`) USING BTREE,
  CONSTRAINT `bookId` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 2, '这本书什么时候出版的', 2, NULL, '2021-12-12 15:06:57');
INSERT INTO `comment` VALUES (2, 2, '百度一下你就知道', 1, 1, '2021-12-12 21:01:21');
INSERT INTO `comment` VALUES (3, 2, '这本书什么时候出版的', 2, NULL, '2021-12-12 21:37:35');
INSERT INTO `comment` VALUES (13, 2, 'wan', 2, NULL, '2021-12-13 10:53:07');
INSERT INTO `comment` VALUES (23, 2, '123', 1, 1, '2021-12-13 12:17:44');
INSERT INTO `comment` VALUES (24, 2, '1234', 2, NULL, '2021-12-16 10:43:23');
INSERT INTO `comment` VALUES (25, 2, '123', 2, 24, '2021-12-16 10:43:31');
INSERT INTO `comment` VALUES (26, 2, '1234', 2, 24, '2021-12-16 10:43:47');
INSERT INTO `comment` VALUES (27, 2, '1234', 1, 1, '2021-12-16 10:59:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `administrator` int NOT NULL,
  `postRight` int NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xujunbin', '123', 1, 1);
INSERT INTO `user` VALUES (2, 'AAA', '123', 0, 0);
INSERT INTO `user` VALUES (3, 'BBB', '123', 0, 1);
INSERT INTO `user` VALUES (4, 'DDD', '123', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
