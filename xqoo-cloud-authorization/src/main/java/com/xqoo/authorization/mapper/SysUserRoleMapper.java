package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.bo.SysUserRoleMapBO;
import com.xqoo.authorization.entity.SysUserRoleEntity;
import com.xqoo.authorization.vo.SysUserRoleInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 用户角色表(SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Mapper
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    List<SysUserRoleMapBO> getUserRoleMap(@PathVariable("userId") String userId);

    List<SysUserRoleInfoVO> getUserRoleListInfo(@PathVariable("userId") String userId);

    void insertList(@Param("list") List<SysUserRoleEntity> list);
}
