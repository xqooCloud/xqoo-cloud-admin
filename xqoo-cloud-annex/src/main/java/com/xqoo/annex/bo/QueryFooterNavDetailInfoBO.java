package com.xqoo.annex.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("查询脚页分组信息实体")
public class QueryFooterNavDetailInfoBO extends PageRequestBean {


    private static final long serialVersionUID = 6077138806762990916L;
    @ApiModelProperty("分组id")
    private Integer groupId;

    @Override
    public String toString() {
        return "QueryFooterNavGroupInfoBO{" +
                "groupId='" + groupId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QueryFooterNavDetailInfoBO)) return false;
        if (!super.equals(o)) return false;
        QueryFooterNavDetailInfoBO that = (QueryFooterNavDetailInfoBO) o;
        return Objects.equals(getGroupId(), that.getGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGroupId());
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }
}
