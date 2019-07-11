package com.blueview.web;


import com.blueview.model.Mould;
import com.blueview.service.MouldService;
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
@RequestMapping("/mould")
public class MouldController {
    @Autowired
    private MouldService mouldService;

    @RequestMapping(value = "/manage")
    public String addMould(Model model) {
        Mould mould = new Mould();
        model.addAttribute("mould", mould);
        return "html/mould";
    }

    @RequestMapping(value = "/selectMoulds")
    @ResponseBody
    public Map<String, Object> selectMoulds(Integer draw, Integer start, Integer length,Mould mould) {

        Map<String, Object> result = new HashMap<String, Object>();

        PageInfo<Mould> moulds = mouldService.getPageMouldsSelective(start, length,mould);
        long total = moulds.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", moulds.getList());
        return result;
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateMould")
    @ResponseBody
    public Map<String, Object> saveOrUpdateMould(@ModelAttribute(value = "mould")Mould mould) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Mould mouldtmp = new Mould();
        mouldtmp.setCode(mould.getCode());
        List<Mould> mouldsSelective = mouldService.getMouldsSelective(mouldtmp);
        if(mould.getId() == null|| "".equals(mould.getId()) ){
            if (mouldsSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = mouldService.insertSelective(mould);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (mouldsSelective.size() < 2) {
                //数据中就一条数据看是不是自己
                if(mouldsSelective.size() == 0 ||(int)mouldsSelective.get(0).getId()==(int)mould.getId()){
                    int n = mouldService.updateByPrimaryKeySelective(mould);
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
    public Mould selectByPrimaryKey(Integer id) {
        return mouldService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        Mould mouldtmp = new Mould();
        mouldtmp.setCode(html.getCode());
        int count = mouldService.countAllMoulds(mouldtmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = mouldService.updateByPrimaryKeySelective(html);
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

    @RequestMapping("/deleteMould")
    @ResponseBody
    public Map<String, Object> deleteMould(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += mouldService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

