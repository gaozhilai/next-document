/*
 Navicat Premium Data Transfer

 Source Server         : LocalDB
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : next_document

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 16/11/2019 20:58:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_permission
-- ----------------------------
DROP TABLE IF EXISTS `account_permission`;
CREATE TABLE `account_permission`  (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限代码, 只能英文, 如 user:list:show',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_name_index`(`name`) USING BTREE COMMENT '限制权限代码唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_permission_forbidden
-- ----------------------------
DROP TABLE IF EXISTS `account_permission_forbidden`;
CREATE TABLE `account_permission_forbidden`  (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(0) NULL,
  `updated_on` datetime(0) NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role`  (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色代码, 只能英文, 如 admin',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name_index`(`name`) USING BTREE COMMENT '限制角色代码唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `account_role_permission`;
CREATE TABLE `account_role_permission`  (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_user
-- ----------------------------
DROP TABLE IF EXISTS `account_user`;
CREATE TABLE `account_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `valid` tinyint(1) NULL DEFAULT 1 COMMENT '0为无效, 1为有效',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_login_name_index`(`login_name`) USING BTREE COMMENT '限制用户登录名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `account_user_permission`;
CREATE TABLE `account_user_permission`  (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_user_role
-- ----------------------------
DROP TABLE IF EXISTS `account_user_role`;
CREATE TABLE `account_user_role`  (
  `id` bigint(20) NOT NULL,
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for doc_category
-- ----------------------------
DROP TABLE IF EXISTS `doc_category`;
CREATE TABLE `doc_category`  (
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
-- Table structure for doc_document
-- ----------------------------
DROP TABLE IF EXISTS `doc_document`;
CREATE TABLE `doc_document`  (
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
-- Table structure for doc_history
-- ----------------------------
DROP TABLE IF EXISTS `doc_history`;
CREATE TABLE `doc_history`  (
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
-- Table structure for doc_project
-- ----------------------------
DROP TABLE IF EXISTS `doc_project`;
CREATE TABLE `doc_project`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `created_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_on` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目描述',
  `created_by` bigint(20) NOT NULL COMMENT '创建者id',
  `updated_by` bigint(20) NOT NULL COMMENT '更新者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
