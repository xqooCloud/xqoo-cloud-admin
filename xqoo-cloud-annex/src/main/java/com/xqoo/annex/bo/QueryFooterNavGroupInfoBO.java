package com.xqoo.annex.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("查询脚页分组信息实体")
public class QueryFooterNavGroupInfoBO extends PageRequestBean {


    private static final long serialVersionUID = 6077138806762990916L;
    @ApiModelProperty("分组名称")
    private String groupName;

    @Override
    public String toString() {
        return "QueryFooterNavGroupInfoBO{" +
                "groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QueryFooterNavGroupInfoBO)) return false;
        if (!super.equals(o)) return false;
        QueryFooterNavGroupInfoBO that = (QueryFooterNavGroupInfoBO) o;
        return Objects.equals(getGroupName(), that.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGroupName());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
