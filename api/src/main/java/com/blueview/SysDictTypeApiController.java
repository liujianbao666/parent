package com.blueview;

import com.blueview.model.SysDictType;
import com.blueview.service.SysDictTypeService;
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
public class SysDictTypeApiController {
    @Autowired
    SysDictTypeService sysDictTypeService;

    /**
     * 查询
     * @param  sysDictType
     * @return
     */
    @RequestMapping(value = {"/sysDictTypes"})
    public ResultJson listSysDictTypes(@RequestBody(required = true) SysDictType sysDictType) {
        ResultJson resultJson = new ResultJson();

        List<SysDictType> list = sysDictTypeService.getSysDictTypesSelective(sysDictType);

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
     * @param  sysDictType
     * @return
     */
    @RequestMapping(value = "/sysDictType", method = POST, produces = "application/json")
    public ResultJson addSysDictType(@RequestBody(required = true) SysDictType sysDictType) {
        ResultJson resultJson = new ResultJson();
        int count = sysDictTypeService.insertSelective(sysDictType);
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
    @RequestMapping(value = "/sysDictType/{id}", method = DELETE, produces = "application/json")
    public ResultJson delSysDictType(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = sysDictTypeService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  sysDictType
     * @return
     */
    @RequestMapping(value = "/sysDictType/{id}", method = PUT, produces = "application/json")
    public ResultJson updateSysDictType(@PathVariable(name = "id") String id,@RequestBody(required = true) SysDictType sysDictType) {
        ResultJson resultJson = new ResultJson();
        sysDictType.setId(Integer.valueOf(id));
        int count = sysDictTypeService.updateByPrimaryKeySelective(sysDictType);
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
