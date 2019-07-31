package com.blueview;

import com.blueview.service.DatabaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 接口对外Api
 *
 **/
@RestController
@RequestMapping("/api/database")
@Log4j2
public class DatabaseApiController {
    @Autowired
    DatabaseService databaseService;

    /**
     * @param map
     * @return
     *{
     * 	"tableName":"a",
     * 	"a":"varchar(20)",
     * 	"b":"int"
     *
     * }
     */
    @RequestMapping(value = {"/createtable"})
    public ResultJson createtable(@RequestBody Map<String,String> map) {
        ResultJson resultJson = new ResultJson();
        String tableName = map.get("tableName");
        String tableComment = map.get("tableComment");
        map.remove("tableName");

        try {
            databaseService.createNewTable(tableName,tableComment,map);
            resultJson.setCode("200");
            resultJson.setMsg("表创建成功!");
        }catch (Exception e){
            resultJson.setCode("-1");
            resultJson.setMsg("表创建失败!");
        }

        return resultJson;
    }

    /**
     *
     * @param map
     * @return
     * {
     * 	"tableName":"a",
     * 	"ccc":"varchar(200)"
     *
     * }
     */
    @RequestMapping(value = {"/addColumn"})
    public ResultJson addColumn(@RequestBody Map<String,String> map) {
        ResultJson resultJson = new ResultJson();
        String tableName = map.get("tableName");
        map.remove("tableName");

        try {
            databaseService.addColumn(tableName,map);
            resultJson.setCode("200");
            resultJson.setMsg("字段添加成功!");
        }catch (Exception e){
            resultJson.setCode("-1");
            resultJson.setMsg("字段添加失败!");
        }

        return resultJson;
    }

    /**
     * {
     * 	"tableName":"a",
     * 	"oldColumn":"ccc",
     * 	"newColumn":"newColumn",
     * 	"columnType":"varchar(2)"
     * }
     * @param map
     * @return
     */
    @RequestMapping(value = {"/updateColumnName"})
    public ResultJson updateColumnName(@RequestBody Map<String,String> map) {
        ResultJson resultJson = new ResultJson();
        try {
            databaseService.updateColumnName(map.get("tableName"),map.get("oldColumn"),map.get("newColumn"),map.get("columnType"));
            resultJson.setCode("200");
            resultJson.setMsg("字段名称更新成功!");
        }catch (Exception e){
            resultJson.setCode("-1");
            resultJson.setMsg("字段名称更新失败!");
        }

        return resultJson;
    }


    @RequestMapping(value = {"/getDataBaseTableName"})
    public ResultJson getDataBaseTableName() {
        ResultJson resultJson = new ResultJson();
        try {
            List<Map<String,String>> map = databaseService.getDataBaseTableName();
            resultJson.setCode("200");
            resultJson.setMsg("数据库表查询成功!");
            resultJson.setData(map);
        }catch (Exception e){
            e.printStackTrace();
            resultJson.setCode("-1");
            resultJson.setMsg("数据库表查询失败!");
        }

        return resultJson;
    }
}
