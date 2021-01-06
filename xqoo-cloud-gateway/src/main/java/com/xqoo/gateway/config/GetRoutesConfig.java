package com.xqoo.gateway.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.gateway.bean.ServerPortBean;
import com.xqoo.gateway.utils.SpringContextUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GetRoutesConfig {


    private static ServerPortBean serverPortBean = SpringContextUtil.getBean(ServerPortBean.class);


    public static List<Object> onHttp(){
        String port = serverPortBean.getServerPort();
        //配置OkHttp
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        Request.Builder requestBuilder = new Request.Builder();
        //配置url
        requestBuilder.url("http://127.0.0.1:" + port + "/xqooSysActuator/gateway/routes");
        //配置请求方式
        requestBuilder.get();
        try {
            //发送网络请求
            Response response = client.newCall(requestBuilder.build()).execute();
            assert response.body() != null;
            String result = response.body().string();
            return JacksonUtils.toObj(result, new TypeReference<List<Object>>() { });
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
