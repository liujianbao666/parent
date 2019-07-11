package com.blueview.web;


import com.blueview.model.Equipment;
import com.blueview.service.EquipmentService;
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
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping(value = "/manage")
    public String addEquipment(Model model) {
        Equipment equipment = new Equipment();
        model.addAttribute("equipment", equipment);
        return "html/equipment";
    }

    @RequestMapping(value = "/selectEquipments")
    @ResponseBody
    public Map<String, Object> selectEquipments(Integer draw, Integer start, Integer length,Equipment equipment) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<Equipment> equipments = equipmentService.getEquipmentPageSelective(start, length,equipment);
        long total = equipments.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", equipments.getList());
        return result;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateEquipment")
    @ResponseBody
    public Map<String, Object> saveOrUpdateEquipment(@ModelAttribute(value = "equipment")Equipment equipment) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Equipment equipmenttmp = new Equipment();
        equipmenttmp.setCode(equipment.getCode());
        List<Equipment> equipmentsSelective = equipmentService.getEquipmentsSelective(equipmenttmp);
        if(equipment.getId() == null|| "".equals(equipment.getId()) ){
            if (equipmentsSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = equipmentService.insertSelective(equipment);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (equipmentsSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(equipmentsSelective.size() ==0 || (int)equipmentsSelective.get(0).getId()==(int)equipment.getId()){
                    int n = equipmentService.updateByPrimaryKeySelective(equipment);
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
    public Equipment selectByPrimaryKey(Integer id) {
        return equipmentService.selectByPrimaryKey(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */

    @RequestMapping("/deleteEquipment")
    @ResponseBody
    public Map<String, Object> deleteEquipment(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += equipmentService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

