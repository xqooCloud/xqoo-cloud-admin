package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.entity.SysConsoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理控制台菜单表(SysConsoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-06 11:50:06
 */
@Mapper
@Repository
public interface SysConsoleMenuMapper extends BaseMapper<SysConsoleMenuEntity> {

}