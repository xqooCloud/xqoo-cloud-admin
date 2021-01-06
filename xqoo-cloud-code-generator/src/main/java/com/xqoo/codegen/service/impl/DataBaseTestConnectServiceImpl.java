package com.xqoo.codegen.service.impl;

import com.xqoo.codegen.dto.DataBasePropertiesDTO;
import com.xqoo.codegen.service.DataBaseTestConnectService;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service("dataBaseTestConnectService")
public class DataBaseTestConnectServiceImpl extends CodeGeneratorBaseServiceImpl implements DataBaseTestConnectService {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseTestConnectServiceImpl.class);

    @Override
    public ResultEntity<String> testDataBaseConnect(DataBasePropertiesDTO dto, String type) {
        String url = super.getDataBaseUrl(dto.getDataBaseHost(), dto.getDataBasePort(), dto.getDataBaseName(), dto.getProperties(), type);
        String classDriver = super.getClassDriver(type);
        Connection conn = null;
        String password = super.decodePassword(dto.getDataBasePassword());
        if(StringUtils.isEmpty(password)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "连接失败,连接密码转换错误");
        }
        try {
            Class.forName(classDriver);
            conn = DriverManager.getConnection(url, dto.getDataBaseUserName(), password);
            return new ResultEntity<>(HttpStatus.OK, "连接成功");
        } catch (Exception e) {
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "连接失败", e.getMessage());
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error("[代码生成中心]关闭测试mysql数据库连接发生错误，错误代码:{}, Sql状态: {},错误信息:{}",
                            e.getErrorCode(), e.getSQLState(), e.getMessage());
                }
            }
        }
    }
}
