package com.blueview;

import com.blueview.model.Supplier;
import com.blueview.service.SupplierService;
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
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    /**
     * 查询
     * @param  supplier
     * @return
     */
    @RequestMapping(value = {"/suppliers"})
    public ResultJson listSuppliers(@RequestBody(required = true) Supplier supplier) {
        ResultJson resultJson = new ResultJson();

        List<Supplier> list = supplierService.getCompanysSelective(supplier);

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
     * @param  supplier
     * @return
     */
    @RequestMapping(value = "/supplier", method = POST, produces = "application/json")
    public ResultJson addSupplier(@RequestBody(required = true) Supplier supplier) {
        ResultJson resultJson = new ResultJson();
        int count = supplierService.insertSelective(supplier);
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
    @RequestMapping(value = "/supplier/{id}", method = DELETE, produces = "application/json")
    public ResultJson delSupplier(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = supplierService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  supplier
     * @return
     */
    @RequestMapping(value = "/supplier/{id}", method = PUT, produces = "application/json")
    public ResultJson updateSupplier(@PathVariable(name = "id") String id,@RequestBody(required = true) Supplier supplier) {
        ResultJson resultJson = new ResultJson();
        supplier.setId(Integer.valueOf(id));
        int count = supplierService.updateByPrimaryKeySelective(supplier);
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
