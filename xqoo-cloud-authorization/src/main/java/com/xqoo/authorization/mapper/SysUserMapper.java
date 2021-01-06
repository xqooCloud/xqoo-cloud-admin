package com.xqoo.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.authorization.bo.QueryUserInfoBO;
import com.xqoo.authorization.entity.SysUserEntity;
import com.xqoo.authorization.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-23 19:13:19
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    List<UserInfoVO> pageQueryUserInfo(@Param("bo")QueryUserInfoBO bo);
}
