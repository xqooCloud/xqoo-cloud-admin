package com.xqoo.gateway.controller;

import com.google.common.base.Optional;
import com.xqoo.common.core.exception.SystemException;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.gateway.bean.GatewayFiltersConfigEntity;
import com.xqoo.gateway.bean.GatewayPredicatesConfigEntity;
import com.xqoo.gateway.bean.GatewayRouteBO;
import com.xqoo.gateway.bean.GatewayRouteEntity;
import com.xqoo.gateway.service.GatewayServiceHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/sysConsole/gatewayRoute")
public class RouteController {

    private static Logger log = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private GatewayServiceHandler gatewayServiceHandler;

    /**
     * 刷新路由配置
     */
    @GetMapping("/refresh")
    public ResultEntity refresh(){
        try{
            GatewayRouteBO routeQueyr = new GatewayRouteBO();
            routeQueyr.setServiceStatus(1);
            this.gatewayServiceHandler.loadRouteConfig(routeQueyr);

            log.info("[路由管理中心]:刷新网关路由成功");
            return new ResultEntity<>(HttpStatus.OK, "刷新网关路由成功");
        }catch(Exception e){
            e.printStackTrace();
            log.error("[路由管理中心]:刷新网关路由失败:" + e.getMessage());
            throw new SystemException("网关路由刷新失败", e);
        }
    }

    /**
     * 查询路由配置
     */
    @PostMapping("/getRouteList")
    public ResultEntity getRouteList(@RequestBody GatewayRouteBO gatewayRouteBO){
        try{
            return new ResultEntity<>(HttpStatus.OK,"获取网关路由成功", gatewayServiceHandler.getRouteList(gatewayRouteBO));
        }catch(Exception e){
            e.printStackTrace();
            log.error("[路由管理中心]:获取网关路由失败:" + e.getMessage());
            throw new SystemException("获取网关路由信息失败", e);
        }
    }

