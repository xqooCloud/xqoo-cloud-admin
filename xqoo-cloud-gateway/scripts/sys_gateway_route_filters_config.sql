/*
 Navicat Premium Data Transfer

 Source Server         : law-web-online
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 47.92.193.46:3306
 Source Schema         : lvyingdb

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 08/01/2020 22:56:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
