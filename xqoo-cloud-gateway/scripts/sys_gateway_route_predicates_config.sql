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

 Date: 21/11/2019 13:52:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_gateway_route_predicates_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_gateway_route_predicates_config`;
CREATE TABLE `sys_gateway_route_predicates_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id主键',
  `predicates_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '断言属性类型，args-[只有一个值，key为_genkey_家数字],map-[值有多个，key为具体属性名称，value为属性值]',
  `predicates_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '断言属性名称',
  `predicates_tips` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '断言器说明文本',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark_tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网关路由断言项目皮遏制表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gateway_route_predicates_config
-- ----------------------------
INSERT INTO `sys_gateway_route_predicates_config` VALUES (1, 'args', 'After', 'After Route Predicate Factory采用一个参数——日期时间。在该日期时间之后发生的请求都将被匹配。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (2, 'args', 'Before ', 'Before Route Predicate Factory采用一个参数——日期时间。在该日期时间之前发生的请求都将被匹配。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (3, 'args', 'Between', 'Between 路由断言 Factory有两个参数，datetime1和datetime2。在datetime1和datetime2之间的请求将被匹配。datetime2参数的实际时间必须在datetime1之后。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (4, 'args', 'Cookie', 'Cookie 路由断言 Factory有两个参数，cookie名称和正则表达式。请求包含次cookie名称且正则表达式为真的将会被匹配。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (5, 'args', 'Header', 'Header 路由断言 Factory有两个参数，header名称和正则表达式。请求包含次header名称且正则表达式为真的将会被匹配。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (6, 'args', 'Host', 'Host 路由断言 Factory包括一个参数：host name列表。使用Ant路径匹配规则，.作为分隔符。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (7, 'args', 'Method', 'Method 路由断言 Factory只包含一个参数: 需要匹配的HTTP请求方式', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (8, 'args', 'Path', 'Path 路由断言 Factory 有2个参数: 一个Spring PathMatcher表达式列表和可选matchOptionalTrailingSeparator标识 .', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (9, 'args', 'Query', 'Query 路由断言 Factory 有2个参数: 必选项 param 和可选项 regexp.', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_predicates_config` VALUES (10, 'args', 'RemoteAddr', 'RemoteAddr 路由断言 Factory的参数为 一个CIDR符号（IPv4或IPv6）字符串的列表，最小值为1，例如192.168.0.1/16（其中192.168.0.1是IP地址并且16是子网掩码）。', '2019-11-19 23:11:23', 'Bluce', NULL, NULL, '初始化数据');

SET FOREIGN_KEY_CHECKS = 1;
