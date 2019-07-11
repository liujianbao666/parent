package com.blueview.web;


import com.blueview.model.SysDictType;
import com.blueview.service.SysDictTypeService;
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
@RequestMapping("/sysDictType")
public class SysDictTypeController {
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @RequestMapping(value = "/manage")
    public String addSysDictType(Model model) {
        SysDictType sysDictType = new SysDictType();
        model.addAttribute("sysDictType", sysDictType);
        return "html/sysDictType";
    }

    @RequestMapping(value = "/selectSysDictTypes")
    @ResponseBody
    public List<SysDictType> selectSysDictTypes(SysDictType sysDictType) {
        return sysDictTypeService.getSysDictTypesSelective(sysDictType);
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateSysDictType")
    @ResponseBody
    public Map<String, Object> saveOrUpdateSysDictType(@ModelAttribute(value = "sysDictType")SysDictType sysDictType) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        SysDictType sysDictTypetmp = new SysDictType();
        sysDictTypetmp.setCode(sysDictType.getCode());
        List<SysDictType> sysDictTypesSelective = sysDictTypeService.getSysDictTypesSelective(sysDictTypetmp);
        if(sysDictType.getId() == null|| "".equals(sysDictType.getId()) ){
            if (sysDictTypesSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = sysDictTypeService.insertSelective(sysDictType);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (sysDictTypesSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(sysDictTypesSelective.size() == 0 ||(int)sysDictTypesSelective.get(0).getId()==(int)sysDictType.getId()){
                    int n = sysDictTypeService.updateByPrimaryKeySelective(sysDictType);
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
    public SysDictType selectByPrimaryKey(Integer id) {
        return sysDictTypeService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        SysDictType sysDictTypetmp = new SysDictType();
        sysDictTypetmp.setCode(html.getCode());
        int count = sysDictTypeService.countAllSysDictTypes(sysDictTypetmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = sysDictTypeService.updateByPrimaryKeySelective(html);
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

    @RequestMapping("/deleteSysDictType")
    @ResponseBody
    public Map<String, Object> deleteSysDictType(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += sysDictTypeService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

