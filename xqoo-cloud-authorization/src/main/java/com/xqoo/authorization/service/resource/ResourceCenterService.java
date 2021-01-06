package com.xqoo.authorization.service.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.authorization.vo.SysConsoleMenuVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

public interface ResourceCenterService {

    JsonNode getUserMenuList(CurrentUser currentUser);

    JsonNode getConsoleMenuInfo();

    ResultEntity<SysConsoleMenuVO> consoleMenuDetailInfo(Integer menuId, CurrentUser currentUser);

    JsonNode getFactAndNoRedirectMenuDetailInfo(CurrentUser currentUser);
}
