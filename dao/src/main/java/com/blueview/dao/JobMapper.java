package com.blueview.dao;

import com.blueview.model.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Integer id);

    List<Job> getJobsSelective(Job record);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}