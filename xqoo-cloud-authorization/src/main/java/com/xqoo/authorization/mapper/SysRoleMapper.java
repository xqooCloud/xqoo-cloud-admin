package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.bo.QueryRoleBO;
import com.xqoo.authorization.entity.SysRoleEntity;
import com.xqoo.authorization.vo.SysRoleInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    List<SysRoleInfoVO> getBindRoleUser(@Param("bo") QueryRoleBO bo);

    Integer queryUserNoRoleCount(@PathVariable("userId") String userId);

    List<SysRoleEntity> queryUserNoRole(@PathVariable("userId") String userId);

}
