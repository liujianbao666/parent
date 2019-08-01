package com.blueview.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blueview.model.Company;
import com.blueview.model.TableManage;
import com.blueview.service.DatabaseService;
import com.blueview.service.TableManagerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private TableManagerService tableManagerService;

    @RequestMapping(value = "/manage")
    public String addCompany(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "html/database";
    }

    /**
     * 新建表
     */
    @RequestMapping("/saveOrUpdateTable")
    @ResponseBody
    public Map<String, Object> saveOrUpdateTable(String tableName, String tableComment, String oldTableName, String rows) {
        Map<String, Object> map = new HashMap<>();
        //查询是否已存在
        String column = databaseService.isTargetTableExistInDB("mdm", tableName);
        if (column == null) {
            Map<String, String> columnMap = new HashMap();
            System.out.println(rows);
            JSONArray jsonArray = JSONArray.parseArray(rows);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject js = (JSONObject) (jsonArray.get(i));
                columnMap.put(js.get("column_name").toString(), js.get("column_type").toString());
            }

            try {
                databaseService.createNewTable(tableName, tableComment, columnMap);
                map.put("msg", "表创建成功！");
                map.put("code", 200);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
                map.put("msg", "表创建失败！");
            }
        } else {//如果不为空时为修改
            try {
                databaseService.alterTableName(tableName, oldTableName);
                map.put("msg", "修改成功！");
                map.put("code", 200);
                return map;
            } catch (Exception e) {
                map.put("msg", "修改失败！");
            }
        }

        return map;
    }

    /**
     * 删除表
     */
    @RequestMapping("/dropTable")
    @ResponseBody
    public Map<String, Object> dropTable(String tableName) {
        Map<String, Object> map = new HashMap<>();
        try {
            databaseService.dropTable(tableName);
            map.put("msg", "表删除成功！");
            map.put("code", 200);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "表删除失败！");
        }
        return map;
    }

    /**
     * 修改表备注
     */
    @RequestMapping("/updateTableComment")
    @ResponseBody
    public Map<String, Object> updateTableComment(String tableName, String tableComment) {
        Map<String, Object> map = new HashMap<>();
        try {
            databaseService.updateTableComment(tableName, tableComment);
            map.put("msg", "表备注修改成功！");
            map.put("code", 200);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "表备注修改失败！");
        }
        return map;
    }

    /**
     * 获取目录tree
     *
     * @return
     */
    @RequestMapping("/getTree")
    @ResponseBody
    public JSONArray getTree() {
        JSONArray result = new JSONArray();
        List<Map<String, String>> dataBaseTableName = databaseService.getDataBaseTableName();
        JSONObject parent = new JSONObject();
        parent.put("id", 0);
        parent.put("parent", "#");
        parent.put("text", "MDM数据库");
        result.add(parent);
        for (int i = 0; i < dataBaseTableName.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            String tableName = dataBaseTableName.get(i).get("table_name");
            jsonObject.put("id", tableName);
            TableManage tableManage = new TableManage();
            tableManage.setSideTable(tableName);
            List<TableManage> tableManages = tableManagerService.getTableManagerSelective(tableManage);
            if(tableManages.size()>0){
                for (TableManage t:tableManages) {
                    jsonObject.put("parent", t.getMainTable());
                }
            }else{
                jsonObject.put("parent", "0");
            }


            jsonObject.put("text", dataBaseTableName.get(i).get("comment"));
            result.add(jsonObject);
        }
        return result;
    }


    /**
     * 获取表的详细信息
     *
     * @return
     */
    @RequestMapping("/loadData")
    @ResponseBody
    public Map<String, Object> loadData(int draw, int start, int length, String tableName) {
        Map<String, Object> result = new HashMap<>();
        PageInfo<Map<String, String>> res = databaseService.getTableDetailPage(start, length, tableName);
        long total = res.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", res.getList());
        return result;
    }

    /**
     * 获取表字段的详细信息
     *
     * @return
     */
    @RequestMapping("/loadDataByColumnName")
    @ResponseBody
    public List<Map<String, String>> loadDataByColumnName(String tableName, String columnName) {
        List<Map<String, String>> column = databaseService.getTableDetail(tableName, columnName);
        return column;
    }

    /**
     * 添加字段
     */
    @RequestMapping("/saveOrUpdateColumn")
    @ResponseBody
    public Map<String, Object> saveOrUpdateColumn(String tableName, String columnName, String columnType, String columnKey, String columnComment, String newColumnName) {
        Map<String, Object> map = new HashMap<>();
        //查询是否已存在
        List<Map<String, String>> column = databaseService.getTableDetail(tableName, columnName);
        if (column.size() == 0) {
            Map<String, String> columnMap = new HashMap<>();
            columnMap.put(columnName, columnType + " COMMENT '" + columnComment + "'");
            try {
                databaseService.addColumn(tableName, columnMap);
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
                map.put("msg", "添加失败！");
            }
        } else {//如果不为空时为修改
            try {
                databaseService.updateColumnName(tableName, columnName, newColumnName, columnType);
                map.put("msg", "修改成功！");
                map.put("code", 200);
                return map;
            } catch (Exception e) {
                map.put("msg", "修改失败！");
            }
        }

        return map;
    }

    /**
     * 删除字段
     */
    @RequestMapping("/dropColumn")
    @ResponseBody
    public Map<String, Object> dropColumn(String tableName, String[] ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            for (String id : ids) {
                databaseService.dropColumn(tableName, id);
            }
            map.put("msg", "删除成功！");
            map.put("code", 200);
            return map;
        } catch (Exception e) {
            map.put("msg", "删除失败！");
            return map;
        }
    }

    /**
     * 添加副表
     */
    @RequestMapping("/saveTableManager")
    @ResponseBody
    public Map<String, Object> saveTableManager(TableManage tableManage) {
        Map<String, Object> map = new HashMap<>();
        tableManage.setIsSynchronized(false);
        int count = tableManagerService.insertSelective(tableManage);
        if (count > 0) {
            map.put("msg", "添加成功！");
            map.put("code", 200);
            return map;
        } else {
            map.put("msg", "添加失败！");
        }

        return map;
    }
    /**
     * 查询副表
     */
    @RequestMapping("/selectTableManager")
    @ResponseBody
    public List<TableManage> selectTableManager(TableManage tableManage) {
        List<TableManage> tableManages = tableManagerService.getTableManagerSelective(tableManage);
        return tableManages;
    }
}

