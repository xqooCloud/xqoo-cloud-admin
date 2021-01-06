package com.xqoo.feign.common.bo;

import com.xqoo.feign.entity.SysOperationLogEntity;
import com.xqoo.feign.entity.SysOperationLogRequestInfoEntity;
import com.xqoo.feign.entity.SysOperationLogResponseInfoEntity;

import java.util.Objects;

// 操作日志新增传输实体
public class OperationLogRecordBO {

    private SysOperationLogEntity sysOperationLogEntity;

    private SysOperationLogRequestInfoEntity sysOperationLogRequestInfoEntity;

    private SysOperationLogResponseInfoEntity sysOperationLogResponseInfoEntity;

    @Override
    public String toString() {
        return "OperationLogRecordBO{" +
                "sysOperationLogEntity=" + sysOperationLogEntity +
                ", sysOperationLogRequestInfoEntity=" + sysOperationLogRequestInfoEntity +
                ", sysOperationLogResponseInfoEntity=" + sysOperationLogResponseInfoEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationLogRecordBO that = (OperationLogRecordBO) o;
        return Objects.equals(sysOperationLogEntity, that.sysOperationLogEntity) &&
                Objects.equals(sysOperationLogRequestInfoEntity, that.sysOperationLogRequestInfoEntity) &&
                Objects.equals(sysOperationLogResponseInfoEntity, that.sysOperationLogResponseInfoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysOperationLogEntity, sysOperationLogRequestInfoEntity, sysOperationLogResponseInfoEntity);
    }

    public SysOperationLogEntity getSysOperationLogEntity() {
        return sysOperationLogEntity;
    }

    public void setSysOperationLogEntity(SysOperationLogEntity sysOperationLogEntity) {
        this.sysOperationLogEntity = sysOperationLogEntity;
    }

    public SysOperationLogRequestInfoEntity getSysOperationLogRequestInfoEntity() {
        return sysOperationLogRequestInfoEntity;
    }

    public void setSysOperationLogRequestInfoEntity(SysOperationLogRequestInfoEntity sysOperationLogRequestInfoEntity) {
        this.sysOperationLogRequestInfoEntity = sysOperationLogRequestInfoEntity;
    }

    public SysOperationLogResponseInfoEntity getSysOperationLogResponseInfoEntity() {
        return sysOperationLogResponseInfoEntity;
    }

    public void setSysOperationLogResponseInfoEntity(SysOperationLogResponseInfoEntity sysOperationLogResponseInfoEntity) {
        this.sysOperationLogResponseInfoEntity = sysOperationLogResponseInfoEntity;
    }
}
