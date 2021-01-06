package com.xqoo.authorization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.authorization.mapper.SysApiMapper;
import com.xqoo.authorization.entity.SysApiEntity;
import com.xqoo.authorization.service.SysApiService;
import org.springframework.stereotype.Service;

/**
 * 用户接口资源(SysApi)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Service("sysApiService")
public class SysApiServiceImpl extends ServiceImpl<SysApiMapper, SysApiEntity> implements SysApiService {

}