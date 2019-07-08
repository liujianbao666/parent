package com.blueview;

import com.blueview.model.Customer;
import com.blueview.service.CustomerService;
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
public class CustomerApiController {
    @Autowired
    CustomerService customerService;

    /**
     * 查询
     * @param  Customer
     * @return
     */
    @RequestMapping(value = {"/customers"})
    public ResultJson listCustomers(@RequestBody(required = true) Customer Customer) {
        ResultJson resultJson = new ResultJson();

        List<Customer> list = customerService.getCustomersSelective(Customer);

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
     * @param  Customer
     * @return
     */
    @RequestMapping(value = "/customer", method = POST, produces = "application/json")
    public ResultJson addCustomer(@RequestBody(required = true) Customer Customer) {
        ResultJson resultJson = new ResultJson();
        int count = customerService.insertSelective(Customer);
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
    @RequestMapping(value = "/customer/{id}", method = DELETE, produces = "application/json")
    public ResultJson delCustomer(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = customerService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  Customer
     * @return
     */
    @RequestMapping(value = "/customer/{id}", method = PUT, produces = "application/json")
    public ResultJson updateCustomer(@PathVariable(name = "id") String id,@RequestBody(required = true) Customer Customer) {
        ResultJson resultJson = new ResultJson();
        Customer.setId(Integer.valueOf(id));
        int count = customerService.updateByPrimaryKeySelective(Customer);
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
