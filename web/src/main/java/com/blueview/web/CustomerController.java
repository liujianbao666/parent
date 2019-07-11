package com.blueview.web;


import com.blueview.model.Customer;
import com.blueview.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/manage")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "html/customer";
    }

    @RequestMapping(value = "/selectCustomers")
    @ResponseBody
    public Map<String, Object> selectCustomers(Integer draw, Integer start, Integer length, Customer customer) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<Customer> customers = customerService.getCustomersPageSelective(start, length,customer);
        long total = customers.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", customers.getList());
        return result;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateCustomer")
    @ResponseBody
    public Map<String, Object> saveOrUpdateCustomer(@ModelAttribute(value = "customer")Customer customer) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Customer customertmp = new Customer();
        customertmp.setCode(customer.getCode());
        List<Customer> customersSelective = customerService.getCustomersSelective(customertmp);
        if(customer.getId() == null|| "".equals(customer.getId()) ){
            if (customersSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = customerService.insertSelective(customer);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (customersSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(customersSelective.size() == 0 ||(int)customersSelective.get(0).getId()==(int)customer.getId()){
                    int n = customerService.updateByPrimaryKeySelective(customer);
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
    public Customer selectByPrimaryKey(Integer id) {
        return customerService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        Customer customertmp = new Customer();
        customertmp.setCode(customer.getCode());
        int count = customerService.countAllCustomers(customertmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = customerService.updateByPrimaryKeySelective(customer);
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

    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public Map<String, Object> deleteCustomer(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += customerService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

