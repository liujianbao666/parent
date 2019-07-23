package com.blueview.web;


import com.blueview.model.OrderClass;
import com.blueview.service.OrderClassService;
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
@RequestMapping("/orderClass")
public class OrderClassController {
    @Autowired
    private OrderClassService orderClassService;

    @RequestMapping(value = "/manage")
    public String addOrderClass(Model model) {
        OrderClass orderClass = new OrderClass();
        model.addAttribute("orderClass", orderClass);
        return "html/orderClass";
    }

    @RequestMapping(value = "/selectOrderClasss")
    @ResponseBody
    public Map<String, Object> selectOrderClasss(Integer draw, Integer start, Integer length, OrderClass orderClass) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<OrderClass> orderClasss = orderClassService.getOrderClasssPageSelective(start, length,orderClass);
        long total = orderClasss.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", orderClasss.getList());
        return result;
    }
    @RequestMapping(value = "/selectAllOrderClasss")
    @ResponseBody
    public List<OrderClass> selectAllOrderClasss(Integer draw, Integer start, Integer length, OrderClass orderClass) {

        List<OrderClass> orderClasss = orderClassService.getOrderClassesSelective(orderClass);

        return orderClasss;
    }
    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateOrderClass")
    @ResponseBody
    public Map<String, Object> saveOrUpdateOrderClass(@ModelAttribute(value = "orderClass")OrderClass orderClass) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        OrderClass orderClasstmp = new OrderClass();
        orderClasstmp.setCode(orderClass.getCode());
        List<OrderClass> orderClasssSelective = orderClassService.getOrderClassesSelective(orderClasstmp);
        if(orderClass.getId() == null|| "".equals(orderClass.getId()) ){
            if (orderClasssSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = orderClassService.insertSelective(orderClass);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (orderClasssSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(orderClasssSelective.size() ==0 ||(int)orderClasssSelective.get(0).getId()==(int)orderClass.getId()){
                    int n = orderClassService.updateByPrimaryKeySelective(orderClass);
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
    public OrderClass selectByPrimaryKey(Integer id) {
        return orderClassService.selectByPrimaryKey(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */

    @RequestMapping("/deleteOrderClass")
    @ResponseBody
    public Map<String, Object> deleteOrderClass(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += orderClassService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

