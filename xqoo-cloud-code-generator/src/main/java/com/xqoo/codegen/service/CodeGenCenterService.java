package com.xqoo.codegen.service;

import com.xqoo.codegen.bo.EntityGenCodeBo;
import com.xqoo.codegen.bo.MicroServiceGeneratorBO;
import com.xqoo.codegen.bo.TableGeneratorBO;
import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.entity.TableEntity;
import com.xqoo.codegen.pojo.TemplateDataPOJO;
import com.xqoo.codegen.vo.GeneratorCodeVO;
import com.xqoo.common.entity.ResultEntity;

import java.util.List;

public interface CodeGenCenterService {

    ResultEntity<List<TableEntity>> getDataSourceTables(Integer id);

    ResultEntity<String> removeCacheTables(Integer id);

    ResultEntity<List<TemplateDataPOJO>> getTemplateInfo(String type);

    List<TableColumnsEntity> getTableColumns(String tableName, Integer id);

    ResultEntity<String> removeCacheColumns(String tableName, Integer id);

    ResultEntity<GeneratorCodeVO> tableGeneratorCode(TableGeneratorBO bo);

    ResultEntity<GeneratorCodeVO> microServiceGeneratorCode(MicroServiceGeneratorBO bo);

    ResultEntity<GeneratorCodeVO> entityGeneratorCode(EntityGenCodeBo bo);
}
