package com.xqoo.paycenter.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.xqoo.paycenter.annotations.MaxLength;
import com.xqoo.paycenter.annotations.Required;
import com.xqoo.paycenter.annotations.SignField;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JacksonXmlRootElement(localName = "xml")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect
public class UnifiedOrderResponse extends WeixinPaySignablePdu {
    /**
     *
     */
    private static final long serialVersionUID = -6045775612152015920L;
    // 返回状态码
    @MaxLength(16)
    @Required
    @SignField
    private String return_code;
    // 返回信息
    @MaxLength(128)
    @SignField
    private String return_msg;
    // 公众账号ID
    @MaxLength(32)
    @Required
    @SignField
    private String appid;
    // 商户号
    @MaxLength(32)
    @Required
    @SignField
    private String mch_id;
    // 设备号
    @MaxLength(32)
    @SignField
    private String device_info;
    // 随机字符串
    @MaxLength(32)
    @Required
    @SignField
    private String nonce_str;
    // 业务结果
    @MaxLength(16)
    @Required
    @SignField
    private String result_code;
    // 错误代码
    @MaxLength(32)
    @SignField
    private String err_code;
    // 错误代码描述
    @MaxLength(128)
    @SignField
    private String err_code_des;
    // 交易类型
    @MaxLength(16)
    @Required
    @SignField
    private String trade_type;
    // 预支付交易会话标识
    @MaxLength(64)
    @Required
    @SignField
    private String prepay_id;
    // 二维码链接
    @MaxLength(64)
    @SignField
    private String code_url;

    public String getReturn_code() {
        return return_code;
    }

    public UnifiedOrderResponse setReturn_code(String return_code) {
        this.return_code = return_code;
        return this;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public UnifiedOrderResponse setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public UnifiedOrderResponse setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public UnifiedOrderResponse setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getDevice_info() {
        return device_info;
    }

    public UnifiedOrderResponse setDevice_info(String device_info) {
        this.device_info = device_info;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public UnifiedOrderResponse setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getResult_code() {
        return result_code;
    }

    public UnifiedOrderResponse setResult_code(String result_code) {
        this.result_code = result_code;
        return this;
    }

    public String getErr_code() {
        return err_code;
    }

    public UnifiedOrderResponse setErr_code(String err_code) {
        this.err_code = err_code;
        return this;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public UnifiedOrderResponse setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
        return this;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public UnifiedOrderResponse setTrade_type(String trade_type) {
        this.trade_type = trade_type;
        return this;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public UnifiedOrderResponse setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
        return this;
    }

    public String getCode_url() {
        return code_url;
    }

    public UnifiedOrderResponse setCode_url(String code_url) {
        this.code_url = code_url;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("return_code", return_code)
                .append("return_msg", return_msg)
                .append("appid", appid)
                .append("mch_id", mch_id)
                .append("device_info", device_info)
                .append("nonce_str", nonce_str)
                .append("result_code", result_code)
                .append("err_code", err_code)
                .append("err_code_des", err_code_des)
                .append("trade_type", trade_type)
                .append("prepay_id", prepay_id)
                .append("code_url", code_url)
                .toString();
    }
}
