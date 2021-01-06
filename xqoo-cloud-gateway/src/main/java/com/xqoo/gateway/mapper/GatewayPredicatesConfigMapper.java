package com.xqoo.gateway.mapper;

import com.xqoo.gateway.bean.GatewayPredicatesConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GatewayPredicatesConfigMapper {

    List<GatewayPredicatesConfigEntity> getPredicatesConfigList(GatewayPredicatesConfigEntity gatewayPredicatesConfigEntity);
}
