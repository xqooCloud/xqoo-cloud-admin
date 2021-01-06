package com.xqoo.codegen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.codegen.bo.QueryDataSourceBO;
import com.xqoo.codegen.entity.DataSourceInfoEntity;
import com.xqoo.codegen.vo.DataSourceInfoVO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;

/**
 * 数据源表(DataSourceInfo)表服务接口
 *
 * @author makejava
 * @since 2020-12-23 13:37:40
 */
public interface DataSourceInfoService extends IService<DataSourceInfoEntity> {

    ResultEntity<PageResponseBean<DataSourceInfoVO>> pageGetDataSourceInfo(QueryDataSourceBO bo);

    ResultEntity<String> deleteDataSource(Integer id);

    ResultEntity<String> updateDataSource(DataSourceInfoVO vo);

    ResultEntity<String> addDataSource(DataSourceInfoVO vo);
}
