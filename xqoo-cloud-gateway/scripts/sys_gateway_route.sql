/*
 Navicat Premium Data Transfer

 Source Server         : 孔晓文的数据库
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : rm-bp15sx5e65i5yd31ano.mysql.rds.aliyuncs.com:3306
 Source Schema         : xqoo-cloud

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/11/2019 13:52:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_gateway_route`;
CREATE TABLE `sys_gateway_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `service_status` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '路由状态，0-不可用，1-可用，默认可用',
  `service_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型。CLIENT-前台客户端服务，SYS-后台管理系统服务，TOOL-工具类服务',
  `service_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务id，不可重复',
  `uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转发地址，可以是服务中心的服务id，也可以是常规url',
  `predicates` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由断言参数，json列表字符串存储，详情见官网配置',
  `filters` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '路由过滤器参数，json列表字符串存储，详情见官网',
  `order_no` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序顺序，不填默认参数0',
  `service_cname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路由中文名，不能为null',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录修改人',
  `remark_tips` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网关路由信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gateway_route
-- ----------------------------
INSERT INTO `sys_gateway_route` VALUES (1, 1, 'CLIENT', 'xqoo-client', 'xqoo-client', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/client/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}},{\"name\":\"RequestRateLimiter\",\"args\":{\"key-resolver\":\"#{@remoteAddrKeyResolver}\",\"redis-rate-limiter.burstCapacity\":\"20\",\"redis-rate-limiter.replenishRate\":\"10\"}},{\"name\":\"Hystrix\",\"args\":{\"fallbackUri\":\"forward:/fallback\",\"name\":\"fallbackcmd\"}}]', 0, '测试服务端1', '2019-11-14 12:33:50', 'initData', '2019-11-21 11:54:56', '1', '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (2, 1, 'CLIENT', 'xqoo-client-two', 'xqoo-client-two', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/clientTwo/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '测试服务端2', '2019-11-14 15:25:26', 'intiData', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (3, 1, 'TOOL', 'xqoo-uid-generator', 'xqoo-uid-generator', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/sysTool/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '流水号生成器服务', '2019-11-14 15:28:55', 'initData', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (4, 1, 'SYS', 'xqoo-system-manager', 'xqoo-system-manager', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/system/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '系统管理服务', '2019-11-14 15:38:12', 'initData', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (7, 1, 'SYS', 'xqoo-system-auth', 'xqoo-system-auth', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/auth/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}},{\"name\":\"ImgCodeFilter\",\"args\":{}}]', 0, '授权中心服务', '2019-11-14 15:28:55', 'initData', NULL, NULL, '初始化数据');

SET FOREIGN_KEY_CHECKS = 1;
