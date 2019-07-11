package com.blueview;

import com.blueview.model.WorkCalendar;
import com.blueview.service.WorkCalendarService;
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
public class WorkCalendarApiController {
    @Autowired
    WorkCalendarService workCalendarService;

    /**
     * 查询
     * @param  workCalendar
     * @return
     */
    @RequestMapping(value = {"/workCalendars"})
    public ResultJson listWorkCalendars(@RequestBody(required = true) WorkCalendar workCalendar) {
        ResultJson resultJson = new ResultJson();

        List<WorkCalendar> list = workCalendarService.getWorkCalendarsSelective(workCalendar);

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
     * @param  workCalendar
     * @return
     */
    @RequestMapping(value = "/workCalendar", method = POST, produces = "application/json")
    public ResultJson addWorkCalendar(@RequestBody(required = true) WorkCalendar workCalendar) {
        ResultJson resultJson = new ResultJson();
        int count = workCalendarService.insertSelective(workCalendar);
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
    @RequestMapping(value = "/workCalendar/{id}", method = DELETE, produces = "application/json")
    public ResultJson delWorkCalendar(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = workCalendarService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  workCalendar
     * @return
     */
    @RequestMapping(value = "/workCalendar/{id}", method = PUT, produces = "application/json")
    public ResultJson updateWorkCalendar(@PathVariable(name = "id") String id,@RequestBody(required = true) WorkCalendar workCalendar) {
        ResultJson resultJson = new ResultJson();
        workCalendar.setId(Integer.valueOf(id));
        int count = workCalendarService.updateByPrimaryKeySelective(workCalendar);
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
