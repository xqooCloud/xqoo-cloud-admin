/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : xqoo_system_console

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/01/2021 09:16:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `menu_id` int(20) UNSIGNED NOT NULL COMMENT '关联的菜单的id',
  `api_permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '接口权限字符',
  `api_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '接口名称',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark_tips` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '用户接口资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_console_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_console_menu`;
CREATE TABLE `sys_console_menu`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标记，0-未删除，1-已删除',
  `default_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认菜单，0-否，1,-是，默认菜单不可删除',
  `parent_id` int(20) NOT NULL COMMENT '父级菜单，为0则是第一级菜单',
  `sort_no` int(4) UNSIGNED NOT NULL COMMENT '排序序号，1-9999之间',
  `out_side_jump` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否外部跳转，0-不是，1-是，外部跳转时下面除了path,name,icon,target，其余属性均无用',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '菜单路径，若严格按照层级，则不显示菜单将会高亮上级菜单，外部连接直接填写域名',
  `target` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '是否打开额外窗口，null或空-不打开，_blank-打开',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '此项可填写前端国际化key值，如没有则直接显示此值,最多64字',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '组件指向，点击菜单跳转的页面，前端文件相对路径的字符串',
  `chinese_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '中文名字，仅用来做备注使用，最多24字',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '图标，名称是前端固定枚举值',
  `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '跳转的路径，选取现有菜单的path值即可',
  `hide_in_menu` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否隐藏菜单，0-不隐藏，1-隐藏',
  `parent_keys` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '高亮父级菜单，此菜单被隐藏时可用，跳转此页面时关联的菜单将高亮，值为jsonArr，选取现有菜单path值即可',
  `menu_render` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否展示菜单栏，0-不展示，1-展示，此项为0时打开对应菜单将隐藏整个菜单栏',
  `menu_header_render` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否展示菜单顶栏，0-不展示，1-展示，此项为0时打开对应菜单将隐藏菜单顶栏',
  `fix_siderbar` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '固定菜单栏，不随页面上下滑动，0-不固定，1-固定，此项当menuRender为0时无效',
  `layout` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL DEFAULT 'mix' COMMENT '打开页面时菜单展示方式，固定值 side-正常，top-顶端，mix-混合，false-直接隐藏所有层，默认mix，此项当menuRender为0时无效',
  `nav_theme` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL DEFAULT 'light' COMMENT '打开页面时菜单主题，固定值 dark-黑，light-亮色，realDark-暗黑，默认light，此项当menuRender为0时无效',
  `header_render` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否展示顶栏，0-不展示，1-展示，此项为0时打开对应菜单将隐藏整个顶部导航栏',
  `fixed_header` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '固定顶栏，0-不固定，1-固定，此项在headerRender为0时无效',
  `header_theme` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT 'dark' COMMENT '顶栏主题，固定值 dark-黑，light-亮色，此项仅当布局为mix时有效',
  `footer_render` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否展示页脚，0-不展示，1-展示',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最近修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最近修改时间',
  `remark_tips` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '管理控制台菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_console_menu
-- ----------------------------
INSERT INTO `sys_console_menu` VALUES (1, 0, 1, 0, 1, 0, '/welcome', NULL, '首页', './Welcome', '首页', 'SmileOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 1, 'system', '2020-12-07 09:37:48', NULL, NULL, '初始化数据');
INSERT INTO `sys_console_menu` VALUES (2, 0, 1, 0, 99, 0, '/system', NULL, '系统管理', './Welcome', '系统管理', 'SettingOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (3, 0, 0, 2, 1, 0, '/system/menuInfo', NULL, '菜单信息', './system/MenuManager', '菜单信息', 'PicRightOutlined', NULL, 0, '[]', 1, 1, 1, 'top', 'dark', 1, 1, 'light', 0, NULL, NULL, '1', '2020-12-13 15:23:38', NULL);
INSERT INTO `sys_console_menu` VALUES (4, 0, 0, 2, 2, 0, '/system/roleInfo', NULL, '角色管理', './system/RoleManager', '角色管理', 'InfoCircleOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-13 12:59:22', '1', '2020-12-13 14:20:17', NULL);
INSERT INTO `sys_console_menu` VALUES (5, 0, 0, 2, 3, 0, '/system/UserManager', NULL, '用户管理', './system/UserManager', '用户管理', 'WarningOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-15 13:15:49', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (6, 0, 0, 0, 100, 0, '/devOps', NULL, '运维管理', './Welcome', '运维管理', 'FundProjectionScreenOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-15 13:26:08', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (7, 0, 0, 12, 1, 1, 'http://127.0.0.1:9411', '_blank', 'zipkin链路追踪', NULL, 'zipkin链路追踪', 'SmileOutlined', NULL, 0, '[]', 1, 1, 0, 'mix', 'light', 1, 1, 'dark', 1, '1', '2020-12-15 13:27:17', '1', '2020-12-23 20:18:58', NULL);
INSERT INTO `sys_console_menu` VALUES (8, 0, 0, 12, 2, 1, 'http://127.0.0.1:8080/doc.html', '_blank', 'swagger接口', NULL, 'swagger接口', 'SmileOutlined', NULL, 0, '[]', 1, 1, 0, 'mix', 'light', 1, 1, 'dark', 1, '1', '2020-12-15 18:18:41', '1', '2020-12-23 20:19:27', NULL);
INSERT INTO `sys_console_menu` VALUES (9, 0, 0, 12, 3, 1, 'http://127.0.0.1:8099', '_blank', 'SpringAdmin监控', NULL, 'SpringAdmin监控', 'SmileOutlined', NULL, 0, '[]', 1, 1, 0, 'mix', 'light', 1, 1, 'dark', 1, '1', '2020-12-15 18:55:18', '1', '2020-12-23 20:20:15', NULL);
INSERT INTO `sys_console_menu` VALUES (10, 0, 0, 12, 4, 1, 'http://127.0.0.1:8718', '_blank', 'sentinel控制台', NULL, 'sentinel控制台', 'SmileOutlined', NULL, 0, '[]', 1, 1, 0, 'mix', 'light', 1, 1, 'dark', 1, '1', '2020-12-15 19:07:15', '1', '2020-12-23 20:20:34', NULL);
INSERT INTO `sys_console_menu` VALUES (11, 0, 0, 12, 5, 1, 'http://127.0.0.1:8184/nacos', '_blank', 'Nacos控制台', './Welcome', 'Nacos控制台', 'SmileOutlined', NULL, 0, '[]', 1, 1, 0, 'mix', 'light', 1, 1, 'dark', 1, '1', '2020-12-15 20:52:15', '1', '2020-12-23 20:20:47', NULL);
INSERT INTO `sys_console_menu` VALUES (12, 0, 0, 6, 1, 0, '/devOps/third', NULL, '第三方控制台', './Welcome', '第三方控制台', 'SmileOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-15 21:17:02', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (13, 0, 0, 2, 4, 0, '/system/userRole', NULL, '用户角色', './system/UserRole', '用户角色', 'QuestionCircleOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-17 21:40:18', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (14, 0, 0, 6, 2, 0, '/devOps/authProperties', NULL, '授权配置概览', './devOps/AuthProperties', '授权配置概览', 'PlusOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-21 17:06:48', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (15, 0, 0, 6, 3, 0, '/devOps/gatewayIntercept', NULL, '网关拦截日志', './devOps/GatewayIntercept', '网关拦截日志', 'CloseCircleOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-22 10:15:48', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (16, 0, 0, 6, 4, 0, '/devOps/userLoginHistory', NULL, '用户登录历史', './devOps/UserLoginHistory', '用户登录历史', 'PicRightOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-22 11:01:08', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (17, 0, 0, 6, 5, 0, '/devOps/operationLog', NULL, '操作日志记录', './devOps/OperationLog', '操作日志记录', 'RadiusBottomrightOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-22 13:21:10', '1', '2020-12-22 17:14:54', NULL);
INSERT INTO `sys_console_menu` VALUES (18, 0, 0, 0, 101, 0, '/code', NULL, '代码管理', './Welcome', '代码管理', 'CodeOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-23 20:26:05', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (19, 0, 0, 18, 1, 0, '/code/DataSource', NULL, '数据源管理', './code/DataSource', '数据源管理', 'FundProjectionScreenOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-23 21:01:01', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (20, 0, 0, 18, 2, 0, '/code/codeGenerator', NULL, '代码生成', './code/CodeGenerator', '代码生成', 'CodeOutlined', NULL, 0, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-28 15:39:26', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (21, 0, 0, 20, 1, 0, '/code/codeGenerator/tableGen', NULL, '按表生成代码', './code/generator/TableGen', '按表生成代码', 'SmileOutlined', NULL, 1, '[\"/code/codeGenerator\"]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-28 16:59:54', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (22, 0, 0, 20, 2, 0, '/code/codeGenerator/microServiceGen', NULL, '生成微服务模块', './code/generator/MicroServiceGen', '生成微服务模块', 'BorderOuterOutlined', NULL, 1, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2020-12-31 17:45:02', NULL, NULL, NULL);
INSERT INTO `sys_console_menu` VALUES (23, 0, 0, 20, 3, 0, '/code/codeGenerator/entityGen', NULL, '生成实体bean', './code/codeGenerator/EntityGen', '生成实体bean', 'PicLeftOutlined', NULL, 1, '[]', 1, 1, 1, 'mix', 'light', 1, 1, 'dark', 0, '1', '2021-01-04 15:41:18', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_gateway_intercept_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_gateway_intercept_log`;
CREATE TABLE `sys_gateway_intercept_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `intercept_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '拦截类型，REMOTE-来源拦截，TARGET-访问拦截',
  `request_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求ip',
  `request_port` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求来源端口',
  `request_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '请求的资源',
  `intercept_time` bigint(13) NULL DEFAULT NULL COMMENT '拦截时间戳',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '网关拦截记录表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网关路由信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gateway_route
