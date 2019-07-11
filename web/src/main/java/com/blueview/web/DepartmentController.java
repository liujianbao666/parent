package com.blueview.web;


import com.blueview.model.Department;
import com.blueview.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/manage")
    public String addDepartment(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "html/department";
    }

    @RequestMapping(value = "/selectDepartments")
    @ResponseBody
    public Map<String, Object> selectDepartments(Integer draw, Integer start, Integer length, Department department) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<Department> departments = departmentService.getDepartmentsPageSelective(start, length,department);
        long total = departments.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", departments.getList());
        return result;
    }
    @RequestMapping(value = "/selectAllDepartments")
    @ResponseBody
    public List<Department> selectAllDepartments(@RequestParam(required = false) Department department) {
        List<Department> departments = departmentService.getDepartmentsSelective(department);
        return departments;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateDepartment")
    @ResponseBody
    public Map<String, Object> saveOrUpdateDepartment(@ModelAttribute(value = "department")Department department) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Department departmenttmp = new Department();
        departmenttmp.setCode(department.getCode());
        List<Department> departmentsSelective = departmentService.getDepartmentsSelective(departmenttmp);
        if(department.getId() == null|| "".equals(department.getId()) ){
            if (departmentsSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = departmentService.insertSelective(department);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (departmentsSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(departmentsSelective.size() == 0 || (int)departmentsSelective.get(0).getId()==(int)department.getId()){
                    int n = departmentService.updateByPrimaryKeySelective(department);
                    if (n == 1) {
                        map.put("msg", "修改成功！");
                        map.put("code", 200);
                        return map;
                    }
                }
                map.put("msg", "编码已存在！");
                return map;
            }

            map.put("msg", "修改失败！");
        }

        return map;
    }

    /**
     * 按主键查询
     */
    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public Department selectByPrimaryKey(Integer id) {
        return departmentService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        Department departmenttmp = new Department();
        departmenttmp.setCode(department.getCode());
        int count = departmentService.countAllDepartments(departmenttmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = departmentService.updateByPrimaryKeySelective(department);
        map.put("msg", "修改成功！");
        map.put("code", 200);
*/
//        return map;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */

    @RequestMapping("/deleteDepartment")
    @ResponseBody
    public Map<String, Object> deleteDepartment(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += departmentService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

