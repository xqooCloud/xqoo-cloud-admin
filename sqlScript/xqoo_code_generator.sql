/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : xqoo_code_generator

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/01/2021 09:15:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_source_info
-- ----------------------------
DROP TABLE IF EXISTS `data_source_info`;
CREATE TABLE `data_source_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否逻辑删除，0-未删除，1-已删除',
  `data_base_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT 'mysql' COMMENT '数据库类型，mysql.oracle.db2等等，目前仅支持mysql',
  `data_base_show_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '数据源显示名称',
  `data_base_host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '数据库地址',
  `data_base_port` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT '3306' COMMENT '数据库端口，默认3306',
  `data_base_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '数据库库名',
  `data_base_properties` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '数据库连接参数，可不填，将带入系统默认参数',
  `data_base_user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '数据库用户名',
  `data_base_password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '数据库密码',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最近修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最近修改时间',
  `remark_tips` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '数据源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_source_info
-- ----------------------------
INSERT INTO `data_source_info` VALUES (1, 0, 'mysql', '系统核心运行库', '127.0.0.1', '3306', 'xqoo_system_console', NULL, 'root', 'S/1Fu1RAsTfZFOjXoBcnkA==', '初始化数据', '2020-12-23 14:37:25', '1', '2020-12-23 23:38:18', NULL);
INSERT INTO `data_source_info` VALUES (2, 0, 'mysql', '操作日志中心库', '127.0.0.1', '3306', 'xqoo_system_operation_log', NULL, 'root', 'S/1Fu1RAsTfZFOjXoBcnkA==', '1', '2020-12-23 23:46:51', '1', '2020-12-23 23:49:25', NULL);

SET FOREIGN_KEY_CHECKS = 1;
