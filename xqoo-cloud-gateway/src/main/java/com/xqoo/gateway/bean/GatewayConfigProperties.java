package com.xqoo.gateway.bean;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.common.core.utils.SpringPathMather;
import com.xqoo.common.core.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ConfigurationProperties(prefix = "xqoo.gateway")
public class GatewayConfigProperties {

    // 访问目的url黑名单
    private List<GatewayUrlList> targetBlackList;

    // 不需要登录的路径
    private List<GatewayUrlList> noLoginFilter;

    // 系统内置过滤的状态
    private List<FilterHttpStatus> filterHttpStatus;

    // 来源ip禁止黑名单
    private List<GatewayUrlList> remoteBlackList;

    @Override
    public String toString() {
        return "GatewayConfigProperties{" +
                "targetBlackList=" + targetBlackList +
                ", noLoginFilter=" + noLoginFilter +
                ", filterHttpStatus=" + filterHttpStatus +
                ", remoteBlackList=" + remoteBlackList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatewayConfigProperties that = (GatewayConfigProperties) o;
        return Objects.equals(targetBlackList, that.targetBlackList) &&
                Objects.equals(noLoginFilter, that.noLoginFilter) &&
                Objects.equals(filterHttpStatus, that.filterHttpStatus) &&
                Objects.equals(remoteBlackList, that.remoteBlackList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetBlackList, noLoginFilter, filterHttpStatus, remoteBlackList);
    }

    public List<GatewayUrlList> getTargetBlackList() {
        if(this.targetBlackList == null){
            return Collections.emptyList();
        }
        return targetBlackList;
    }

    public List<String> getTargetBlackListFilter(){
        return getUrlListFilter(this.targetBlackList);
    }

    private List<String> getUrlListFilter (List<GatewayUrlList> list) {
        if(CollUtil.isEmpty(list)){
            list = Collections.emptyList();
        }
        List<String> filterList = new ArrayList<>(list.size());
        list.forEach(item -> {
            StringBuilder filter = new StringBuilder();
            if(StringUtils.isNotEmpty(item.getHost()) && StringUtils.isEmpty(item.getPath())){
                filterList.add("/");
                filter.append(item.getHost());
                filter.append("/**");
                filterList.add(filter.toString());
                return;
            }
            if(StringUtils.isEmpty(item.getHost()) && StringUtils.isNotEmpty(item.getPath())){
                filter.append("/*");
                filter.append(item.getPath());
                filterList.add(filter.toString());
                return;
            }
            if(StringUtils.isNotEmpty(item.getHost()) && StringUtils.isNotEmpty(item.getPath())){
                filter.append("/");
                filter.append(item.getHost());
                filter.append(item.getPath());
                filterList.add(filter.toString());
            }
        });
        return filterList;
    }

    public void setTargetBlackList(List<GatewayUrlList> targetBlackList) {
        this.targetBlackList = targetBlackList;
    }

    public List<FilterHttpStatus> getFilterHttpStatus() {
        if(this.filterHttpStatus == null){
            return Collections.emptyList();
        }
        return filterHttpStatus;
    }

    public List<GatewayUrlList> getNoLoginFilter() {
        return noLoginFilter;
    }

    public void setNoLoginFilter(List<GatewayUrlList> noLoginFilter) {
        this.noLoginFilter = noLoginFilter;
    }

    public List<String> getNoLoginListFilter(){
        return getUrlListFilter(this.noLoginFilter);
    }

    public void setFilterHttpStatus(List<FilterHttpStatus> filterHttpStatus) {
        this.filterHttpStatus = filterHttpStatus;
    }

    public List<GatewayUrlList> getRemoteBlackList() {
        return remoteBlackList;
    }

    // 获取拦截ip的通配过滤公式
    public List<String> getRemoteBlackListFilter() {
        if(CollUtil.isEmpty(this.remoteBlackList)){
            this.remoteBlackList = Collections.emptyList();
        }
        List<String> list = new ArrayList<>(this.remoteBlackList.size());
        this.remoteBlackList.forEach(item -> {
            StringBuilder filter = new StringBuilder();
            if(StringUtils.isEmpty(item.getHost())){
                return;
            }
            if(StringUtils.isNotEmpty(item.getHost()) && CollUtil.isEmpty(item.getPort())){
                filter.append(item.getHost());
                filter.append(":**");
                list.add(filter.toString());
                return;
            }
            if(StringUtils.isNotEmpty(item.getHost()) && CollUtil.isNotEmpty(item.getPort())){
                // 判断是否有 * 或者 ** 的子项，有的话移除以增加的
                List<String> portList = new ArrayList<>(item.getPort().size());
                for (String portItem : item.getPort()) {
                    StringBuilder portFilter = new StringBuilder();
                    if(StringUtils.isEmpty(portItem)){
                        continue;
                    }
                    if(SpringPathMather.isAllMatch(portItem)){
                        portList.clear();
                        portFilter.append(item.getHost());
                        portFilter.append(":**");
                        portList.add(portFilter.toString());
                        break;
                    }

                    portFilter.append(item.getHost());
                    portFilter.append(":");
                    portFilter.append(portItem);
                    portList.add(portFilter.toString());
                }
                list.addAll(portList);
            }
        });
        return list;
    }

    public void setRemoteBlackList(List<GatewayUrlList> remoteBlackList) {
        this.remoteBlackList = remoteBlackList;
    }

    public static class GatewayUrlList{
        // 主机域名或ip
        private String host;
        // 当前域名路径
        private String path;
        // 当前address的端口
        private List<String> port;

        @Override
        public String toString() {
            return "GatewayBlackList{" +
                    "host='" + host + '\'' +
                    ", path='" + path + '\'' +
                    ", port='" + port + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GatewayUrlList that = (GatewayUrlList) o;
            return Objects.equals(host, that.host) &&
                    Objects.equals(path, that.path) &&
                    Objects.equals(port, that.port);
        }

        @Override
        public int hashCode() {
            return Objects.hash(host, path, port);
        }

        public String getHost() {
            if(this.host == null){
                return null;
            }
            return StringUtils.removeUrlHeader(this.host);
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<String> getPort() {
            if(this.port == null){
                return Collections.emptyList();
            }
            return port;
        }

        public void setPort(List<String> port) {
            this.port = port;
        }
    }


    public static class FilterHttpStatus{
        // 状态码
        private Integer statusCode;
        // 错误原因
        private String reason;

        @Override
        public String toString() {
            return "FilterHttpStatus{" +
                    "statusCode=" + statusCode +
                    ", reason='" + reason + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FilterHttpStatus that = (FilterHttpStatus) o;
            return Objects.equals(statusCode, that.statusCode) &&
                    Objects.equals(reason, that.reason);
        }

        @Override
        public int hashCode() {
            return Objects.hash(statusCode, reason);
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
