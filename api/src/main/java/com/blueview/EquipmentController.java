package com.blueview;

import com.blueview.model.Equipment;
import com.blueview.service.EquipmentService;
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
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    /**
     * 查询
     * @param  equipment
     * @return
     */
    @RequestMapping(value = {"/equipments"})
    public ResultJson listEquipments(@RequestBody(required = true) Equipment equipment) {
        ResultJson resultJson = new ResultJson();

        List<Equipment> list = equipmentService.getEquipmentsSelective(equipment);

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
     * @param  equipment
     * @return
     */
    @RequestMapping(value = "/equipment", method = POST, produces = "application/json")
    public ResultJson addEquipment(@RequestBody(required = true) Equipment equipment) {
        ResultJson resultJson = new ResultJson();
        int count = equipmentService.insertSelective(equipment);
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
    @RequestMapping(value = "/equipment/{id}", method = DELETE, produces = "application/json")
    public ResultJson delEquipment(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = equipmentService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  equipment
     * @return
     */
    @RequestMapping(value = "/equipment/{id}", method = PUT, produces = "application/json")
    public ResultJson updateEquipment(@PathVariable(name = "id") String id,@RequestBody(required = true) Equipment equipment) {
        ResultJson resultJson = new ResultJson();
        equipment.setId(Integer.valueOf(id));
        int count = equipmentService.updateByPrimaryKeySelective(equipment);
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
