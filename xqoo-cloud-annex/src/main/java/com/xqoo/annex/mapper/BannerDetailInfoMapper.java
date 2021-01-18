package com.xqoo.annex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.annex.entity.BannerDetailInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源表(banner_detail_info)表数据库访问层
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:31:34
 */

@Mapper
@Repository
public interface BannerDetailInfoMapper extends BaseMapper<BannerDetailInfoEntity> {

    void insertList(@Param("list") List<BannerDetailInfoEntity> list) throws RuntimeException;
}
