package com.xqoo.common.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.ServletUtils;
import com.xqoo.common.core.utils.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author mumu
 * @date 2019/11/07 上午11:13
 * 自定义字段填充处理器
 * 被填充的字段传值为null就行
 * createDate 创建时间 updateDate 更新时间
 * createBy updateBy 均为执行操作人的登录名 没有填入 "系统"
 * 使用 @TableField(value = "create_date", fill = FieldFill.INSERT) FieldFill插入或者更新 记住是小写,对应的数据库字段名字
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 除去controller service mapper configuration注解的启动注入类，其他工具类需要注入bean时必须加下面这两个，否则注入进来的mapper永远是null
//    private static MyMetaObjectHandler myMetaObjectHandler;
//    @PostConstruct
//    public void init(){
//        myMetaObjectHandler=this;
//    }

    @Override
    public void insertFill(MetaObject metaObject) {
        CurrentUser currentUser = getCurrentUser(ServletUtils.getRequest());
        //填充插入时间
        this.setFieldValByName("createDate",new Date(),metaObject);
        //填充创建人
        if(StringUtils.isEmpty(currentUser.getUserId())){
            this.setFieldValByName("createBy",currentUser.getUserName(), metaObject);
        }else{
            this.setFieldValByName("createBy",currentUser.getUserId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        CurrentUser currentUser = getCurrentUser(ServletUtils.getRequest());
        //填充创建人
        //填充更新时间
        this.setFieldValByName("updateDate",new Date(),metaObject);
        //填充创建人
        if(StringUtils.isEmpty(currentUser.getUserId())){
            this.setFieldValByName("updateBy",currentUser.getUserName(), metaObject);
        }else{
            this.setFieldValByName("updateBy",currentUser.getUserId(), metaObject);
        }
    }

    private CurrentUser getCurrentUser(HttpServletRequest request){
        String token = request.getHeader(SystemPublicConstant.AUTH_HEADER_KEY_NAME);
        CurrentUser currentUser = new CurrentUser();
        if(StringUtils.isNotEmpty(token)){
            currentUser.setUserId(request.getHeader(SystemPublicConstant.USER_ID_HEADER_KEY_NAME));
            currentUser.setUserName(request.getHeader(SystemPublicConstant.USER_NAME_HEADER_KEY_NAME));
        }else{
            currentUser.setUserName(request.getHeader(SystemPublicConstant.REMOTE_REQUEST_IP));
        }
        return currentUser;
    }
}
