package com.xqoo.integral.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Map;

/**
 * integral_account实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-26 11:17:12
 */
@ApiModel("积分账户表实体")
@TableName(value = "integral_account")
public class IntegralAccountEntity extends Model<IntegralAccountEntity> {

    private static final long serialVersionUID = -159035556085684885L;

    @ApiModelProperty("账户id，AI+userid")
    @TableId(value = "account_id")
    private String accountId;

    @ApiModelProperty("账户状态，0-正常，1-冻结，2-注销")
    private Integer accountStatus;

    @ApiModelProperty("账户拥有者userId")
    private String accountOwner;

    @ApiModelProperty("账户积分余额")
    private BigDecimal accountAmount;

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId(){
        return this.accountId;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getAccountStatus(){
        return this.accountStatus;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountOwner(){
        return this.accountOwner;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getAccountAmount(){
        return this.accountAmount;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public IntegralAccountEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accountId", getAccountId())
            .append("accountStatus", getAccountStatus())
            .append("accountOwner", getAccountOwner())
            .append("accountAmount", getAccountAmount())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        IntegralAccountEntity that = (IntegralAccountEntity) o;
        return new EqualsBuilder()
                .append(accountId, that.accountId)
                .append(accountStatus, that.accountStatus)
                .append(accountOwner, that.accountOwner)
                .append(accountAmount, that.accountAmount)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(accountId)
                .append(accountStatus)
                .append(accountOwner)
                .append(accountAmount)
                .toHashCode();
    }
}
