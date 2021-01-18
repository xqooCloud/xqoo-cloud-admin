package com.xqoo.paycenter.dto;

import com.xqoo.paycenter.interfaces.WeixinPayPdu;

public class WeixinPaySignablePdu implements WeixinPayPdu {

    /**
     *
     */
    private static final long serialVersionUID = -3011613066844770293L;


    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WeixinPaySignablePdu [sign=" + sign + "]";
    }
}