    /**
     * 开启路由接口
     *
     * @param serviceId
     * @return
     */
    @GetMapping("/openClient")
    public ResultEntity add(@RequestParam String serviceId){
        if(StringUtils.isEmpty(serviceId)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "操作失败，必须指定一个需要开启的路由的id");
        }
        try{
            GatewayRouteBO routeQueyr = new GatewayRouteBO();
            routeQueyr.setServiceStatus(1);
            routeQueyr.setServiceId(serviceId);
            if(gatewayServiceHandler.getRouteInfo(routeQueyr).size() == 0){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"未查询到路由:["
                        + serviceId + "]信息，不能开启");
            }
            gatewayServiceHandler.loadRouteConfig(routeQueyr);
            log.info("[路由管理中心]:成功启动路由:[" + serviceId + "]");
            return new ResultEntity<>(HttpStatus.OK,"成功启动路由:[" + serviceId + "]");
        }catch(Exception e){
            e.printStackTrace();
            log.info("[路由管理中心]:启动路由失败:" + e.getMessage());
            throw new SystemException("启动路由失败", e);
        }
    }


    /**
     * 关闭服务路由接口

     */

    @GetMapping("/closeClient")
    public ResultEntity delete(@RequestParam String serviceId){
        if(StringUtils.isEmpty(serviceId)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "操作失败，必须指定一个需要关闭的路由的id");
        }
        try{
            gatewayServiceHandler.deleteRoute(serviceId);
            log.info("[路由管理中心]:成功关闭路由:[" + serviceId + "]");
            return new ResultEntity<>(HttpStatus.OK, "成功关闭路由:[" + serviceId + "]");
        }catch(Exception e){
            e.printStackTrace();
            log.info("[路由管理中心]:移除路由失败:" + e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "移除路由失败:" + e.getMessage());
        }

    }

    /**
     * 置为不可用服务

     */

    @PostMapping("/denyClient")
    public ResultEntity denyClient(@RequestBody GatewayRouteBO gatewayRouteBO){
        if(StringUtils.isEmpty(gatewayRouteBO.getServiceId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "操作失败，必须指定一个需要删除的路由的id");
        }
        gatewayRouteBO.setServiceStatus(0);
        try{
            gatewayServiceHandler.changeRouteStatus(gatewayRouteBO);
            gatewayServiceHandler.deleteRoute(gatewayRouteBO.getServiceId());
            log.info("[路由管理中心]:成功删除路由:[" + gatewayRouteBO.getServiceId() + "]");
            return new ResultEntity<>(HttpStatus.OK, "成功删除路由:[" + gatewayRouteBO.getServiceId() + "]");
        }catch(Exception e){
            e.printStackTrace();
            log.info("[路由管理中心]:移除路由失败:" + e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "移除路由失败:" + e.getMessage());
        }

    }

    /**
     * 恢复为可用服务

     */

    @PostMapping("/resetClient")
    public ResultEntity resetClient(@RequestBody GatewayRouteBO gatewayRouteBO){
        if(StringUtils.isEmpty(gatewayRouteBO.getServiceId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "操作失败，必须指定一个需要恢复的路由的id");
        }
        gatewayRouteBO.setServiceStatus(1);
        try{
            gatewayServiceHandler.changeRouteStatus(gatewayRouteBO);
            log.info("[路由管理中心]:成功恢复路由:[" + gatewayRouteBO.getServiceId() + "]");
            return new ResultEntity<>(HttpStatus.OK, "成功恢复路由:[" + gatewayRouteBO.getServiceId() + "]");
        }catch(Exception e){
            e.printStackTrace();
            log.info("[路由管理中心]:恢复路由失败:" + e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "恢复路由失败:" + e.getMessage());
        }

    }

    /**
     * 获取断言器配置信息

     */
    @GetMapping("/getPredicatesList")
    public ResultEntity getPredicatesList(){
        GatewayPredicatesConfigEntity gatewayPredicatesConfigEntity = new GatewayPredicatesConfigEntity();
        return new ResultEntity<>(HttpStatus.OK, "获取断言器配置成功", this.gatewayServiceHandler.getPredicatesConfigList(gatewayPredicatesConfigEntity));
    }

    /**
     * 获取过滤器配置信息
     * */
    @GetMapping("/getFiltersList")
    public ResultEntity getFiltersList(){
        GatewayFiltersConfigEntity gatewayFiltersConfigEntity = new GatewayFiltersConfigEntity();
        return new ResultEntity<>(HttpStatus.OK, "获取过滤器配置成功", this.gatewayServiceHandler.getFiltersConfigList(gatewayFiltersConfigEntity));
    }

    /**
     * 获取过滤器配置信息
     * */
    @PostMapping("/putRouteInfo")
    public ResultEntity putRouteInfo(@RequestBody GatewayRouteEntity gatewayRouteEntity){
        GatewayRouteBO routeQueyr = new GatewayRouteBO();
        routeQueyr.setServiceId(gatewayRouteEntity.getServiceId());
        List<GatewayRouteEntity> list = gatewayServiceHandler.getRouteInfo(routeQueyr);
        String userId = "userId";
        if(StringUtils.isEmpty(gatewayRouteEntity.getFilters()) || "[]".equals(gatewayRouteEntity.getFilters())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"新增路由信息失败:[过滤器属性为空，不允许新增]");
        }
        if(StringUtils.isEmpty(gatewayRouteEntity.getPredicates()) || "[]".equals(gatewayRouteEntity.getPredicates())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"新增路由信息失败:[断言属性为空，不允许新增]");
        }
        if(!Optional.fromNullable(gatewayRouteEntity.getId()).isPresent()){
            if(list.size() > 0){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"新增路由信息失败:["
                        + gatewayRouteEntity.getServiceId() + "]信息已存在，不能新增");
            }
            try{
                gatewayRouteEntity.setCreateDate(new Date());
                gatewayRouteEntity.setCreateBy(userId);
                gatewayRouteEntity.setServiceStatus(1);
                gatewayServiceHandler.insertRouteInfo(gatewayRouteEntity);
                return new ResultEntity<>(HttpStatus.OK, "新增成功");
            }catch (Exception e){
                e.printStackTrace();
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "[新增路由失败]：" + e.getMessage());
            }
        } else {
            if(list.size() == 0){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"修改路由信息失败:["
                        + gatewayRouteEntity.getServiceId() + "]信息不存在，请联系开发人员处理");
            }
            try{
                gatewayRouteEntity.setUpdateDate(new Date());
                gatewayRouteEntity.setUpdateBy(userId);
                gatewayServiceHandler.updateRouteInfo(gatewayRouteEntity);
                return new ResultEntity<>(HttpStatus.OK, "修改成功");
            }catch (Exception e){
                e.printStackTrace();
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "[修改路由失败]：" + e.getMessage());
            }
        }

    }
}
