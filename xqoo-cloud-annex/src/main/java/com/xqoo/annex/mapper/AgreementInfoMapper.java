package com.xqoo.annex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.annex.entity.AgreementInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(agreement_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 14:56:53
 */

@Mapper
@Repository
public interface AgreementInfoMapper extends BaseMapper<AgreementInfoEntity> {

    void insertList(@Param("list") List<AgreementInfoEntity> list) throws RuntimeException;
}
