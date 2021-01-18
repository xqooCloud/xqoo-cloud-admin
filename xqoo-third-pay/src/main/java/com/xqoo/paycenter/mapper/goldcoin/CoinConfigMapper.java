package com.xqoo.paycenter.mapper.goldcoin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqoo.paycenter.entity.goldcoin.CoinConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 内购配置表(CoinConfig)表数据库访问层
 *
 * @author liangyongpeng
 * @since 2020-04-29 15:33:42
 */
@Mapper
@Repository
public interface CoinConfigMapper extends BaseMapper<CoinConfigEntity> {

    /**
     * 根据id查询内购配置信息
     *
     * @param chargeId
     * @return
     */
    List<CoinConfigEntity> getCoinConfigs(@Param(value = "chargeId") String chargeId);

}
