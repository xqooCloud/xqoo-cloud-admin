package com.xqoo.salecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.salecenter.entity.SaleGoodsInfoEntity;
import com.xqoo.salecenter.pojo.SaleGoodsInfoQuery;

import java.util.List;

/**
 * 数据源表(sale_goods_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:08:34
 */

public interface SaleGoodsInfoService extends IService<SaleGoodsInfoEntity> {

    /**
    * 分页查询sale_goods_info
    * @param query
    * @return
    */
    ResultEntity<PageResponseBean<SaleGoodsInfoEntity>> pageGetList(SaleGoodsInfoQuery query);

    /**
    * 批量插入sale_goods_info
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<SaleGoodsInfoEntity> list, CurrentUser currentUser);

    /**
     * 提交商品审核
     * @param goodsId
     * @return
     */
    ResultEntity<String> sendAudit(String goodsId);

    /**
     * 审核通过
     * @param goodsId
     * @return
     */
    ResultEntity<String> auditPass(String goodsId);

    /**
     * 废弃商品
     * @param goodsId
     * @return
     */
    ResultEntity<String> aboardGoods(String goodsId);

    /**
     * 上架商品
     * @param goodsIds
     * @return
     */
    ResultEntity<String> publishGoods(List<String> goodsIds);

    /**
     * 下架商品
     * @param goodsIds
     * @return
     */
    ResultEntity<String> undercarriageGoods(List<String> goodsIds);

    /**
    * 获取sale_goods_info主键记录了
    * @return
    */
    SaleGoodsInfoEntity getOneSaleGoodsInfoEntityByPrimaryKey(String goodsId);
}