-- ----------------------------
INSERT INTO `sys_gateway_route` VALUES (1, 1, 'CLIENT', 'xqoo-client', 'xqoo-client', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/client/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '测试服务端1', '2019-11-14 12:33:50', 'initData', '2019-11-21 11:54:56', '1', '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (2, 1, 'SYSTEM', 'xqoo-cloud-authorization', 'xqoo-cloud-authorization', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/authorization/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '授权中心', '2019-11-14 12:33:50', 'initData', '2019-11-21 11:54:56', '1', '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (3, 1, 'SYSTEM', 'xqoo-cloud-operation-log', 'xqoo-cloud-operation-log', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/operLog/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '操作日志', '2019-11-14 12:33:50', 'initData', '2019-11-21 11:54:56', '1', '初始化数据');
INSERT INTO `sys_gateway_route` VALUES (4, 1, 'SYSTEM', 'xqoo-cloud-code-generator', 'xqoo-cloud-code-generator', '[{\"name\":\"Path\",\"args\":{\"_genkey_0\":\"/codeGen/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 0, '代码生成', '2019-11-14 12:33:50', 'initData', '2019-11-21 11:54:56', '1', '初始化数据');

-- ----------------------------
-- Table structure for sys_gateway_route_filters_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_gateway_route_filters_config`;
CREATE TABLE `sys_gateway_route_filters_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id主键',
  `filters_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '过滤属性类型，args-[只有一个值，key为_genkey_家数字],map-[值有多个，key为具体属性名称，value为属性值]',
  `filters_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '过滤属性名称',
  `filters_tips` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '过滤器说明文本',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `remark_tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网关路由过滤器配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gateway_route_filters_config
-- ----------------------------
INSERT INTO `sys_gateway_route_filters_config` VALUES (1, 'args', 'PrefixPath', 'PrefixPath GatewayFilter 只有一个 prefix 参数.', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (2, 'args', 'StripPrefix', 'StripPrefix GatewayFilter Factory 包括一个parts参数。 parts参数指示在将请求发送到下游之前，要从请求中去除的路径中的节数。', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (3, 'args', 'AddRequestHeader', '采用一对名称和值作为参数，例:AddRequestHeader=X-Request-Foo, Bar|对于所有匹配的请求，这将向下游请求的头中添加 x-request-foo:bar header', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (4, 'args', 'AddRequestParameter', '采用一对名称和值作为参数，例:AddRequestParameter=foo, bar|对于所有匹配的请求，这将向下游请求添加foo=bar查询字符串', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (5, 'args', 'AddResponseHeader', '采用一对名称和值作为参数，例:AddResponseHeader=X-Response-Foo, Bar|对于所有匹配的请求，这会将x-response-foo:bar头添加到下游响应的header中', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (6, 'map', 'Hystrix', 'Hystrix 是Netflix开源的断路器组件。Hystrix GatewayFilter允许你向网关路由引入断路器，保护你的服务不受级联故障的影响，并允许你在下游故障时提供fallback响应。\n\n要在项目中启用Hystrix网关过滤器，需要添加对 spring-cloud-starter-netflix-hystrix的依赖 Spring Cloud Netflix.\n\nhystrix过滤器还可以接受可选的fallbackUri 参数。目前，仅支持forward: 预设的URI，如果调用fallback，则请求将转发到与URI匹配的控制器。', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (7, 'map', 'FallbackHeaders', 'FallbackHeaders允许在转发到外部应用程序中的FallbackUri的请求的header中添加Hystrix异常详细信息', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (8, 'args', 'PreserveHostHeader', '该filter没有参数。设置了该Filter后，GatewayFilter将不使用由HTTP客户端确定的host header ，而是发送原始host header 。', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (9, 'map', 'RequestRateLimiter', 'redis-rate-limiter.replenishRate是你允许用户每秒执行多少请求，而丢弃任何请求。这是令牌桶的填充速率。\n\n``redis-rate-limiter.burstCapacity`是允许用户在一秒钟内执行的最大请求数。这是令牌桶可以保存的令牌数。将此值设置为零将阻止所有请求。\n\n稳定速率是通过在replenishRate 和 burstCapacity中设置相同的值来实现的。可通过设置burstCapacity高于replenishRate来允许临时突发流浪。在这种情况下，限流器需要在两次突发之间留出一段时间（根据replenishRate），因为连续两次突发将导致请求丢失 (HTTP 429 - Too Many Requests).限流器也可以定义为RateLimiter接口的实现 bean。在配置中，按名称使用SpEL引用bean。#{@myRateLimiter}是引用名为\'myRateLimiter\'的bean的SpEL表达式。', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (10, 'args', 'RedirectTo', '该过滤器有一个 status 和一个 url参数。status是300类重定向HTTP代码，如301。该URL应为有效的URL，这将是 Location header的值', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (11, 'args', 'RemoveRequestHeader', '有一个name参数. 这是要删除的header的名称', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (12, 'args', 'RemoveResponseHeader', '有一个name参数. 这是要删除的header的名称', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (13, 'args', 'RewritePath', '包含一个 regexp正则表达式参数和一个 replacement 参数. 通过使用Java正则表达式灵活地重写请求路径', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (14, 'args', 'RewriteResponseHeader', '包含 name, regexp和 replacement 参数.。通过使用Java正则表达式灵活地重写响应头的值。', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (15, 'args', 'SaveSession', 'SaveSession GatewayFilter Factory将调用转发到下游之前强制执行WebSession::save 操作。这在使用 Spring Session 之类时特别有用，需要确保会话状态在进行转发调用之前已保存', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (16, 'args', 'SetPath', 'SetPath GatewayFilter Factory 采用 template路径参数。它提供了一种通过允许路径的模板化segments来操作请求路径的简单方法。使用Spring Framework中的URI模板，允许多个匹配segments', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (17, 'args', 'SetResponseHeader', 'SetResponseHeader GatewayFilter Factory 包括 name 和 value 参数.', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (18, 'args', 'SetStatus', 'SetStatus GatewayFilter Factory 包括唯一的 status参数.必须是一个可用的Spring HttpStatus。它可以是整数值404或字符串枚举NOT_FOUND', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (19, 'map', 'Retry', 'Retry GatewayFilter Factory包括 retries, statuses, methods和 series 参数.\n\nretries: 应尝试的重试次数\nstatuses: 应该重试的HTTP状态代码，用org.springframework.http.HttpStatus标识\nmethods: 应该重试的HTTP方法，用 org.springframework.http.HttpMethod标识\nseries: 要重试的一系列状态码，用 org.springframework.http.HttpStatus.Series标识\r\n', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (20, 'map', 'RequestSize', '当请求大小大于允许的限制时，RequestSize GatewayFilter Factory可以限制请求不到达下游服务。过滤器以RequestSize作为参数，这是定义请求的允许大小限制(以字节为单位)', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (21, 'map', 'ImgCodeFilter', '自定义过滤器，图片的验证码', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');
INSERT INTO `sys_gateway_route_filters_config` VALUES (22, 'map', 'IgnoreFilter', '自定义过滤器，不需要进全局过滤的配置', '2019-11-19 23:22:23', 'Bluce', NULL, NULL, '初始化数据');

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网关路由断言项目皮遏制表' ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `admin` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否超级管理员，此角色有且仅有一个，初始化数据',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标志，0-未删除，1-已删除',
  `role_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '角色的key值，自定义，英文字符，不超过32字',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '角色名称，中文名称，不超过16字',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark_tips` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, 0, 'super:admin', '超级管理员', 'system', '2020-12-06 12:49:06', NULL, NULL, '初始化数据');
INSERT INTO `sys_role` VALUES (2, 0, 0, 'console:admin', '后台管理员', 'system', '2020-12-06 12:49:06', '1', '2020-12-15 13:08:31', '初始化数据');

-- ----------------------------
-- Table structure for sys_role_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_api`;
CREATE TABLE `sys_role_api`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `role_id` int(20) UNSIGNED NOT NULL COMMENT '角色id',
  `api_id` int(20) UNSIGNED NOT NULL COMMENT 'api接口id',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark_tips` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '角色和api接口关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `menu_id` int(20) UNSIGNED NOT NULL COMMENT '菜单id',
  `role_id` int(20) UNSIGNED NOT NULL COMMENT '角色id',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark_tips` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 4, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '用户id',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除标记，0-未删除，1-已删除',
  `login_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名，为考虑一手机多用户，此处必须与手机并存唯一，不能使用特殊字符',
  `user_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态，0-正常，1-封禁，2-停用',
  `user_type` tinyint(2) UNSIGNED NOT NULL DEFAULT 10 COMMENT '用户类型，99-超级管理员，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，9-临时用户，后续有新的用户类型继续在中间相加',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '未填写昵称' COMMENT '用户名，最多32字',
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '密码，允许为空，为空时密码登录将无效',
  `salt` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '盐，随机8位字母+数字',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `profile_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '头像地址',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最近修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最近修改时间',
  `remark_tips` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 0, 'xqoo_admin', 0, 99, '超级管理员', '43dc278131a676892264ab29c321747179d8a401ab7f65e50c16ea66', 'iRX9B2', '2021-01-04 14:04:47', NULL, 'system', '2020-11-21 14:58:13', '127.0.0.1', '2021-01-04 14:04:48', NULL);
