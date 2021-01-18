package com.xqoo.paycenter.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class WxHttpUtil {

    public final static int PAGE_ITEMS_COUNT = 8;

    public WxHttpUtil() {
    }

    /**
     **获取url连接中的参数
     *并返回String数组合
     **
     */
    public static Map<String,String> getHttpUrlParam(String httpUrlStr) {

        HashMap<String, String> rtnStrArray= new HashMap<String,String>();
        if(httpUrlStr!=null&&!"".equals(httpUrlStr)&&httpUrlStr.contains("?")){
            int beginIndex =httpUrlStr.indexOf("?");
            httpUrlStr=httpUrlStr.substring(beginIndex);
            String[] paramArray=httpUrlStr.split("&");
            for(String paramStr:paramArray){
                String[] paramStrSbu=paramStr.split("=");
                rtnStrArray.put(paramStrSbu[0], paramStrSbu[1]);
            }
        }
        return rtnStrArray;
    }

    public static void debugRequestHeader(HttpServletRequest request) {
        String queryStr = request.getQueryString()==null? "" : ("?" + request.getQueryString());
        String headerStr = request.getMethod() + " " + request.getRequestURL() + queryStr + "\r\n";
        Enumeration headers = request.getHeaderNames();
        while ( headers.hasMoreElements() ) {
            String header = (String) headers.nextElement();
            String value = request.getHeader( header );
            headerStr += header + ": " + value + "\r\n";
        }
        ////Debug.println( headerStr );
    }

    public static void debugRequestParameter(HttpServletRequest request) {
        String queryStr = request.getQueryString()==null? "" : ("?" + request.getQueryString());
        String paramStr = request.getMethod() + " " + request.getRequestURL() + queryStr + "\r\n";
        Enumeration params = request.getParameterNames();
        while ( params.hasMoreElements() ) {
            String param = (String) params.nextElement();
            paramStr += "  " + param + " =";
            String[] values = request.getParameterValues( param );
            try {
                for (int i=0; i<values.length; i++) {
                    paramStr += " " + ascii2gb(values[i]);
                }
            } catch (Exception e) {}
            paramStr += "\r\n";
        }
        //Debug.println( paramStr );
    }

    public static void debugRequestGet(HttpServletRequest request) {
        String queryStr = request.getQueryString()==null? "" : ("?" + request.getQueryString());
        //Debug.println( "GET " + request.getRequestURL() + queryStr );
    }

    public static void debugRequestPost(HttpServletRequest request) {
        String postStr = "POST " + request.getRequestURL() + "\r\n";
        try {
            BufferedReader reader = request.getReader();
            String line = reader.readLine();
            while ( line != null ) {
                postStr += line + "\r\n";
                line = reader.readLine();
            }
        } catch (IOException e) {}
        //Debug.println( postStr );
    }

    public static String ascii2gb(String src) throws UnsupportedEncodingException {
        if (src.length() == 0) return src;
        return new String( src.getBytes("ISO-8859-1"), "GBK" );
    }

    public static String gb2ascii(String src) throws UnsupportedEncodingException {
        return new String( src.getBytes("GBK"), "ISO-8859-1" );
    }

    public static String ascii2utf(String src) throws UnsupportedEncodingException {
        return new String( src.getBytes("ISO-8859-1"), "UTF-8" );
    }

    // 2011-3-4: 将输入参数进行转换：null作为空串；做trim()处理；进行编码转换
    public static String paramASCII(String src) throws UnsupportedEncodingException {
        if (src == null || src.length() == 0) return "";
        return src.trim();
    }
    public static String paramGB(String src) throws UnsupportedEncodingException {
        if (src == null || src.length() == 0) return "";
        return new String( src.trim().getBytes("ISO-8859-1"), "GBK" );
    }
    public static String paramUTF(String src) throws UnsupportedEncodingException {
        if (src == null || src.length() == 0) return "";
        return new String( src.trim().getBytes("ISO-8859-1"), "UTF-8" );
    }

    public static String mergeValues(String[] values) throws UnsupportedEncodingException {
        if (values == null) return "";

        String rtnStr = "";
        for (int i=0; i<values.length; i++) {
            rtnStr += values[i] + ";";
        }
        return rtnStr;
    }

    public static String mergeValuesGB(String[] values) throws UnsupportedEncodingException {
        if (values == null) return "";

        String rtnStr = "";
        for (int i=0; i<values.length; i++) {
            String value = new String(values[i].getBytes("ISO-8859-1"), "GBK");
            rtnStr += value + ";";
        }
        return rtnStr;
    }

    public static String CRLF2BR(String input) {
        if (input == null || input.length() == 0) return "";
        return input.replaceAll("\r\n", "<br>");
    }

    //检查表单输入是否合法，防止XSS攻击和SQL注入攻击

    public static String validateInput(String input) {
        return validateInput(input, true);
    }
    public static String validateInput(String input, boolean replaceAmp) {
        if ( input == null || input.length() == 0 ) return "";

        if (replaceAmp) input = input.replaceAll("&", "&amp;");

        return input.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("'", "&#39;")
                .replaceAll("\"", "&quot;")
                .trim();
    }

    /**
     * 是否是合法输入，非法字符包括：<>()'"&。用于防止XSS攻击
     * @param input
     * @return
     */
    public static boolean isValidInput(String input) {
        return isValidInput(input, false);
    }

    /**
     * 是否是合法输入，URL允许&字符。用于防止XSS攻击
     * @param input
     * @param isUrl input is a URL
     * @return
     */
    public static boolean isValidInput(String input, boolean isUrl) {
        boolean invalid = input.contains("<") ||
                input.contains(">") ||
                input.contains("(") ||
                input.contains(")") ||
                input.contains("'") ||
                input.contains("\"");
        if (!isUrl) invalid = input.contains("&") || invalid;
        return !invalid;
    }

    public static boolean isValidRequest(HttpServletRequest request) {
        Enumeration params = request.getParameterNames();
        while ( params.hasMoreElements() ) {
            String param = (String) params.nextElement();
            String[] values = request.getParameterValues( param );
            for (int i=0; i<values.length; i++) {
                if ( !isValidInput(values[i]) ) return false;
            }
        }
        return true;
    }

    public static java.text.SimpleDateFormat sdf0 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("MM-dd HH:mm");
    public static java.text.SimpleDateFormat sdf3 = new java.text.SimpleDateFormat("yyyy-MM-dd");
    public static java.text.SimpleDateFormat sdf4 = new java.text.SimpleDateFormat("HH:mm:ss");
    public static java.text.SimpleDateFormat sdf5 = new java.text.SimpleDateFormat("yyyy年MM月dd日");
    public static java.text.SimpleDateFormat sdf6 = new java.text.SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
    public static java.text.SimpleDateFormat sdf7 = new java.text.SimpleDateFormat("HH:mm");
    public static java.text.SimpleDateFormat sdf8 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
    public static java.text.SimpleDateFormat sdf9 = new java.text.SimpleDateFormat("MM月dd日 E HH:mm");
    static {
        sdf6.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String dateFormat0(Date date) { return sdf0.format( date ); }
    public static String dateFormat1(Date date) { return sdf1.format( date ); }
    public static String dateFormat2(Date date) { return sdf2.format( date ); }
    public static String dateFormat3(Date date) { return sdf3.format( date ); }
    public static String dateFormat4(Date date) { return sdf4.format( date ); }
    public static String dateFormat5(Date date) { return sdf5.format( date ); }
    public static String dateFormat6(Date date) { return sdf6.format( date ); }
    public static String dateFormat7(Date date) { return sdf7.format( date ); }
    public static String dateFormat8(Date date) { return sdf8.format( date ); }
    public static String dateFormat9(Date date) { return sdf9.format( date ); }

    public static Date dateParse0(String dateStr) throws ParseException { return sdf0.parse(dateStr);}
    public static Date dateParse1(String dateStr) throws ParseException{ return sdf1.parse(dateStr);}
    public static Date dateParse2(String dateStr) throws ParseException{ return sdf2.parse(dateStr);}
    public static Date dateParse3(String dateStr) throws ParseException{ return sdf3.parse(dateStr);}
    public static Date dateParse4(String dateStr) throws ParseException{ return sdf4.parse(dateStr);}
    public static Date dateParse5(String dateStr) throws ParseException{ return sdf5.parse(dateStr);}
    public static Date dateParse6(String dateStr) throws ParseException{ return sdf6.parse(dateStr);}
    public static Date dateParse7(String dateStr) throws ParseException{ return sdf7.parse(dateStr);}

    public static String half2full( String input ) {
        if ( input == null || input.length() == 0 ) return "";

        input = input.replace('&', '＆');
        input = input.replace('\'', ' ');
        input = input.replace('\"', ' ');
        input = input.replace('%', '％');
        input = input.replace('+', '＋');
        input = input.replace('<', '＜');
        input = input.replace('>', '＞');
        input = input.replace('$', '＄');

        return input.trim();
    }

    public static String full2half(String input) throws UnsupportedEncodingException {
        return full2half(input, false);
    }
    public static String full2half(String input, boolean incAlpha) throws UnsupportedEncodingException {
        String str = ascii2gb(input);

        str = str.replace('０', '0');
        str = str.replace('１', '1');
        str = str.replace('２', '2');
        str = str.replace('３', '3');
        str = str.replace('４', '4');
        str = str.replace('５', '5');
        str = str.replace('６', '6');
        str = str.replace('７', '7');
        str = str.replace('８', '8');
        str = str.replace('９', '9');

        if (incAlpha) {
            str = str.replace('ａ', 'a');
            str = str.replace('ｂ', 'b');
            str = str.replace('ｃ', 'c');
            str = str.replace('ｄ', 'd');
            str = str.replace('ｅ', 'e');
            str = str.replace('ｆ', 'f');
            str = str.replace('ｇ', 'g');
            str = str.replace('ｈ', 'h');
            str = str.replace('ｉ', 'i');
            str = str.replace('ｊ', 'j');
            str = str.replace('ｋ', 'k');
            str = str.replace('ｌ', 'l');
            str = str.replace('ｍ', 'm');
            str = str.replace('ｎ', 'n');
            str = str.replace('ｏ', 'o');
            str = str.replace('ｐ', 'p');
            str = str.replace('ｑ', 'q');
            str = str.replace('ｒ', 'r');
            str = str.replace('ｓ', 's');
            str = str.replace('ｔ', 't');
            str = str.replace('ｕ', 'u');
            str = str.replace('ｖ', 'v');
            str = str.replace('ｗ', 'w');
            str = str.replace('ｘ', 'x');
            str = str.replace('ｙ', 'y');
            str = str.replace('ｚ', 'z');

            str = str.replace('Ａ', 'A');
            str = str.replace('Ｂ', 'B');
            str = str.replace('Ｃ', 'C');
            str = str.replace('Ｄ', 'D');
            str = str.replace('Ｅ', 'E');
            str = str.replace('Ｆ', 'F');
            str = str.replace('Ｇ', 'G');
            str = str.replace('Ｈ', 'H');
            str = str.replace('Ｉ', 'I');
            str = str.replace('Ｊ', 'J');
            str = str.replace('Ｋ', 'K');
            str = str.replace('Ｌ', 'L');
            str = str.replace('Ｍ', 'M');
            str = str.replace('Ｎ', 'N');
            str = str.replace('Ｏ', 'O');
            str = str.replace('Ｐ', 'P');
            str = str.replace('Ｑ', 'Q');
            str = str.replace('Ｒ', 'R');
            str = str.replace('Ｓ', 'S');
            str = str.replace('Ｔ', 'T');
            str = str.replace('Ｕ', 'U');
            str = str.replace('Ｖ', 'V');
            str = str.replace('Ｗ', 'W');
            str = str.replace('Ｘ', 'X');
            str = str.replace('Ｙ', 'Y');
            str = str.replace('Ｚ', 'Z');

            str = str.replace('－', '-');
            str = str.replace('＿', '_');
            str = str.replace('＠', '@');
            str = str.replace('．', '.');
            str = str.replace('：', ':');
        }
        return str;
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i=0; i<cookies.length; i++) {
                if (cookies[i].getName().equals( name )) {
                    return java.net.URLDecoder.decode( cookies[i].getValue() );
                }
            }
        }
        return null;
    }

    /**
     * 设置Cookie
     * @param response
     * @param name
     * @param value
     * @param domain
     * @param path
     * @param maxage 0表示删除Cookie，-1表示临时Cookie，正值表示有效秒数
     */
    public static void setCookie(HttpServletResponse response, String name, String value,
                                 String domain, String path, int maxage) {
        Cookie cookie = new Cookie(name, java.net.URLEncoder.encode( value ));
        if ( !domain.equals("") ) cookie.setDomain( domain );
        if ( !path.equals("") ) cookie.setPath( path );
        cookie.setMaxAge( maxage );
        response.addCookie(cookie);
    }

    public static boolean isValidQQ(String qq) {
        if (qq.length() >= 5 && !qq.startsWith("0")) {
            try {
                Long.parseLong( qq );
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean isValidURL(String url) {
        if (url.length() >= 10 && url.indexOf('.')!=-1 && url.substring(0,5).equalsIgnoreCase("http:")) {
            return true;
        }
        return false;
    }

    public static String validateURL(String url) {
        if (url.length() >= 10 && url.indexOf('.')!=-1) {
            if (url.substring(0,5).equalsIgnoreCase("http:")) {
                return url;
            } else {
                return "http://" + url;
            }
        }
        return "";
    }

    public static String hideIP(String ipaddr) {
        if (ipaddr.length() > 0) {
            String ip3bytes = ipaddr.substring(0, ipaddr.lastIndexOf('.'));
            return ip3bytes + ".*";
        } else {
            return ipaddr;
        }
    }

    private final static char[] DIGITS = { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };

    public static String toHexStr(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return new String( out );
    }

    public final static String MD5KEY = "m4k3j5g7s0";

    public static String md5(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update( input.getBytes() );
            return toHexStr( md.digest() );
        } catch (Exception e) {
            //Debug.print(e);
            return null;
        }
    }

    public static boolean UseProxy = false; //是否使用反向代理服务器
    public static String getRemoteAddr(HttpServletRequest request) {
        String ipaddr = request.getRemoteAddr();
        String xForwardedFor = request.getHeader("X-Forwarded-For"); //来自代理服务器
        if ( UseProxy && xForwardedFor != null ) {
            StringTokenizer st = new StringTokenizer(xForwardedFor, ", ");
            while ( st.hasMoreTokens() ) { //如果有多个IP，取最后一个
                ipaddr = st.nextToken();
            }
        }
        return ipaddr;
    }

    public static String getServerName(HttpServletRequest request) {
        String serverName = request.getServerName();
        String xForwardedHost = request.getHeader("X-Forwarded-Host"); //来自代理服务器
        if ( UseProxy && xForwardedHost != null ) {
            serverName = xForwardedHost;
        }
        return serverName;
    }

    /**
     * 设置页面缓存时间（分钟）。用户登录后内容不同的页面不适合做缓存。
     * @param response
     * @param cacheMins
     */
    public static void setCacheTime(HttpServletResponse response, int cacheMins) {
        Date nowDate = new Date();

        nowDate.setTime( nowDate.getTime() - cacheMins*5*1000 ); //创建时间设为5分钟前
        response.setHeader("Last-Modified", WxHttpUtil.dateFormat6(nowDate));

        //nowDate.setTime( nowDate.getTime() + cacheMins*60*1000 );
        //response.setHeader("Expires", HttpUtil.dateFormat6(nowDate));

        response.setHeader("Cache-Control", "max-age=" + cacheMins*60);
    }

    /**
     * 使输入字符串长度不超过maxlen个汉字，用于截取标题或内容摘要。
     * @param input 输入字符串
     * @param maxlen 最大允许的字符串长度
     * @param dotend 超长被截取后结尾是否带...
     * @return
     */
    public static String maxLen(String input, int maxlen, boolean dotend) {
        if (input.length() <= maxlen) return input; //对汉字

        if (input.getBytes().length <= maxlen*2) return input; //对半角字母和数字

        if (dotend) {
            return input.substring(0, maxlen-2) + "...";
        } else {
            return input.substring(0, maxlen);
        }
    }

    /**
     * 把内容中的关键词标记为红色。关键字允许有空格
     * @param content
     * @param keyword
     * @return
     */
    public static String markKeyword(String content, String keyword) {
        if (keyword==null || keyword.equals("")) return content;

        String[] kws = keyword.split("\\s");
        for (int i=0; i<kws.length; i++) {
            content = content.replaceAll(kws[i], "<font color=red>" + kws[i] + "</font>");
        }
        return content;
    }

    /**
     * 填充空行
     * @param output
     * @param rowcount
     * @return
     */
    public static String blankLine(String output, int rowcount){
        String resultOutput = "";
        for (int i=0; i<rowcount; i++) {
            resultOutput += output + "\r\n";
        }
        return resultOutput;
    }

    public static String[][] ZhuanYiTable = {
            {"&", "&amp;"},
            {"°", "&deg;"},
            {" ", "&nbsp;"},
            {" ", "&ensp;"},
            {" ", "&emsp;"},
            {">", "&gt;"},
            {"<", "&lt;"},
            {"\"", "&quot;"},
            {"“", "&ldquo;"},
            {"”", "&rdquo;"},
            {"—", "&mdash;"},
            {"…", "&hellip;"},
            {"←", "&larr;"},
            {"→", "&rarr;"},
            {"↔", "&harr;"},
            {"↑", "&uarr;"},
            {"↓", "&darr;"},
            {"·", "&middot;"},
            {"•", "&bull;"},
            {"©", "&copy;"},
            {"®", "&reg;"},
            {"™", "&trade;"},
            {"×", "&times;"},
            {"÷", "&divide;"},
            {"¥", "&yen;"},
            {"♠", "&spades;"},
            {"♣", "&clubs;"},
            {"♥", "&hearts;"},
            {"♦", "&diams;"},
            {"√", "&radic;"}
    };

    /**
     * 处理HTML转义字符，从名称转为字符
     * @param input
     * @return
     */
    public static String char2Name(String input) {
        String output = input;
        for (int i=0; i<ZhuanYiTable.length; i++) {
            output = output.replaceAll(ZhuanYiTable[i][0], ZhuanYiTable[i][1]);
        }
        return output;
    }

    /**
     * 处理HTML转义字符，从名称转为字符
     * @param input
     * @return
     */
    public static String name2Char(String input) {
        String output = input;
        if(input!=null&&!"".equals(input)){
            if (input.contains("&")){
                for (int i=0; i<ZhuanYiTable.length; i++) {
                    output = output.replaceAll(ZhuanYiTable[i][1], ZhuanYiTable[i][0]);
                }
            }
        }
        return output;
    }

    /**
     * 处理HTML转义字符，从名称转为字符
     * @param input
     * @return
     */
    public static String specialCharHandle(String input) {
        String output = input;
        if(input!=null&&!"".equals(input)){
            for (int i=0; i<ZhuanYiTable.length; i++) {
                output = output.replaceAll(ZhuanYiTable[i][1], ZhuanYiTable[i][0]);
            }
        }
        return output;
    }


    /******************************************************************/
    /****************   通过UserAgent区分设备类型        *******************/
    /******************************************************************/
    public static String[][] DeviceTable = {
            { "iPhone", "iPhone" },
            { "iPad", "iPad" },
            { "iPod", "iPod" },
            { "Android", "Android" },
            { "Windows", "Windows" },
            { "Mac", "Macintosh" },
            { "Linux", "Linux" }
    };

    /**
     * 解析UserAgent获取设备类型
     * @param useragent
     * @return
     */
    public static String makeDeviceType(String useragent) {
        String devicetype = "";
        for (int i=0; i<DeviceTable.length; i++) {
            if (useragent.contains(DeviceTable[i][1])) {
                devicetype = DeviceTable[i][0];
                break;
            }
        }
        return devicetype;
    }

    /**
     * 替换四个字节的字符 '\xF0\x9F\x98\x84'）的解决方案
     * @author ChenGuiYong
     * @data 2015年8月11日 上午10:31:50
     * @param content
     * @return
     */
    public static String removeFourChar(String content) {
        try {
            byte[] conbyte = content.getBytes("UTF-8");
            for (int i = 0; i < conbyte.length; i++) {
                if ((conbyte[i] & 0xF8) == 0xF0 && i+3<conbyte.length) {
                    for (int j = 0; j < 4; j++) {
                        conbyte[i+j]=0x30;
                    }
                    i += 3;
                }
            }
            content = new String(conbyte, "UTF-8");
            return content.replaceAll("0000", "");
        } catch (Exception e) {
            //Debug.print(e);
            return content;
        }
    }

    /**
     * 请求方法
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}"+ ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}"+ e);
        }
        return null;
    }

    /*
     * 请求xml组装
     */
    public static String getRequestXml(SortedMap<String,Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            if ("attach".equalsIgnoreCase(key)||"body".equalsIgnoreCase(key)||"sign".equalsIgnoreCase(key)) {
                sb.append("<"+key+">"+"<![CDATA["+value+"]]></"+key+">");
            }else {
                sb.append("<"+key+">"+value+"</"+key+">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

}
