package com.blueview.web;


import com.blueview.model.Employer;
import com.blueview.service.EmployerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerService employerService;

    @RequestMapping(value = "/manage")
    public String addEmployer(Model model) {
        Employer employer = new Employer();
        model.addAttribute("employer", employer);
        return "html/employer";
    }

    @RequestMapping(value = "/selectEmployers")
    @ResponseBody
    public Map<String, Object> selectEmployers(Integer draw, Integer start, Integer length, Employer employer) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<Employer> employers = employerService.getEmployersPageSelective(start, length,employer);
        long total = employers.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", employers.getList());
        return result;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateEmployer")
    @ResponseBody
    public Map<String, Object> saveOrUpdateEmployer(@ModelAttribute(value = "employer")Employer employer) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Employer employertmp = new Employer();
        employertmp.setCode(employer.getCode());
        List<Employer> employersSelective = employerService.getEmployersSelective(employertmp);
        if(employer.getId() == null|| "".equals(employer.getId()) ){
            if (employersSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = employerService.insertSelective(employer);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (employersSelective.size()< 2) {
                //数据中就一条数据看是不是自己
                if(employersSelective.size() == 0 || (int)employersSelective.get(0).getId()==(int)employer.getId()){
                    int n = employerService.updateByPrimaryKeySelective(employer);
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
    public Employer selectByPrimaryKey(Integer id) {
        return employerService.selectByPrimaryKey(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */

    @RequestMapping("/deleteEmployer")
    @ResponseBody
    public Map<String, Object> deleteEmployer(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += employerService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

