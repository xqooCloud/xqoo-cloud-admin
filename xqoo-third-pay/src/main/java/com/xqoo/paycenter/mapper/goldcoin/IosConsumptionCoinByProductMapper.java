package com.xqoo.paycenter.mapper.goldcoin;

import com.xqoo.paycenter.entity.goldcoin.CoinAccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: IosConsumptionCoinByProductMapper
 * @Description: IOS金币购买产品Mapper层
 * @Author: liangyongpeng
 * @Date: 2020/5/7 11:12
 * @Verison 1.0
 **/
@Mapper
@Repository
public interface IosConsumptionCoinByProductMapper {

    /**
     * 根据id获取金币用户实体类信息
     *
     * @param userId 用户id
     * @return
     */
    CoinAccountInfoEntity getCoinAccountInfoEntityInfo(@Param(value = "userId") String userId);

}
