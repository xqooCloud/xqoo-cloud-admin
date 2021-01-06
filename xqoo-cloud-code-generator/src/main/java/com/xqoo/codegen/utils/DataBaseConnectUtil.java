package com.xqoo.codegen.utils;

import com.xqoo.codegen.dto.DataBasePropertiesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * mysql数据库工具
 */
public class DataBaseConnectUtil {

    private final static Logger logger = LoggerFactory.getLogger(DataBaseConnectUtil.class);


    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection(DataBasePropertiesDTO dto) {
        Connection conn = null;
        try {
            Class.forName(dto.getDriverClass());
            conn = DriverManager.getConnection(dto.getUrl(), dto.getDataBaseUserName(), dto.getDataBasePassword());
        } catch (Exception e) {
            logger.error("[代码生成中心]获取mysql数据库连接发生错误，错误原因:{},错误信息:{}",
                    e.getClass().getSimpleName(),  e.getMessage());
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("[代码生成中心]关闭mysql数据库连接发生错误，错误代码:{}, Sql状态: {},错误信息:{}",
                        e.getErrorCode(), e.getSQLState(), e.getMessage());
            }
        }
    }
}
