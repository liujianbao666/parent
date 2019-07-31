package com.blueview.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface DatabaseService {

    int alterTableName(String originalTableName, String newTableName);

    int truncateTable(String tableName);

    void createNewTableAndInsertData(String newTableName, String originalTableName);

    int getRecordCount(String tableName);

    String getCurDataBaseName();

    String isTargetTableExistInDB(String dataBaseName, String tableName);

    Map showCretaTable(String tableName);

    void dropTable(String tableName);

    void updateTableComment(String tableName,String tableComment);

    void createNewTable(String tableName,String tableComment, Map coll);

    void addColumn(String tableName,Map coll);

    void updateColumnName(String tableName,String oldColumn,String newColumn,String columnType);

    List<Map<String,String>> getDataBaseTableName();

    List<Map<String,String>> getTableDetail(String tableName,String columnName);

    PageInfo<Map<String, String>> getTableDetailPage(Integer start, Integer length, String tableName);

    int dropColumn(String tableName,String columnName);
}
