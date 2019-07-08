package com.blueview.dao;

import com.blueview.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    List<Department> getDepartmentsSelective(Department record);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}