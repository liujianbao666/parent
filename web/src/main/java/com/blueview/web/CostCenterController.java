package com.blueview.web;


import com.blueview.model.CostCenter;
import com.blueview.service.CostCenterService;
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
@RequestMapping("/costCenter")
public class CostCenterController {
    @Autowired
    private CostCenterService costCenterService;

    @RequestMapping(value = "/manage")
    public String addCostCenter(Model model) {
        CostCenter costCenter = new CostCenter();
        model.addAttribute("costCenter", costCenter);
        return "html/costCenter";
    }

    @RequestMapping(value = "/selectCostCenters")
    @ResponseBody
    public Map<String, Object> selectCostCenters(Integer draw, Integer start, Integer length, CostCenter costCenter) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<CostCenter> costCenters = costCenterService.getCostCentersPageSelective(start, length,costCenter);
        long total = costCenters.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", costCenters.getList());
        return result;
    }
    @RequestMapping(value = "/selectCostCenterList")
    @ResponseBody
    public List<CostCenter> selectCostCenterList(CostCenter costCenter) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<CostCenter> costCenters = costCenterService.getCostCentersSelective(costCenter);

        return costCenters;
    }
    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateCostCenter")
    @ResponseBody
    public Map<String, Object> saveOrUpdateCostCenter(@ModelAttribute(value = "costCenter")CostCenter costCenter) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        CostCenter costCentertmp = new CostCenter();
        costCentertmp.setCode(costCenter.getCode());
        List<CostCenter> costCentersSelective = costCenterService.getCostCentersSelective(costCentertmp);
        if(costCenter.getId() == null|| "".equals(costCenter.getId()) ){
            if (costCentersSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = costCenterService.insertSelective(costCenter);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (costCentersSelective.size() < 2 ) {
                //数据中就一条数据看是不是自己
                if(costCentersSelective.size() == 0 ||  (int)costCentersSelective.get(0).getId()==(int)costCenter.getId()){
                    int n = costCenterService.updateByPrimaryKeySelective(costCenter);
                    if (n == 1) {
                        map.put("msg", "修改成功！");
                        map.put("code", 200);
                        return map;
                    }
                }
                map.put("msg", "编码已存在！");
                return map;
            }else{
                map.put("msg", "编码已存在！修改失败！");
            }


        }

        return map;
    }

    /**
     * 按主键查询
     */
    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public CostCenter selectByPrimaryKey(Integer id) {
        return costCenterService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        CostCenter costCentertmp = new CostCenter();
        costCentertmp.setCode(costCenter.getCode());
        int count = costCenterService.countAllCostCenters(costCentertmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = costCenterService.updateByPrimaryKeySelective(costCenter);
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

    @RequestMapping("/deleteCostCenter")
    @ResponseBody
    public Map<String, Object> deleteCostCenter(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += costCenterService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

