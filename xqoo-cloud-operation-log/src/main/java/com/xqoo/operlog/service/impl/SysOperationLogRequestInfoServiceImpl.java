package com.xqoo.operlog.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.entity.SysOperationLogRequestInfoEntity;
import com.xqoo.operlog.mapper.SysOperationLogRequestInfoMapper;
import com.xqoo.operlog.service.SysOperationLogRequestInfoService;
import com.xqoo.operlog.vo.ShowOperationDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志请求信息(SysOperationLogRequestInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-11-30 12:06:59
 */
@Service("sysOperationLogRequestInfoService")
public class SysOperationLogRequestInfoServiceImpl extends ServiceImpl<SysOperationLogRequestInfoMapper, SysOperationLogRequestInfoEntity> implements SysOperationLogRequestInfoService {

    @Autowired
    private SysOperationLogRequestInfoMapper sysOperationLogRequestInfoMapper;

    @Override
    public void addRecord(SysOperationLogRequestInfoEntity entity) throws Exception{
        sysOperationLogRequestInfoMapper.insert(entity);
    }

    @Override
    public ResultEntity<ShowOperationDataVO> getDataByLogId(String logId) {
        LambdaQueryWrapper<SysOperationLogRequestInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysOperationLogRequestInfoEntity::getParentId, logId);
        List<SysOperationLogRequestInfoEntity> list = sysOperationLogRequestInfoMapper.selectList(queryWrapper);
        ShowOperationDataVO vo = new ShowOperationDataVO();
        if(CollUtil.isEmpty(list)){
            vo.setHasData(false);
            vo.setContent("[]");
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);
        }
        ArrayNode json = JacksonUtils.createArrayNode();
        list.forEach(item -> {
            try{
                JsonNode content = JacksonUtils.toObj(item.getMessageContent());
                json.add(content);
            }catch (Exception e){
                json.add(JacksonUtils.createEmptyJsonNode());
            }
        });
        String jsonStr = JacksonUtils.toJsonStr(json);
        vo.setHasData(true);
        vo.setContent(JacksonUtils.formatJson(jsonStr));
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }
}
