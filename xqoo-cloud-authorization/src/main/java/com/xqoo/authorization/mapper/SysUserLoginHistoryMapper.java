package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.entity.SysUserLoginHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户登录历史记录(SysUserLoginHistory)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-26 17:18:51
 */
@Mapper
@Repository
public interface SysUserLoginHistoryMapper extends BaseMapper<SysUserLoginHistoryEntity> {

}
