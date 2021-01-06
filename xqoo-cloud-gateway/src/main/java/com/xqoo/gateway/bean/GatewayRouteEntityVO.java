package com.xqoo.gateway.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.util.List;
import java.util.Objects;

@ApiModel("路由信息VO层")
public class GatewayRouteEntityVO extends GatewayRouteEntity {

    @ApiModelProperty(value="路由断言器配置列表")
    private List<PredicateDefinition> predicateDefinitionList;

    @ApiModelProperty(value="路由过滤器配置列表")
    private List<FilterDefinition> filterDefinitionList;

    @ApiModelProperty(value = "是否正在运行的路由，0-否，1-是,默认0")
    private Integer isActive = 0;

    public List<PredicateDefinition> getPredicateDefinitionList() {
        return predicateDefinitionList;
    }

    public void setPredicateDefinitionList(List<PredicateDefinition> predicateDefinitionList) {
        this.predicateDefinitionList = predicateDefinitionList;
    }

    public List<FilterDefinition> getFilterDefinitionList() {
        return filterDefinitionList;
    }

    public void setFilterDefinitionList(List<FilterDefinition> filterDefinitionList) {
        this.filterDefinitionList = filterDefinitionList;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GatewayRouteEntityVO that = (GatewayRouteEntityVO) o;
        return Objects.equals(predicateDefinitionList, that.predicateDefinitionList) &&
                Objects.equals(filterDefinitionList, that.filterDefinitionList) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), predicateDefinitionList, filterDefinitionList, isActive);
    }

    @Override
    public String toString() {
        return "GatewayRouteEntityVO{" +
                "predicateDefinitionList=" + predicateDefinitionList +
                ", filterDefinitionList=" + filterDefinitionList +
                ", isActive=" + isActive +
                '}';
    }
}
