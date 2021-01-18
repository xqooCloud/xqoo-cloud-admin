package com.xqoo.annex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.annex.entity.BannerGroupInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(banner_group_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:45:34
 */

@Mapper
@Repository
public interface BannerGroupInfoMapper extends BaseMapper<BannerGroupInfoEntity> {

    void insertList(@Param("list") List<BannerGroupInfoEntity> list) throws RuntimeException;
}
