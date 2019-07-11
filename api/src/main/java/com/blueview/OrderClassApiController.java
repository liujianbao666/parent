package com.blueview;

import com.blueview.model.OrderClass;
import com.blueview.service.OrderClassService;
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
public class OrderClassApiController {
    @Autowired
    OrderClassService orderClassService;

    /**
     * 查询
     * @param  orderClass
     * @return
     */
    @RequestMapping(value = {"/orderClasss"})
    public ResultJson listOrderClasss(@RequestBody(required = true) OrderClass orderClass) {
        ResultJson resultJson = new ResultJson();

        List<OrderClass> list = orderClassService.getOrderClassesSelective(orderClass);

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
     * @param  orderClass
     * @return
     */
    @RequestMapping(value = "/orderClass", method = POST, produces = "application/json")
    public ResultJson addOrderClass(@RequestBody(required = true) OrderClass orderClass) {
        ResultJson resultJson = new ResultJson();
        int count = orderClassService.insertSelective(orderClass);
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
    @RequestMapping(value = "/orderClass/{id}", method = DELETE, produces = "application/json")
    public ResultJson delOrderClass(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = orderClassService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  orderClass
     * @return
     */
    @RequestMapping(value = "/orderClass/{id}", method = PUT, produces = "application/json")
    public ResultJson updateOrderClass(@PathVariable(name = "id") String id,@RequestBody(required = true) OrderClass orderClass) {
        ResultJson resultJson = new ResultJson();
        orderClass.setId(Integer.valueOf(id));
        int count = orderClassService.updateByPrimaryKeySelective(orderClass);
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
