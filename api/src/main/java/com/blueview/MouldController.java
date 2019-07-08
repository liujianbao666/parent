package com.blueview;

import com.blueview.model.Mould;
import com.blueview.service.MouldService;
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
public class MouldController {
    @Autowired
    MouldService mouldService;

    /**
     * 查询
     * @param  mould
     * @return
     */
    @RequestMapping(value = {"/moulds"})
    public ResultJson listMoulds(@RequestBody(required = true) Mould mould) {
        ResultJson resultJson = new ResultJson();

        List<Mould> list = mouldService.getMouldsSelective(mould);

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
     * @param  mould
     * @return
     */
    @RequestMapping(value = "/mould", method = POST, produces = "application/json")
    public ResultJson addMould(@RequestBody(required = true) Mould mould) {
        ResultJson resultJson = new ResultJson();
        int count = mouldService.insertSelective(mould);
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
    @RequestMapping(value = "/mould/{id}", method = DELETE, produces = "application/json")
    public ResultJson delMould(@PathVariable(required = true) String id) {
        ResultJson resultJson = new ResultJson();
        int count = mouldService.deleteByPrimaryKey(Integer.valueOf(id));
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
     * @param  mould
     * @return
     */
    @RequestMapping(value = "/mould/{id}", method = PUT, produces = "application/json")
    public ResultJson updateMould(@PathVariable(name = "id") String id,@RequestBody(required = true) Mould mould) {
        ResultJson resultJson = new ResultJson();
        mould.setId(Integer.valueOf(id));
        int count = mouldService.updateByPrimaryKeySelective(mould);
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
