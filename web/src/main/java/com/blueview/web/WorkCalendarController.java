package com.blueview.web;


import com.blueview.model.WorkCalendar;
import com.blueview.service.WorkCalendarService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workCalendar")
public class WorkCalendarController {
    @Autowired
    private WorkCalendarService workCalendarService;

    @RequestMapping(value = "/manage")
    public String addWorkCalendar(Model model) {
        WorkCalendar workCalendar = new WorkCalendar();
        model.addAttribute("workCalendar", workCalendar);
        return "html/workCalendar";
    }

    @RequestMapping(value = "/selectWorkCalendars")
    @ResponseBody
    public Map<String, Object> selectWorkCalendars(Integer draw, Integer start, Integer length,WorkCalendar workCalendar) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<WorkCalendar> workCalendars = workCalendarService.getWorkCalendarPageSelective(start, length,workCalendar);
        long total = workCalendars.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", workCalendars.getList());
        return result;
    }
    @RequestMapping(value = "/selectWorkCalendarsSelective")
    @ResponseBody
    public List<WorkCalendar> selectWorkCalendarsSelective(WorkCalendar workCalendar) {
        List<WorkCalendar> workCalendars = workCalendarService.getWorkCalendarsSelective(workCalendar);
        return workCalendars;
    }
    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateWorkCalendar")
    @ResponseBody
    public Map<String, Object> saveOrUpdateWorkCalendar(@RequestBody WorkCalendar workCalendar) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        List<WorkCalendar> workCalendars = workCalendarService.selectByDate(workCalendar.getDate());
        if(workCalendars.size() <= 0){
            int n = workCalendarService.insertSelective(workCalendar);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            workCalendar.setId(workCalendars.get(0).getId());
            int n = workCalendarService.updateByPrimaryKeySelective(workCalendar);
            if (n == 1) {
                map.put("msg", "修改成功！");
                map.put("code", 200);
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
    public WorkCalendar selectByPrimaryKey(Integer id) {
        return workCalendarService.selectByPrimaryKey(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */

    @RequestMapping("/deleteWorkCalendar")
    @ResponseBody
    public Map<String, Object> deleteWorkCalendar(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += workCalendarService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

