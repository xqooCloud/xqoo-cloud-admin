package com.xqoo.paycenter.utils;

import cn.hutool.core.date.DateUtil;
import com.xqoo.common.core.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

public class WxPayUtil {

    public static String getCurrentTimeMillis(int m){
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);
        return timestamp;
    }

    /**
     * 转换BigDecimal防止异常
     * @param value
     * @return
     */
    public static BigDecimal valueToNum(String value){
        BigDecimal num;
        try {
            num = new BigDecimal(value);
            return num;
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }

    /**
     * 生成随机数
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * MD5加密
     * 微信支付签名加密
     */
    public static String createSign(String characterEncoding, SortedMap<String,Object> parameters, String ApiKey){
        StringBuffer sb = new StringBuffer();
        Set<?> es = parameters.entrySet();
        Iterator<?> it = es.iterator();
        while(it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + ApiKey);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * XML解析成MAP
     * 微信支付使用
     */
    public static Map<String,Object> doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if(StringUtils.isEmpty(strxml)) {
            return Collections.emptyMap();
        }

        Map<String,Object> m = new HashMap<String,Object>();
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List<?> list = root.getChildren();
        Iterator<?> it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List<?> children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            m.put(k, v);
        }
        //关闭流
        in.close();

        return m;
    }

    public static Map<String,Object> doXMLParse(HttpServletRequest request) throws JDOMException, IOException {
        Map<String,Object> m = new HashMap<String,Object>();
        InputStream in = request.getInputStream();
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List<?> list = root.getChildren();
        Iterator<?> it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List<?> children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            m.put(k, v);
        }
        //关闭流
        in.close();

        return m;
    }

    public static String getChildrenText(List<?> children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator<?> it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<?> list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static String timeStart() {
        return DateUtil.format(new Date(), "yyyyMMddHHmmss");
    }


    public static String timeExpire() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 60);
        return DateUtil.format(now.getTime(), "yyyyMMddHHmmss");
    }
}
