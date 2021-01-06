/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : xqoo_system_operation_log

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/01/2021 09:15:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `log_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '日志id',
  `operation_status` tinyint(1) UNSIGNED NOT NULL COMMENT '操作结果，0-正常，1-异常',
  `operation_type` tinyint(1) UNSIGNED NOT NULL COMMENT '操作类型，1-查询，2-增加，3-修改，4-删除，0-其他',
  `request_method` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '请求方式，GET, POST,PUT等',
  `method_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '方法名称',
  `request_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求地址',
  `operator_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作人userId',
  `operator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作人登录名',
  `operator_remote_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作人的ip和端口',
  `operator_message` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作信息简述，不超过128字',
  `operator_error_message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '操作异常提示信息，不超过256字',
  `tips_message` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '提示信息',
  `create_time` bigint(13) NOT NULL COMMENT '操作时间戳',
  `login_source` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录来源',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operation_log_request_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log_request_info`;
CREATE TABLE `sys_operation_log_request_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '父级日志信息id',
  `message_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL COMMENT '请求数据json',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '操作日志请求信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operation_log_response_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log_response_info`;
CREATE TABLE `sys_operation_log_response_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '父级日志信息id',
  `message_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL COMMENT '响应数据json',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '操作日志返回信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
