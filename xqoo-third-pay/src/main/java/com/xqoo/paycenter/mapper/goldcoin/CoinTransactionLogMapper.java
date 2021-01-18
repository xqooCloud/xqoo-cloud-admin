package com.xqoo.paycenter.mapper.goldcoin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.goldcoin.CoinTransactionLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 金币交易流水记录表(CoinTransactionLog)表数据库访问层
 *
 * @author liangyongpeng
 * @since 2020-05-07 14:07:38
 */
@Mapper
@Repository
public interface CoinTransactionLogMapper extends BaseMapper<CoinTransactionLogEntity> {

}
