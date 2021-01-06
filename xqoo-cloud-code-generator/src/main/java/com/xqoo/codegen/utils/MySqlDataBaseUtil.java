package com.xqoo.codegen.utils;

import com.xqoo.codegen.dto.DataBasePropertiesDTO;
import com.xqoo.codegen.entity.TableColumnsEntity;
import com.xqoo.codegen.entity.TableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDataBaseUtil {

    private final static Logger logger = LoggerFactory.getLogger(MySqlDataBaseUtil.class);

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    /**
     * 获取数据库下的所有表名
     */
    public static List<TableEntity> getTableNames(DataBasePropertiesDTO dto) {
        List<TableEntity> tableNames = new ArrayList<>();
        Connection conn = DataBaseConnectUtil.getConnection(dto);
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("select * from information_schema.tables where table_schema= ? and table_type='base table'");
            preparedStatement.setString(1, dto.getDataBaseName());
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                TableEntity entity = new TableEntity();
                entity.setComment(rs.getString("TABLE_COMMENT"));
                entity.setTableName(rs.getString("TABLE_NAME"));
                entity.setSchemeName(rs.getString("TABLE_SCHEMA"));
                tableNames.add(entity);
            }
        } catch (SQLException e) {
            logger.error("[代码生成中心]获取mysql数据库中表名发生错误，错误代码:{}, Sql状态: {},错误信息:{}",
                    e.getErrorCode(), e.getSQLState(), e.getMessage());
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                DataBaseConnectUtil.closeConnection(conn);
            } catch (SQLException e) {
                logger.error("[代码生成中心]关闭mysql数据库连接和结果集发生错误，错误代码:{}, Sql状态: {},错误信息:{}",
                        e.getErrorCode(), e.getSQLState(), e.getMessage());
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段属性
     * @return
     */
    public static List<TableColumnsEntity> getColumns(DataBasePropertiesDTO dto) {
        List<TableColumnsEntity> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = DataBaseConnectUtil.getConnection(dto);
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("select * from information_schema.columns where table_name=?");
            preparedStatement.setString(1, dto.getTableName());
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                TableColumnsEntity entity = new TableColumnsEntity();
                entity.setPrimaryKey("PRI".equals(rs.getString("COLUMN_KEY")));
                entity.setAutoIncrement("auto_increment".equals(rs.getString("EXTRA")));
                entity.setColumnDisplaySize(rs.getInt("CHARACTER_MAXIMUM_LENGTH"));
                entity.setColumnName(rs.getString("COLUMN_NAME"));
                entity.setColumnsTypeName(rs.getString("DATA_TYPE"));
                entity.setNullAble("YES".equals(rs.getString("IS_NULLABLE")));
                entity.setComment(rs.getString("COLUMN_COMMENT"));
                entity.setChecked(true);
                columnNames.add(entity);
            }
        } catch (SQLException e) {
            logger.error("[代码生成中心]获取mysql数据库中{}表字段名发生错误，错误代码:{}, Sql状态: {},错误信息:{}",
                    dto.getTableName(), e.getErrorCode(), e.getSQLState(), e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    DataBaseConnectUtil.closeConnection(conn);
                } catch (SQLException e) {
                    logger.error("[代码生成中心]关闭mysql数据库连接和结果集发生错误，错误代码:{}, Sql状态: {},错误信息:{}",
                            e.getErrorCode(), e.getSQLState(), e.getMessage());
                }
            }
        }
        return columnNames;
    }

}
