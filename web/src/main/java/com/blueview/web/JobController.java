package com.blueview.web;


import com.blueview.model.Job;
import com.blueview.service.JobService;
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
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/manage")
    public String addJob(Model model) {
        Job job = new Job();
        model.addAttribute("job", job);
        return "html/job";
    }

    @RequestMapping(value = "/selectJobs")
    @ResponseBody
    public Map<String, Object> selectJobs(Integer draw, Integer start, Integer length, Job job) {

        Map<String, Object> result = new HashMap<String, Object>();
        PageInfo<Job> jobs = jobService.getJobsPageSelective(start, length,job);
        long total = jobs.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", total);
        result.put("data", jobs.getList());
        return result;
    }

    @RequestMapping(value = "/selectAllJobs")
    @ResponseBody
    public List<Job> selectAllJobs(@RequestParam(required = false) Job job) {
        return jobService.getJobsSelective(job);
    }

    /**
     * 添加
     */
    @RequestMapping("/saveOrUpdateJob")
    @ResponseBody
    public Map<String, Object> saveOrUpdateJob(@ModelAttribute(value = "job")Job job) {
        Map<String, Object> map = new HashMap<>();
        //查询已存在
        Job jobtmp = new Job();
        jobtmp.setCode(job.getCode());
        List<Job> jobsSelective = jobService.getJobsSelective(jobtmp);
        if(job.getId() == null|| "".equals(job.getId()) ){
            if (jobsSelective.size() > 0) {
                map.put("msg", "编码已存在！");
                return map;
            }
            int n = jobService.insertSelective(job);
            if (n == 1) {
                map.put("msg", "添加成功！");
                map.put("code", 200);
                return map;
            }
            map.put("msg", "添加失败！");
        }else{//如果不为空时为修改
            if (jobsSelective.size()< 2) {
                //数据中就一条数据看是不是自己
                if(jobsSelective.size() ==0 || (int)jobsSelective.get(0).getId()==(int)job.getId()){
                    int n = jobService.updateByPrimaryKeySelective(job);
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
    public Job selectByPrimaryKey(Integer id) {
        return jobService.selectByPrimaryKey(id);
      /*  Map<String, Object> map = new HashMap<>();
        Job jobtmp = new Job();
        jobtmp.setCode(html.getCode());
        int count = jobService.countAllJobs(jobtmp);
        if (count > 0) {
            map.put("msg", "编码已存在！");
            return map;
        }
        int n = jobService.updateByPrimaryKeySelective(html);
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

    @RequestMapping("/deleteJob")
    @ResponseBody
    public Map<String, Object> deleteJob(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += jobService.deleteByPrimaryKey(Integer.valueOf(id));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        return map;
    }
}

