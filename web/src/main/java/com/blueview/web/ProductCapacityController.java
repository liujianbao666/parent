package com.blueview.web;


import com.blueview.model.ProductCapacity;
import com.blueview.service.ProductCapacityService;
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
@RequestMapping("/productCapacity")
public class ProductCapacityController {
    @Autowired
    private ProductCapacityService productCapacityService;

    @RequestMapping(value = "/manage")
    public String addProductCapacity(Model model) {
        ProductCapacity productCapacity = new ProductCapacity();
        model.addAttribute("productCapacity", productCapacity);
        return "html/productCapacity";
    }

    @RequestMapping(value = "/selectProductCapacitys")
    @ResponseBody
    public Map<String, Object> selectProductCapacitys(Integer draw, Integer start, Integer length,ProductCapacity productCapacity) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<ProductCapacity> productCapacitys = productCapacityService.getProductCapacitysPageSelective(start, length,productCapacity);
        long total = productCapacitys.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", productCapacitys.getList());
        return result;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateProductCapacity")
    @ResponseBody
    public Map<String, Object> saveOrUpdateProductCapacity(@ModelAttribute(value = "productCapacity")ProductCapacity productCapacity) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        ProductCapacity productCapacitytmp = new ProductCapacity();
        productCapacitytmp.setCode(productCapacity.getCode());
        List<ProductCapacity> productCapacitysSelective = productCapacityService.getProductCapacitiesSelective(productCapacitytmp);
        if(productCapacity.getId() == null|| "".equals(productCapacity.getId()) ){
            if (productCapacitysSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = productCapacityService.insertSelective(productCapacity);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (productCapacitysSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(productCapacitysSelective.size() == 0||(int)productCapacitysSelective.get(0).getId()==(int)productCapacity.getId()){
                    int n = productCapacityService.updateByPrimaryKeySelective(productCapacity);
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
    public ProductCapacity selectByPrimaryKey(Integer id) {
        return productCapacityService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        ProductCapacity productCapacitytmp = new ProductCapacity();
        productCapacitytmp.setCode(html.getCode());
        int count = productCapacityService.countAllProductCapacitys(productCapacitytmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = productCapacityService.updateByPrimaryKeySelective(html);
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

    @RequestMapping("/deleteProductCapacity")
    @ResponseBody
    public Map<String, Object> deleteProductCapacity(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += productCapacityService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

