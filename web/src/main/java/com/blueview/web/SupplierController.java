package com.blueview.web;


import com.blueview.model.Supplier;
import com.blueview.service.SupplierService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = "/manage")
    public String addSupplier(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "html/supplier";
    }

    @RequestMapping(value = "/selectSuppliers")
    @ResponseBody
    public Map<String, Object> selectSuppliers(Integer draw, Integer start, Integer length, Supplier supplier) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<Supplier> suppliers = supplierService.getSuppliersPageSelective(start, length,supplier);
        long total = suppliers.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", suppliers.getList());
        return result;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateSupplier")
    @ResponseBody
    public Map<String, Object> saveOrUpdateSupplier(@ModelAttribute(value = "supplier")Supplier supplier) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Supplier suppliertmp = new Supplier();
        suppliertmp.setCode(supplier.getCode());
        List<Supplier> suppliersSelective = supplierService.getSuppliersSelective(suppliertmp);
        if(supplier.getId() == null|| "".equals(supplier.getId()) ){
            if (suppliersSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = supplierService.insertSelective(supplier);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (suppliersSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(suppliersSelective.size() ==0 || (int)suppliersSelective.get(0).getId()==(int)supplier.getId()){
                    int n = supplierService.updateByPrimaryKeySelective(supplier);
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
    public Supplier selectByPrimaryKey(Integer id) {
        return supplierService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        Supplier suppliertmp = new Supplier();
        suppliertmp.setCode(html.getCode());
        int count = supplierService.countAllSuppliers(suppliertmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = supplierService.updateByPrimaryKeySelective(html);
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

    @RequestMapping("/deleteSupplier")
    @ResponseBody
    public Map<String, Object> deleteSupplier(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += supplierService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }

    /**
     * 查询所有的供应商
     * @param supplier
     * @return
     */
    @RequestMapping(value = "/selectAllsupplier")
    @ResponseBody
    public List<Supplier> selectAllsupplier(@RequestParam(required = false) Supplier supplier) {
        return supplierService.getSuppliersSelective(supplier);
    }
}

