package com.blueview.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 执行备份数据库相关表的Mapper
 */
@Mapper
public interface DatabaseMapper {

        /**
         * 修改数据库的表名字
         * @param originalTableName
         * @param newTableName
         * @return
         */
        int alterTableName(@Param("originalTableName") String originalTableName,
                           @Param("newTableName") String newTableName);

        /**
         * truncate指定数据库表的数据
         * @param tableName
         * @return
         */
        int truncateTable(@Param("tableName") String tableName);

        /**
         * 修改表备注名称
         * @param tableName
         * @param tableComment
         */
        void updateTableComment(@Param("tableName") String tableName,@Param("tableComment") String tableComment);
        /**
         * 根据传入的表名，创建新的表并且将原表的数据插入到新的Occur表中
         * @param newTableName
         * @param originalTableName
         */
        void createNewTableAndInsertData(@Param("newTableName") String newTableName,
                                         @Param("originalTableName") String originalTableName);

        /**
         * 统计某张表中的总数据条数。
         * @param tableName
         * @return 指定表中的总记录条数。
         */
        int getRecordCount(@Param("tableName") String tableName);

        /**
         * 获得当前数据库的名字
         * @return
         */
        String getCurDataBaseName();

        /**
         * 从指定数据库中，查询是否存在某张表
         * @param dataBaseName
         * @param tableName
         * @return
         */
        String isTargetTableExistInDB(@Param("dataBaseName") String dataBaseName,
                                      @Param("tableName") String tableName);

        /**
         *  获取当前表的创建表语句和表名称
         * @param tableName
         * @return
         */
        Map<String,String> showCretaTable( @Param("tableName") String tableName);

        /**
         * 删除表
         * @param tableName
         */
        void dropTable(@Param("tableName") String tableName);

        /**
         * 创建表
         * @param tableName
         * @param coll
         */
        void createNewTable(@Param("tableName") String tableName,@Param("tableComment") String tableComment,@Param("coll") Map coll);

        /**
         * 为表增加列
         * @param tableName
         * @param coll
         */
        void addColumn(@Param("tableName") String tableName,@Param("coll") Map coll);

        /**
         * 删除字段
         * @param tableName
         * @param columnName
         * @return
         */
        int dropColumn(@Param("tableName") String tableName,@Param("columnName") String columnName);

        /**
         * 更新表字段名称
         * @param tableName
         * @param oldColumn
         * @param newColumn
         * @param columnType
         */
        void updateColumnName(@Param("tableName") String tableName,@Param("oldColumn") String oldColumn,@Param("newColumn") String newColumn,@Param("columnType") String columnType);

        /**
         * 查询数据所有表
         * @return
         */
        List<Map<String,String>> getDataBaseTableName();

        /**
         * 根据表名查询表字段信息
         * @param tableName
         * @return
         */
        List<Map<String,String>> getTableDetail(@Param("tableName") String tableName,@Param("columnName") String columnName);
}