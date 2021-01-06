package com.xqoo.gateway.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.gateway.bean.*;
import com.xqoo.gateway.config.GetRoutesConfig;
import com.xqoo.gateway.config.RedisRouteDefinitionRepository;
import com.xqoo.gateway.mapper.GatewayFiltersConfigMapper;
import com.xqoo.gateway.mapper.GatewayPredicatesConfigMapper;
import com.xqoo.gateway.mapper.GatewayRouteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author gaoyang
 * @create 2019/11/14
 * 初始化路由信息，读取数据库中的路由配置加载到网关中
 */

@Service
public class GatewayServiceHandler implements ApplicationEventPublisherAware, CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(GatewayServiceHandler.class);

    @Autowired
    private RedisRouteDefinitionRepository routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;
    @Autowired
    private GatewayPredicatesConfigMapper gatewayPredicatesConfigMapper;
    @Autowired
    private GatewayFiltersConfigMapper gatewayFiltersConfigMapper;

    @Override
    public void run(String... args){
        GatewayRouteBO routeQueyr = new GatewayRouteBO();
        routeQueyr.setServiceStatus(1);
        this.loadRouteConfig(routeQueyr);
    }

    // 发射路由信息到网关
    public String loadRouteConfig(GatewayRouteBO routeQueyr) {
        //从数据库拿到路由配置
        List<GatewayRouteEntity> gatewayRouteEntityList = this.getRouteInfo(routeQueyr);

        log.info("网关配置信息：=====>"+ gatewayRouteEntityList.toString());
        gatewayRouteEntityList.forEach(gatewayRouteEntity -> {
            RouteDefinition definition = new RouteDefinition();

            URI uri = null;
            if(gatewayRouteEntity.getUri().startsWith("http")){
                //http地址
                uri = UriComponentsBuilder.fromHttpUrl(gatewayRouteEntity.getUri()).build().toUri();
            }else{
                //注册中心
                uri = UriComponentsBuilder.fromUriString("lb://"+ gatewayRouteEntity.getUri()).build().toUri();
            }

            definition.setId(gatewayRouteEntity.getServiceId());
//            definition.setOrder(gatewayRouteEntity.getOrderNo());

            // 名称是固定的，spring gateway会根据名称找对应的PredicateFactory

            List<PredicateDefinition> predicateList = this.getPredicateList(gatewayRouteEntity.getPredicates());

            List<FilterDefinition> filterDefinitionList = this.getFilterList(gatewayRouteEntity.getFilters());

            definition.setPredicates(predicateList);
            definition.setFilters(filterDefinitionList);
            definition.setUri(uri);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        });

        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }


    // 获取前台展示的路由信息

    public PageResponseBean<GatewayRouteEntityVO> getRouteList(GatewayRouteBO gatewayRouteBO){
        Integer count = gatewayRouteMapper.queryRoutesCount(gatewayRouteBO);
        if(count == 0){
            return new PageResponseBean<>();
        }
        PageResponseBean<GatewayRouteEntityVO> responseBean =
                new PageResponseBean<>(gatewayRouteBO.getPage(), gatewayRouteBO.getPageSize(), count);
        PageHelper.startPage(gatewayRouteBO.getPage(), gatewayRouteBO.getPageSize());
        List<GatewayRouteEntity> routeList = this.getRouteInfo(gatewayRouteBO);
        List<GatewayRouteEntityVO> routeVOList = new ArrayList<>();
        List<String> nowRouteList = this.getRouteNow();
        routeList.forEach(routeSingleInfo -> {
                    GatewayRouteEntityVO gatewayRouteVO = new GatewayRouteEntityVO();
                    BeanUtils.copyProperties(routeSingleInfo, gatewayRouteVO);
                    List<PredicateDefinition> predicateList = this.getPredicateList(routeSingleInfo.getPredicates());
                    List<FilterDefinition> filterDefinitionList = this.getFilterList(routeSingleInfo.getFilters());
                    gatewayRouteVO.setFilterDefinitionList(filterDefinitionList);
                    gatewayRouteVO.setPredicateDefinitionList(predicateList);
                    gatewayRouteVO.setIsActive(0);
                    routeVOList.add(gatewayRouteVO);
                });
        routeVOList.stream().filter(tmp -> nowRouteList.contains(tmp.getServiceId()))
                .forEach(item -> item.setIsActive(1));
        responseBean.setContent(routeVOList);
        return responseBean;
    }


    // 获取数据库路由配置
    public List<GatewayRouteEntity> getRouteInfo(GatewayRouteBO routeQueyr){
        return gatewayRouteMapper.queryRoutes(routeQueyr);
    }

    // 获取当前网关中的路由
    public List<String> getRouteNow(){
        List<Object> json = GetRoutesConfig.onHttp();
        List<String> routeList = new ArrayList<>();
        for(int i = 0;i < json.size();i++){
            JsonNode jsonObject = JacksonUtils.transferToJsonNode(json.get(i));
            routeList.add(jsonObject.get("route_id").asText());
        }
        return routeList;
    }

    // 将路由断言的json字符串转为list
    private List<PredicateDefinition> getPredicateList(String predicateStr){
        JsonNode predicateJsonArray = JacksonUtils.toObj(predicateStr);
        List<PredicateDefinition> predicateList = new ArrayList<PredicateDefinition>();
        for(int i =0;i < predicateJsonArray.size(); i++){
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            JsonNode predicateObj = predicateJsonArray.get(i);
            predicateDefinition.setName(predicateObj.get("name").asText());

            Map<String, String> argsMap = JacksonUtils.toObj(predicateObj.get("args"), new TypeReference<Map<String, String>>(){ });
            predicateDefinition.setArgs(argsMap);
            predicateList.add(predicateDefinition);
        }
        return predicateList;
    }

    // 将过滤器的json字符串转为list
    private List<FilterDefinition> getFilterList(String filterStr){
        JsonNode filterJsonArray = JacksonUtils.toObj(filterStr);
        List<FilterDefinition> filterList = new ArrayList<FilterDefinition>();
        for(int i =0;i < filterJsonArray.size(); i++){
            FilterDefinition filterDefinition = new FilterDefinition();
            JsonNode filterObj = filterJsonArray.get(i);
            filterDefinition.setName(filterObj.get("name").asText());

            Map<String, String> argsMap = JacksonUtils.toObj(filterObj.get("args"), new TypeReference<Map<String, String>>(){ });
            filterDefinition.setArgs(argsMap);
            filterList.add(filterDefinition);
        }
        return filterList;
    }

    // 从网关中移除路由
    public void deleteRoute(String routeId) throws Exception{
        routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    // 改变路由状态
    public void changeRouteStatus(GatewayRouteBO gatewayRouteBO) throws  Exception{
        gatewayRouteMapper.changeRouteStatus(gatewayRouteBO);
    }

    // 获取断言配置信息
    public List<GatewayPredicatesConfigEntity> getPredicatesConfigList(GatewayPredicatesConfigEntity gatewayPredicatesConfigEntity){
        return this.gatewayPredicatesConfigMapper.getPredicatesConfigList(gatewayPredicatesConfigEntity);
    }

    // 获取过滤器配置信息
    public List<GatewayFiltersConfigEntity> getFiltersConfigList(GatewayFiltersConfigEntity gatewayFiltersConfigEntity){
        return this.gatewayFiltersConfigMapper.getFiltersConfigList(gatewayFiltersConfigEntity);
    }

    // 插入路由信息
    public void insertRouteInfo(GatewayRouteEntity gatewayRouteEntity){
        this.gatewayRouteMapper.insertRouteInfo(gatewayRouteEntity);
    }
    // 更新路由信息
    public void updateRouteInfo(GatewayRouteEntity gatewayRouteEntity){
        this.gatewayRouteMapper.updateRouteInfo(gatewayRouteEntity);
    }

}
