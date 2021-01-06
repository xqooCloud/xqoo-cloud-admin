package com.xqoo.gateway.mapper;

import com.xqoo.gateway.bean.GatewayFiltersConfigEntity;
import com.xqoo.gateway.bean.GatewayRouteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GatewayFiltersConfigMapper {

    List<GatewayFiltersConfigEntity> getFiltersConfigList(GatewayFiltersConfigEntity gatewayFiltersConfigEntity);


}
