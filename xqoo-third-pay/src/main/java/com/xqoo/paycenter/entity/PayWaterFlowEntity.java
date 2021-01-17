package com.xqoo.paycenter.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/

@ApiModel("支付流水表")
@TableName(value = "pay_water_flow")
public class PayWaterFlowEntity extends Model<PayWaterFlowEntity> {

    @ApiModelProperty("系统申请付款预付单流水号")
    @TableId(value = "pay_transaction_id")
    private String payTransactionId;

    @ApiModelProperty("所用支付平台，WxPay-微信支付，AliPay-支付宝支付，IOSApp-苹果内购，UnionPay-银联支付")
    private String payPlat;

    @ApiModelProperty("支付状态，0-已下单未支付，1-已支付")
    private Integer payStatus;

    @ApiModelProperty("交易平台的支付流水号")
    private String transactionId;

    @ApiModelProperty("交易平台客户的唯一标识")
    private String clientPayId;

    @ApiModelProperty("是否有退款，0-未退款，1-退过款")
    private Integer refundStatus;

    @ApiModelProperty("支付设备，PC-电脑浏览器，APP-手机APP，MOBILE-移动端网页，JSAPI-公众号，SMP-小程序，POS-POS机扫码")
    private String payDevice;

    @ApiModelProperty("所用支付平台，WxPay-微信支付，AliPay-支付宝支付，IOSApp-苹果内购，UnionPay-银联支付")
    private String payPlatName;

    @ApiModelProperty("支付设备，PC-电脑浏览器，APP-手机APP，MOBILE-移动端网页，JSAPI-公众号，SMP-小程序，POS-POS机扫码")
    private String payDeviceName;

    @ApiModelProperty("付款金额")
    private BigDecimal payAmount;

    @ApiModelProperty("已退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("付款人的系统id")
    private String payUserId;

    @ApiModelProperty("付款人的系统名称")
    private String payUserName;

    @ApiModelProperty("付款备注")
    private String payComment;

    @ApiModelProperty("付款时间")
    private Date payTime;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改人")
    private String updateBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改时间")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("payTransactionId", payTransactionId)
                .append("payPlat", payPlat)
                .append("payStatus", payStatus)
                .append("transactionId", transactionId)
                .append("clientPayId", clientPayId)
                .append("refundStatus", refundStatus)
                .append("payDevice", payDevice)
                .append("payPlatName", payPlatName)
                .append("payDeviceName", payDeviceName)
                .append("payAmount", payAmount)
                .append("refundAmount", refundAmount)
                .append("payUserId", payUserId)
                .append("payUserName", payUserName)
                .append("payComment", payComment)
                .append("payTime", payTime)
                .append("createBy", createBy)
                .append("createDate", createDate)
                .append("updateBy", updateBy)
                .append("updateDate", updateDate)
                .append("remarkTips", remarkTips)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PayWaterFlowEntity that = (PayWaterFlowEntity) o;

        return new EqualsBuilder().append(payTransactionId, that.payTransactionId).append(payPlat, that.payPlat).append(payStatus, that.payStatus).append(transactionId, that.transactionId).append(clientPayId, that.clientPayId).append(refundStatus, that.refundStatus).append(payDevice, that.payDevice).append(payPlatName, that.payPlatName).append(payDeviceName, that.payDeviceName).append(payAmount, that.payAmount).append(refundAmount, that.refundAmount).append(payUserId, that.payUserId).append(payUserName, that.payUserName).append(payComment, that.payComment).append(payTime, that.payTime).append(createBy, that.createBy).append(createDate, that.createDate).append(updateBy, that.updateBy).append(updateDate, that.updateDate).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(payTransactionId).append(payPlat).append(payStatus).append(transactionId).append(clientPayId).append(refundStatus).append(payDevice).append(payPlatName).append(payDeviceName).append(payAmount).append(refundAmount).append(payUserId).append(payUserName).append(payComment).append(payTime).append(createBy).append(createDate).append(updateBy).append(updateDate).append(remarkTips).toHashCode();
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getPayPlat() {
        return payPlat;
    }

    public void setPayPlat(String payPlat) {
        this.payPlat = payPlat;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getClientPayId() {
        return clientPayId;
    }

    public void setClientPayId(String clientPayId) {
        this.clientPayId = clientPayId;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getPayDevice() {
        return payDevice;
    }

    public void setPayDevice(String payDevice) {
        this.payDevice = payDevice;
    }

    public String getPayPlatName() {
        return payPlatName;
    }

    public void setPayPlatName(String payPlatName) {
        this.payPlatName = payPlatName;
    }

    public String getPayDeviceName() {
        return payDeviceName;
    }

    public void setPayDeviceName(String payDeviceName) {
        this.payDeviceName = payDeviceName;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    public String getPayComment() {
        return payComment;
    }

    public void setPayComment(String payComment) {
        this.payComment = payComment;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.payTransactionId;
    }

}
