package com.xqoo.gateway.mapper;

import com.xqoo.gateway.bean.GatewayRouteEntity;
import com.xqoo.gateway.bean.GatewayRouteBO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GatewayRouteMapper {
    List<GatewayRouteEntity> queryRoutes(GatewayRouteBO gatewayRoute);

    Integer queryRoutesCount(GatewayRouteBO gatewayRoute);

    void changeRouteStatus(GatewayRouteBO gatewayRoute);

    void insertRouteInfo(GatewayRouteEntity gatewayRouteEntity);

    void updateRouteInfo(GatewayRouteEntity gatewayRouteEntity);
}
