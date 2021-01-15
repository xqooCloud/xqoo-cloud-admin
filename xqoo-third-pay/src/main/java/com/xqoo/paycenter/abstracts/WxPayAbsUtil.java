package com.xqoo.paycenter.abstracts;

import com.xqoo.paycenter.annotations.MaxLength;
import com.xqoo.paycenter.annotations.Required;
import com.xqoo.paycenter.annotations.SignField;
import com.xqoo.paycenter.dto.WeixinPaySignablePdu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WxPayAbsUtil {
    private static final String WX_DATE_FORMAT = "yyyyMMddHHmmss";
    private static Logger logger = LoggerFactory.getLogger(WxPayAbsUtil.class);
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static byte[] hexStringToBytes(String hexStr) {
        if (hexStr == null || "".equals(hexStr)) {
            return null;
        }
        int byteCount = hexStr.length() / 2;
        byte[] arrayOfByte = new byte[byteCount];
        for (int i = 0; i < byteCount; i++) {
            arrayOfByte[i] = (byte) Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
        }
        return arrayOfByte;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] md5(byte[] input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toWeixinDateFormat(Date date) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(WX_DATE_FORMAT);
        return dateTimeFormatter.format(date);
    }

    public static Date fromWeixinDateFormat(String date) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(WX_DATE_FORMAT);
        try {
            return dateTimeFormatter.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Wrong input " + date + ", expected " + WX_DATE_FORMAT, e);
        }
    }

    public static void validateField(Object valueObject, Field field) {
        Object value = getFieldValue(valueObject, field);

        // Check whether required field is missing value or with empty
        // String
        if (field.isAnnotationPresent(Required.class)) {
            if (value == null) {
                throw new IllegalArgumentException("Field [" + field.getName() + "] is required.");
            }

            if (field.getType().equals(String.class)) {
                String strValue = (String) value;
                if (WxPayAbsUtil.isEmpty(strValue)) {
                    throw new IllegalArgumentException("Field [" + field.getName() + "] is required.");
                }
            }
        }

        // Check whether String fields exceeds the max length
        if (field.getType().equals(String.class) && field.isAnnotationPresent(MaxLength.class)) {
            int annotatedMaxLength = field.getAnnotation(MaxLength.class).value();
            String strValue = (String) value;
            if (!WxPayAbsUtil.isEmpty(strValue) && strValue.length() > annotatedMaxLength) {
                throw new IllegalArgumentException("Field [" + field.getName() + "]'s length "
                        + strValue.length() + " exceeds the max length " + annotatedMaxLength);
            }
        }
    }

    /**
     * Validate the value object according to the annotation
     *
     * @param valueObject
     * @throws IllegalArgumentException if required field is missing or String field exceeds the length
     */
    public static void validateValueObject(Object valueObject) {
        Field[] fields = valueObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            validateField(valueObject, field);
        }
    }

    public static Object getFieldValue(Object valueObject, Field field) {
        try {
            field.setAccessible(true);
            return field.get(valueObject);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Should not happen.", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Should not happen.", e);
        }
    }

    /**
     * 根据微信的签名要求生成sign
     *
     * @param valueObject
     * @param key
     * @return
     */
    public static String validateFieldsAndGenerateWxSignature(WeixinPaySignablePdu valueObject,
                                                              String key) {
        if (isEmpty(key)) {
            throw new IllegalStateException("Key is required for computing the signature.");
        }

        // Get all fields annotated with SignField
        Map<String, Object> nameValuesMap = new HashMap<String, Object>();

        Field[] allFields = valueObject.getClass().getDeclaredFields();
        for (Field field : allFields) {
            // Validate whether the field is missing value if required and etc.
            WxPayAbsUtil.validateField(valueObject, field);
            Object value = WxPayAbsUtil.getFieldValue(valueObject, field);
            if (field.isAnnotationPresent(SignField.class)) {
                if (field.getType().equals(Map.class)) {
                    // Annotated SignField Map must be Map<String, Object>
                    @SuppressWarnings("unchecked")
                    Map<String, Object> valueMap = (Map<String, Object>) value;
                    for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
                        nameValuesMap.put(entry.getKey(), entry.getValue());
                    }
                } else {
                    String signName = field.getAnnotation(SignField.class).signName();
                    if (isEmpty(signName)) {
                        nameValuesMap.put(field.getName(), value);
                    } else {
                        nameValuesMap.put(signName, value);
                    }
                }
            }
        }

        List<Map.Entry<String, Object>> listOfKeyValuesToBeSigned = new ArrayList<Map.Entry<String, Object>>(
                nameValuesMap.entrySet());
        // Sort the fields
        Collections.sort(listOfKeyValuesToBeSigned, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareToIgnoreCase(o2.getKey());
            }
        });

        // Generate the signed string
        String sperator = "";
        String signInput = "";
        for (int ii = 0; ii < listOfKeyValuesToBeSigned.size(); ii++) {
            if (ii != 0) {
                sperator = "&";
            }
            Map.Entry<String, Object> keyValue = listOfKeyValuesToBeSigned.get(ii);
            Object value = keyValue.getValue();
            // 跳过null或者空字符串
            if (value == null) {
                continue;
            }
            if (value != null && (value instanceof String)) {
                String strValue = (String) value;
                if (WxPayAbsUtil.isEmpty(strValue)) {
                    continue;
                }
            }

            if (value != null) {
                if("prepay_id".equals(keyValue.getKey())){
                    signInput += sperator + "package=" + keyValue.getKey() + "=" + value.toString();
                }else{
                    signInput += sperator + keyValue.getKey() + "=" + value.toString();
                }
            }
        }

        logger.debug("The input to sign before attaching the key: " + signInput);
        signInput += "&key=" + key;
        logger.debug("The input to sign after attaching the key: " + signInput);
        String md5Digest;
        try {
            md5Digest = WxPayAbsUtil.bytesToHexString(WxPayAbsUtil.md5(signInput.getBytes("UTF-8"))).toUpperCase();
            logger.debug("The weixin sign result: " + md5Digest);
            return md5Digest;
        } catch (UnsupportedEncodingException e) {
            // should not happen
            throw new IllegalStateException("Encoding exception unexpected.", e);
        }
    }

    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(allChar.length());
            sb.append(allChar.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 验证反解析后的对象签名是否正确。
     *
     * @param pdu
     * @param key
     */
    public static boolean validateSign(WeixinPaySignablePdu pdu, String key) {
        logger.debug("Validating pdu " + pdu);
        String recomputedSign = WxPayAbsUtil.validateFieldsAndGenerateWxSignature(pdu, key);
        return pdu.getSign().equals(recomputedSign);
    }

    public static String getSign(Map<String, Object> map,String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        try {
            result =  WxPayAbsUtil.bytesToHexString(WxPayAbsUtil.md5(result.getBytes("UTF-8"))).toUpperCase();

        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return result;
    }


    public static String generateResult(String return_code, String return_msg) {
        String res = "<xml>\n" +
                "  <return_code><![CDATA[" + return_code + "]]></return_code>\n" +
                "  <return_msg><![CDATA[" + return_msg + "]]></return_msg>\n" +
                "</xml>";
        return res;
    }

}
