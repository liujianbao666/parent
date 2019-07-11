package com.blueview.service;


import com.blueview.model.Job;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface JobService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Job> getJobsSelective(Job record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Job record);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据id更新公司属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Job record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param job
     * @return
     */
    PageInfo<Job> getJobsPageSelective(Integer start, Integer length, Job job);

    /**
     * 按照主键查询
     * @param id
     * @return
     */
    Job selectByPrimaryKey(Integer id);
}
