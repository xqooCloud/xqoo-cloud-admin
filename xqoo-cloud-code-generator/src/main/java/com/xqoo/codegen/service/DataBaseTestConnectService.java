package com.xqoo.codegen.service;

import com.xqoo.codegen.dto.DataBasePropertiesDTO;
import com.xqoo.common.entity.ResultEntity;

public interface DataBaseTestConnectService {

    ResultEntity<String> testDataBaseConnect(DataBasePropertiesDTO dto, String type);
}
