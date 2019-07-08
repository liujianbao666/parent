package com.blueview;

import com.blueview.model.Department;
import com.blueview.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 接口对外Api
 *
 **/
@RestController
@RequestMapping("/api")
@Log4j2
public class DepartmentApiController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 查询
     * @param  department
     * @return
     */
    @RequestMapping(value = {"/departments"})
    public ResultJson listDepartments(@RequestBody(required = true) Department department) {
        ResultJson resultJson = new ResultJson();

        List<Department> list = departmentService.getDepartmentsSelective(department);

        if(list.size()>0){
            resultJson.setCode("200");
            resultJson.setMsg("成功!");
            resultJson.setData(list);
        }
        else{
            resultJson.setCode("-1");
            resultJson.setMsg("数据为空!");
        }
        return resultJson;
    }
    /**
     * 添加
     * @param  department
     * @return
     */
    @RequestMapping(value = "/department", method = POST, produces = "application/json")
    public ResultJson addDepartment(@RequestBody(required = true) Department department) {
        ResultJson resultJson = new ResultJson();
        int count = departmentService.insertSelective(department);
        if(count==1){
            resultJson.setCode("200");
            resultJson.setMsg("添加成功!");
            resultJson.setData("count:"+count);
        }
        else{
            resultJson.setCode("-1");
            resultJson.setMsg("添加失败!");
        }
        return resultJson;
    }

    /**
     * 删除
     * @param  id
     * @return
     */
    @RequestMapping(value = "/department/{id}", method = DELETE, produces = "application/json")
    public ResultJson delDepartment(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = departmentService.deleteByPrimaryKey(Integer.valueOf(id));
        if(count==1){
            resultJson.setCode("200");
            resultJson.setMsg("删除成功!");
            resultJson.setData("count:"+count);
        }
        else{
            resultJson.setCode("-1");
            resultJson.setMsg("删除失败!");
        }
        return resultJson;
    }
    /**
     * 修改信息
     * @param  department
     * @return
     */
    @RequestMapping(value = "/department/{id}", method = PUT, produces = "application/json")
    public ResultJson updateDepartment(@PathVariable(name = "id") String id,@RequestBody(required = true) Department department) {
        ResultJson resultJson = new ResultJson();
        department.setId(Integer.valueOf(id));
        int count = departmentService.updateByPrimaryKeySelective(department);
        if(count==1){
            resultJson.setCode("200");
            resultJson.setMsg("修改成功!");
            resultJson.setData("count:"+count);
        }
        else{
            resultJson.setCode("-1");
            resultJson.setMsg("修改失败!");
        }
        return resultJson;
    }

}
