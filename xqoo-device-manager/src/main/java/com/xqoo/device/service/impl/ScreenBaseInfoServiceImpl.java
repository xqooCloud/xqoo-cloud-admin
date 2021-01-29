package com.xqoo.device.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.MD5Util;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.config.DeviceConfigProperties;
import com.xqoo.device.entity.ScreenBaseInfoEntity;
import com.xqoo.device.entity.ScreenPictureViewEntity;
import com.xqoo.device.entity.ScreenPropertiesEntity;
import com.xqoo.device.enums.ScreenStatusEnum;
import com.xqoo.device.mapper.ScreenBaseInfoMapper;
import com.xqoo.device.pojo.DeviceInfoPageQueryPOJO;
import com.xqoo.device.service.ScreenBaseInfoService;
import com.xqoo.device.service.ScreenPictureViewService;
import com.xqoo.device.service.ScreenPropertiesService;
import com.xqoo.device.vo.DeviceDetailInfoVO;
import com.xqoo.device.vo.DeviceInfoVO;
import com.xqoo.device.vo.ScreenConfigPropertiesVO;
import com.xqoo.device.vo.ScreenPictureDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据源表(screen_base_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-27 10:50:14
 */
@Service("screenBaseInfoService")
public class ScreenBaseInfoServiceImpl extends ServiceImpl<ScreenBaseInfoMapper, ScreenBaseInfoEntity> implements ScreenBaseInfoService {

    private final static Logger logger = LoggerFactory.getLogger(ScreenBaseInfoServiceImpl.class);


    @Autowired
    private ScreenBaseInfoMapper screenBaseInfoMapper;

    @Autowired
    private ScreenPropertiesService screenPropertiesService;

    @Autowired
    private ScreenPictureViewService screenPictureViewService;

    @Autowired
    private DeviceConfigProperties deviceConfigProperties;

    @Override
    public ResultEntity<PageResponseBean<DeviceInfoVO>> pageGetList(DeviceInfoPageQueryPOJO page){
        LambdaQueryWrapper<ScreenBaseInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        if(page.getScreenStatus() != null){
            queryWrapper.eq(ScreenBaseInfoEntity::getScreenStatus, page.getScreenStatus());
        }
        if(page.getScreenSize() != null){
            queryWrapper.eq(ScreenBaseInfoEntity::getScreenSize, page.getScreenSize());
        }
        if(StringUtils.isNotEmpty(page.getScreenName())){
            queryWrapper.likeRight(ScreenBaseInfoEntity::getScreenName, page.getScreenName());
        }
        Integer count = screenBaseInfoMapper.selectCount(queryWrapper);
        PageResponseBean<DeviceInfoVO> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<ScreenBaseInfoEntity> list = screenBaseInfoMapper.selectList(queryWrapper);
        List<DeviceInfoVO> voList = list.stream().map(item -> {
            DeviceInfoVO vo = new DeviceInfoVO();
            BeanUtils.copyProperties(item, vo);
            List<String> labelList = Splitter.on("|").trimResults().splitToList(item.getScreenLabel());
            if(CollUtil.isNotEmpty(labelList)){
                vo.setScreenLabel(labelList);
            }
            return vo;
        }).collect(Collectors.toList());
        result.setContent(voList);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<ScreenBaseInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            screenBaseInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.device]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public ResultEntity<String> deviceToDeploy(String deviceId) {
        ScreenBaseInfoEntity entity = screenBaseInfoMapper.selectOne(new LambdaQueryWrapper<ScreenBaseInfoEntity>()
                .eq(ScreenBaseInfoEntity::getId, deviceId)
                .eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL)
                .and(wrapper ->
                        wrapper.eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.PREPARE.getKey())
                                .or()
                                .eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.STOP.getKey()))
                );
        if(entity == null || StringUtils.isEmpty(entity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到符合状态的设备信息");
        }
        entity.setScreenStatus(ScreenStatusEnum.DEPLOY.getKey());
        entity.setScreenTips("正在安装部署设备");
        try {
            screenBaseInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "变更状态成功");
        }catch (Exception e){
            logger.error("[设备模块]屏幕状态变更为【部署中】发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更改为【部署中】发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> deviceToFinish(String deviceId) {
        ScreenBaseInfoEntity entity = screenBaseInfoMapper.selectOne(new LambdaQueryWrapper<ScreenBaseInfoEntity>()
                .eq(ScreenBaseInfoEntity::getId, deviceId)
                .eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL)
                .and(wrapper ->
                        wrapper.eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.DEPLOY.getKey())
                                .or()
                                .eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.PREPARE.getKey())
                                .or()
                                .eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.STOP.getKey())
                )
        );
        if(entity == null || StringUtils.isEmpty(entity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到符合状态的设备信息");
        }
        entity.setScreenStatus(ScreenStatusEnum.FINISH.getKey());
        entity.setScreenTips("设备部署完成");
        try{
            screenBaseInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "设备部署完成");
        }catch (Exception e){
            logger.error("[设备模块]屏幕状态变更为【部署完成】发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更改为【部署完成】发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> deviceToStop(String deviceId) {
        ScreenBaseInfoEntity entity = screenBaseInfoMapper.selectOne(new LambdaQueryWrapper<ScreenBaseInfoEntity>()
                .eq(ScreenBaseInfoEntity::getId, deviceId)
                .eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL)
                .eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.FINISH.getKey())
        );
        if(entity == null || StringUtils.isEmpty(entity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "没有找到对应正在运行中的设备");
        }
        entity.setScreenStatus(ScreenStatusEnum.STOP.getKey());
        entity.setScreenTips("设备已停止");
        try{
            screenBaseInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "设备停止完成");
        }catch (Exception e){
            logger.error("[设备模块]屏幕状态变更为【故障停机】发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更改为【故障停机】发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> deviceToRemove(String deviceId) {
        ScreenBaseInfoEntity entity = screenBaseInfoMapper.selectOne(new LambdaQueryWrapper<ScreenBaseInfoEntity>()
                .eq(ScreenBaseInfoEntity::getId, deviceId)
                .eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL)
                .and(wrapper ->
                        wrapper.eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.PREPARE.getKey())
                                .or()
                                .eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.STOP.getKey())
                )
        );
        if(entity == null || StringUtils.isEmpty(entity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "设备不为停止状态或未启动状态，不能直接拆除");
        }
        entity.setScreenStatus(ScreenStatusEnum.REMOVE.getKey());
        entity.setScreenTips("设备已经拆除使用");
        try{
            screenBaseInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "设备已拆除完成");
        }catch (Exception e){
            logger.error("[设备模块]屏幕状态变更为【移除使用】发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更改为【移除使用】发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> deleteDevice(String deviceId) {
        ScreenBaseInfoEntity entity = screenBaseInfoMapper.selectOne(new LambdaQueryWrapper<ScreenBaseInfoEntity>()
                .eq(ScreenBaseInfoEntity::getId, deviceId)
                .eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL)
                .eq(ScreenBaseInfoEntity::getScreenStatus, ScreenStatusEnum.REMOVE.getKey())
        );
        if(entity == null || StringUtils.isEmpty(entity.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "记录状态不符，不能删除记录");
        }
        entity.setDelFlag(SqlQueryConstant.LOGIC_DEL);
        try{
            screenBaseInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "记录已产出");
        }catch (Exception e){
            logger.error("[设备模块]删除屏幕{}发生错误，错误原因：{}，错误信息：{}", deviceId, e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "删除设备发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<ScreenConfigPropertiesVO> getScreenConfigProperties() {
        ScreenConfigPropertiesVO vo = new ScreenConfigPropertiesVO();
        BeanUtils.copyProperties(deviceConfigProperties, vo);
        List<Map<String, String>> labelList = deviceConfigProperties.getDefaultLabel().stream().map(item -> {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("key", item);
            map.put("value", item);
            return map;
        }).collect(Collectors.toList());
        vo.setLabelList(labelList);
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    @Override
    public DeviceDetailInfoVO getOneScreenBaseInfoEntityByPrimaryKey(String id){
        LambdaQueryWrapper<ScreenBaseInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ScreenBaseInfoEntity::getId, id);
        queryWrapper.eq(ScreenBaseInfoEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        ScreenBaseInfoEntity entity = screenBaseInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            DeviceDetailInfoVO vo = new DeviceDetailInfoVO();
            BeanUtils.copyProperties(entity, vo);
            List<String> labelList = Splitter.on("|").trimResults().splitToList(entity.getScreenLabel());
            if(CollUtil.isNotEmpty(labelList)){
                vo.setScreenLabel(labelList);
            }
            List<ScreenPropertiesEntity> propertiesList = screenPropertiesService.getListByParentId(vo.getId());
            List<ScreenPictureDetailVO> pictureList = screenPictureViewService.getListByParentId(vo.getId());
            vo.setPropertiesList(propertiesList);
            vo.setPictureList(pictureList);
            return vo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity<String> addDeviceBaseInfo(DeviceDetailInfoVO vo, CurrentUser currentUser) {
        ScreenBaseInfoEntity entity = new ScreenBaseInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        entity.setScreenStatus(ScreenStatusEnum.PREPARE.getKey());

         // 处理参数项新增
        String judgePropertiesSize = judgePropertiesOk(vo.getPropertiesList() == null ? 0 : vo.getPropertiesList().size());
        if(StringUtils.isNotEmpty(judgePropertiesSize)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, judgePropertiesSize);
        }
        String id = MD5Util.MD5Encode(System.currentTimeMillis() + vo.getScreenInstallerPhone(), StandardCharsets.UTF_8.name());
        List<ScreenPropertiesEntity> propertiesEntityList = vo.getPropertiesList().stream().peek(item -> item.setParentId(id)).collect(Collectors.toList());

        // 处理新增的图片项目
        String judgePictureSize = judgePicturesOk(vo.getPictureList() == null ? 0 : vo.getPictureList().size());
        if(StringUtils.isNotEmpty(judgePictureSize)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, judgePictureSize);
        }
        List<ScreenPictureViewEntity> pictureList = vo.getPictureList().stream().map(item -> {
            ScreenPictureViewEntity picEntity = new ScreenPictureDetailVO();
            BeanUtils.copyProperties(item, picEntity);
            picEntity.setParentId(id);
            return picEntity;
        }).collect(Collectors.toList());
        try {
            entity.setId(id);
            entity.setScreenLabel(Joiner.on("|").skipNulls().join(vo.getScreenLabel()));
            ResultEntity<String> insertPropertiesResult = screenPropertiesService.insertList(propertiesEntityList, currentUser);
            ResultEntity<String> insertPictureResult = screenPictureViewService.insertList(pictureList, currentUser);
            if(!insertPropertiesResult.getCode().equals(HttpStatus.OK.value())){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "参数信息保存失败，请稍后重试");
            }
            if(!insertPictureResult.getCode().equals(HttpStatus.OK.value())){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "图片信息保存失败，请稍后重试");
            }
            screenBaseInfoMapper.insert(entity);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[设备中心]新增屏幕设备发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增数据时发生错误，请稍后重试");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity<String> updateDeviceBaseInfo(DeviceDetailInfoVO vo, CurrentUser currentUser) {
        if(vo.getId() == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "编辑失败，丢失资源id");
        }
        // 处理参数项
        String judgePropertiesSize = judgePropertiesOk(vo.getPropertiesList() == null ? 0 : vo.getPropertiesList().size());
        if(StringUtils.isNotEmpty(judgePropertiesSize)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, judgePropertiesSize);
        }
        // 获取原有参数的所有id集合
        List<Long> hasPropIds = screenPropertiesService.getIdsByParentId(vo.getId());
        List<ScreenPropertiesEntity> propertiesEntityList = vo.getPropertiesList().stream()
                .peek(item -> item.setParentId(vo.getId())).collect(Collectors.toList());
        List<ScreenPropertiesEntity> addPropertiesList = propertiesEntityList.stream()
                .filter(item -> item.getId() == null || item.getId() == 0).collect(Collectors.toList());
        List<ScreenPropertiesEntity> updatePropertiesList = propertiesEntityList.stream()
                .filter(item -> item.getId() != null && item.getId() > 0).collect(Collectors.toList());
        List<Long> delPropList = new ArrayList<>();
         hasPropIds.forEach(item -> {
            Optional<ScreenPropertiesEntity> find = propertiesEntityList.stream().filter(prop -> prop.getId() != null && prop.getId().equals(item)).findFirst();
            if(find.isPresent()){
                return;
            }
             delPropList.add(item);
        });

        // 处理新增的图片项目
        String judgePictureSize = judgePicturesOk(vo.getPictureList() == null ? 0 : vo.getPictureList().size());
        if(StringUtils.isNotEmpty(judgePictureSize)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, judgePictureSize);
        }
        // 获取原有图片的所有id集合
        List<Long> hasPicIds = screenPictureViewService.getIdsByParentId(vo.getId());
        List<ScreenPictureViewEntity> picEntityList = vo.getPictureList().stream()
                .map(item -> {
                    ScreenPictureViewEntity picEntity = new ScreenPictureDetailVO();
                    BeanUtils.copyProperties(item, picEntity);
                    picEntity.setParentId(vo.getId());
                    return picEntity;
                }).collect(Collectors.toList());
        List<ScreenPictureViewEntity> addPictureList = picEntityList.stream()
                .filter(item -> item.getId() == null || item.getId() == 0).collect(Collectors.toList());
        List<ScreenPictureViewEntity> updatePictureList = picEntityList.stream()
                .filter(item -> item.getId() != null && item.getId() > 0).collect(Collectors.toList());
        List<Long> delPicList = new ArrayList<>();
        hasPicIds.forEach(item -> {
            Optional<ScreenPictureViewEntity> find = picEntityList.stream().filter(prop -> prop.getId() != null && prop.getId().equals(item)).findFirst();
            if(find.isPresent()){
                return;
            }
            delPicList.add(item);
        });

        ScreenBaseInfoEntity entity = new ScreenBaseInfoEntity();
        BeanUtils.copyProperties(vo, entity);
        try {
            entity.setScreenLabel(Joiner.on("|").skipNulls().join(vo.getScreenLabel()));
            ResultEntity<String> insertResult = screenPropertiesService.insertList(addPropertiesList, currentUser);
            ResultEntity<String> insertPicResult = screenPictureViewService.insertList(addPictureList, currentUser);
            if(!insertResult.getCode().equals(HttpStatus.OK.value())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改参数保存错误，请稍后重试");
            }
            if(!insertPicResult.getCode().equals(HttpStatus.OK.value())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改图片保存错误，请稍后重试");
            }
            screenPropertiesService.removeList(delPropList);
            screenPropertiesService.updateList(updatePropertiesList);
            screenPictureViewService.removeList(delPicList);
            screenPictureViewService.updateList(updatePictureList);
            screenBaseInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "修改完成");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[设备中心]修改屏幕设备发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "修改数据时发生错误，请稍后重试");
        }
    }

    /**
     * 处理参数条目是否满足条件
     * @param listSize
     * @return
     */
    protected String judgePropertiesOk(Integer listSize){
        Integer minProperties = deviceConfigProperties.getMinPropertiesCount();
        Integer maxProperties = deviceConfigProperties.getMaxPropertiesCount();
        if(minProperties != null && minProperties.compareTo(0) > 0){
            if(listSize == null
                    || listSize.compareTo(minProperties) < 0){
                return "屏幕的参数条目最少【" + minProperties + "】条";
            }
        }
        if(maxProperties != null && maxProperties.compareTo(0) > 0){
            if(listSize == null
                    || listSize.compareTo(maxProperties) > 0){
                return "屏幕的参数条目最多【" + maxProperties + "】条";
            }
        }
        return null;
    }

    /**
     * 处理图片条目是否满足条件
     * @param listSize
     * @return
     */
    protected String judgePicturesOk(Integer listSize){
        Integer maxPictureCount = deviceConfigProperties.getMaxImageCount();
        if(listSize == null || listSize < 1){
            return "图片至少上传1张";
        }
        if(maxPictureCount != null && maxPictureCount.compareTo(0) > 0){
            if(listSize.compareTo(maxPictureCount) > 0){
                return "图片上传数量最多【" + maxPictureCount + "】张";
            }
        }
        return null;
    }
}
