package com.xqoo.paycenter.dto;

public class FinalUnifiedOrderResponse extends UnifiedOrderResponse{
    private String timestamp;
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
