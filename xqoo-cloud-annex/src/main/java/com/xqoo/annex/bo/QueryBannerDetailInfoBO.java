package com.xqoo.annex.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询轮播信息实体")
public class QueryBannerDetailInfoBO extends PageRequestBean {


    private static final long serialVersionUID = 9077138806762990916L;

    @ApiModelProperty("分组类型")
    private String groupId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
