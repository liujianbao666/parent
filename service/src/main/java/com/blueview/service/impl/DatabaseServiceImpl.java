package com.blueview.service.impl;

import com.blueview.dao.DatabaseMapper;
import com.blueview.service.DatabaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseMapper databaseMapper;
    @Override
    public int alterTableName(String originalTableName, String newTableName) {
        return databaseMapper.alterTableName(originalTableName,newTableName);
    }

    @Override
    public int truncateTable(String tableName) {
        return databaseMapper.truncateTable(tableName);
    }

    @Override
    public void createNewTableAndInsertData(String newTableName, String originalTableName) {
        databaseMapper.createNewTableAndInsertData(newTableName,originalTableName);
    }

    @Override
    public int getRecordCount(String tableName) {
        return databaseMapper.getRecordCount(tableName);
    }

    @Override
    public String getCurDataBaseName() {
        return databaseMapper.getCurDataBaseName();
    }

    @Override
    public String isTargetTableExistInDB(String dataBaseName, String tableName) {
        return databaseMapper.isTargetTableExistInDB(dataBaseName,tableName);
    }

    @Override
    public Map showCretaTable(String tableName) {
        return databaseMapper.showCretaTable(tableName);
    }

    @Override
    public void dropTable(String tableName) {
        databaseMapper.dropTable(tableName);
    }

    @Override
    public void updateTableComment(String tableName, String tableComment) {
        databaseMapper.updateTableComment(tableName,tableComment);
    }

    @Override
    public void createNewTable(String tableName,String tableComment, Map coll) {
        databaseMapper.createNewTable(tableName,tableComment,coll);
    }

    @Override
    public void addColumn(String tableName, Map coll) {
        databaseMapper.addColumn(tableName,coll);
    }

    @Override
    public void updateColumnName(String tableName, String oldColumn, String newColumn, String columnType) {
        databaseMapper.updateColumnName(tableName,oldColumn,newColumn,columnType);
    }

    @Override
    public List<Map<String, String>> getDataBaseTableName() {
        return databaseMapper.getDataBaseTableName();
    }

    @Override
    public List<Map<String, String>> getTableDetail(String tableName,String columnName) {
        return databaseMapper.getTableDetail(tableName,columnName);
    }

    @Override
    public PageInfo<Map<String, String>> getTableDetailPage(Integer start, Integer length, String tableName) {
        PageHelper.offsetPage(start, length);
        List<Map<String, String>> tableDetail = databaseMapper.getTableDetail(tableName,null);
        //获取分页查询后的数据
        PageInfo<Map<String, String>> pageInfo = new PageInfo<>(tableDetail);
        return pageInfo;
    }

    @Override
    public int dropColumn(String tableName, String columnName) {
        return databaseMapper.dropColumn(tableName,columnName);
    }
}
