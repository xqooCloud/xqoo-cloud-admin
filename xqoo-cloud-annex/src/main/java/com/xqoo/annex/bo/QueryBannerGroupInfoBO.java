package com.xqoo.annex.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("查询轮播分组信息实体")
public class QueryBannerGroupInfoBO extends PageRequestBean {


    private static final long serialVersionUID = 8077138806762990916L;
    @ApiModelProperty("分组名称")
    private String groupName;

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
