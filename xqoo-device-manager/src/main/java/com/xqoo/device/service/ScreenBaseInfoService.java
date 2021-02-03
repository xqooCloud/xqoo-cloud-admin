package com.xqoo.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.entity.ScreenBaseInfoEntity;
import com.xqoo.device.pojo.DeviceInfoPageQueryPOJO;
import com.xqoo.device.vo.DeviceDetailInfoVO;
import com.xqoo.device.vo.DeviceInfoVO;
import com.xqoo.device.vo.ScreenConfigPropertiesVO;
import com.xqoo.feign.dto.device.DeviceInfoDetailDTO;

import java.util.List;

/**
 * 数据源表(screen_base_info)表服务接口
 *
 * @author xqoo-code-gen
 * @date 2021-01-27 10:50:14
 */

public interface ScreenBaseInfoService extends IService<ScreenBaseInfoEntity> {

    /**
    * 分页查询screen_base_info
    * @param page
    * @return
    */
    ResultEntity<PageResponseBean<DeviceInfoVO>> pageGetList(DeviceInfoPageQueryPOJO page);

    /**
    * 批量插入screen_base_info
    * @param list
    * @param currentUser
    * @return
    */
    ResultEntity<String> insertList(List<ScreenBaseInfoEntity> list, CurrentUser currentUser);

    /**
     * 更新设备为部署中状态
     * @param deviceId
     * @return
     */
    ResultEntity<String> deviceToDeploy(String deviceId);

    /**
     * 更新设备为部署完成状态
     * @param deviceId
     * @return
     */
    ResultEntity<String> deviceToFinish(String deviceId);

    /**
     * 更新设备为停机状态
     * @param deviceId
     * @return
     */
    ResultEntity<String> deviceToStop(String deviceId);

    /**
     * 更新设备为停机状态
     * @param deviceId
     * @return
     */
    ResultEntity<String> deviceToRemove(String deviceId);

    /**
     * 删除设备记录
     * @param deviceId
     * @return
     */
    ResultEntity<String> deleteDevice(String deviceId);

    /**
     * 获取screen_base_info主键记录了
     * @param id
     * @return
     */
    DeviceDetailInfoVO getOneScreenBaseInfoEntityByPrimaryKey(String id);

    /**
     * 内部根据屏幕id获取相关信息
     * @param id
     * @return
     */
    DeviceInfoDetailDTO getDeviceInfoForPrivate(String id);

    /**
     * 获取屏幕信息配置参数
     * @return
     */
    ResultEntity<ScreenConfigPropertiesVO> getScreenConfigProperties();

    /**
     * 新增屏幕信息参数
     * @param vo
     * @return
     */
    ResultEntity<String> addDeviceBaseInfo(DeviceDetailInfoVO vo, CurrentUser currentUser);

    /**
     * 修改屏幕信息参数
     * @param vo
     * @return
     */
    ResultEntity<String> updateDeviceBaseInfo(DeviceDetailInfoVO vo, CurrentUser currentUser);
}
