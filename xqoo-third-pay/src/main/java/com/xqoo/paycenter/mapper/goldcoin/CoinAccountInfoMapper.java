package com.xqoo.paycenter.mapper.goldcoin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.goldcoin.CoinAccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 金币用户表(CoinAccountInfo)表数据库访问层
 *
 * @author liangyongpeng
 * @since 2020-05-07 11:56:02
 */
@Mapper
@Repository
public interface CoinAccountInfoMapper extends BaseMapper<CoinAccountInfoEntity> {

}