INSERT INTO `sys_user` VALUES ('4963646974743560196', 0, 'admin', 0, 88, '管理员', '43dc278131a676892264ab29c321747179d8a401ab7f65e50c16ea66', 'iRX9B2', '2020-12-24 00:04:19', NULL, 'system', '2020-11-21 14:58:13', '127.0.0.1', '2020-12-24 00:04:19', NULL);

-- ----------------------------
-- Table structure for sys_user_login_history
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_history`;
CREATE TABLE `sys_user_login_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '对应用户id',
  `login_date` bigint(20) NOT NULL COMMENT '登录时间',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '登录ip',
  `login_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '使用何种方式登录，具体参见系统中枚举',
  `login_type_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录方式的名字',
  `login_env` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录环境，一些文本描述',
  `login_source` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '登录请求来源，网站，APP等',
  `login_source_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '登录请求来源，网站，APP等',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '用户登录历史记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT 'userId',
  `role_id` int(20) NOT NULL COMMENT '关联的roleId',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  `remark_tips` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, '1', 1, 'system', '2020-12-06 20:39:33', NULL, NULL, '系统初始化数据');
INSERT INTO `sys_user_role` VALUES (2, '4963646974743560196', 2, '1', '2020-12-20 10:19:00', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for worker_node
-- ----------------------------
DROP TABLE IF EXISTS `worker_node`;
CREATE TABLE `worker_node`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `HOST_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'host name',
  `PORT` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'port',
  `TYPE` int(11) NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
  `LAUNCH_DATE` date NOT NULL COMMENT 'launch date',
  `MODIFIED` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'modified time',
  `CREATED` timestamp(0) NOT NULL COMMENT 'created time',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'DB WorkerID Assigner for UID Generator' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worker_node
-- ----------------------------
INSERT INTO `worker_node` VALUES (1, '192.168.0.5', '1608725405814-65395', 2, '2020-12-23', '2020-12-23 20:10:05', '2020-12-23 20:10:05');
INSERT INTO `worker_node` VALUES (2, '192.168.0.107', '1609204061799-30070', 2, '2020-12-29', '2020-12-29 09:07:42', '2020-12-29 09:07:42');

SET FOREIGN_KEY_CHECKS = 1;
