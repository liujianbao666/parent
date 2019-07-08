package com.blueview;

import com.blueview.model.ProductCapacity;
import com.blueview.service.ProductCapacityService;
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
public class ProductCapacityController {
    @Autowired
    ProductCapacityService productCapacityService;

    /**
     * 查询
     * @param  productCapacity
     * @return
     */
    @RequestMapping(value = {"/productCapacitys"})
    public ResultJson listProductCapacitys(@RequestBody(required = true) ProductCapacity productCapacity) {
        ResultJson resultJson = new ResultJson();

        List<ProductCapacity> list = productCapacityService.getProductCapacitiesSelective(productCapacity);

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
     * @param  productCapacity
     * @return
     */
    @RequestMapping(value = "/productCapacity", method = POST, produces = "application/json")
    public ResultJson addProductCapacity(@RequestBody(required = true) ProductCapacity productCapacity) {
        ResultJson resultJson = new ResultJson();
        int count = productCapacityService.insertSelective(productCapacity);
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
    @RequestMapping(value = "/productCapacity/{id}", method = DELETE, produces = "application/json")
    public ResultJson delProductCapacity(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = productCapacityService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  productCapacity
     * @return
     */
    @RequestMapping(value = "/productCapacity/{id}", method = PUT, produces = "application/json")
    public ResultJson updateProductCapacity(@PathVariable(name = "id") String id,@RequestBody(required = true) ProductCapacity productCapacity) {
        ResultJson resultJson = new ResultJson();
        productCapacity.setId(Integer.valueOf(id));
        int count = productCapacityService.updateByPrimaryKeySelective(productCapacity);
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
