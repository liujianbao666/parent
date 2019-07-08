package com.blueview.dao;

import com.blueview.model.Employer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmployerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employer record);

    int insertSelective(Employer record);

    Employer selectByPrimaryKey(Integer id);

    List<Employer> getEmployersSelective(Employer record);

    int updateByPrimaryKeySelective(Employer record);

    int updateByPrimaryKey(Employer record);
}