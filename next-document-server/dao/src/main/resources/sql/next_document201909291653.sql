/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : next_document

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/09/2019 16:53:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `created_on` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL COMMENT '更新时间',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件夹名称',
  `project_id` bigint(20) NOT NULL COMMENT '所属项目',
  `created_by` bigint(20) NOT NULL COMMENT '创建者id',
  `updated_by` bigint(20) NOT NULL COMMENT '更新者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `created_on` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL COMMENT '更新时间',
  `category_id` bigint(20) NOT NULL COMMENT '分类,文件夹id',
  `project_id` bigint(20) NOT NULL COMMENT '所属项目id',
  `doc_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文档名称',
  `doc_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文档内容',
  `created_by` bigint(20) NOT NULL COMMENT '创建者id',
  `updated_id` bigint(20) NOT NULL COMMENT '最近一次更新者id',
  `free_editing` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0不允许非作者编辑, 1自由编辑',
  `share_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '自定义分享文章url',
  `validate` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0无效, 1有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `created_on` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL COMMENT '更新时间',
  `category_id` bigint(20) NOT NULL COMMENT '分类,文件夹id',
  `project_id` bigint(20) NOT NULL COMMENT '所属项目id',
  `doc_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文档名称',
  `doc_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文档内容',
  `created_by` bigint(20) NOT NULL COMMENT '创建者id',
  `updated_id` bigint(20) NOT NULL COMMENT '最近一次更新者id',
  `free_editing` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0不允许非作者编辑, 1自由编辑',
  `share_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '自定义分享文章url',
  `validate` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0无效, 1有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目描述',
  `created_by` bigint(20) NOT NULL COMMENT '创建者id',
  `updated_by` bigint(20) NOT NULL COMMENT '更新者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `valid` tinyint(1) NULL DEFAULT 1 COMMENT '0为无效, 1为有效',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
