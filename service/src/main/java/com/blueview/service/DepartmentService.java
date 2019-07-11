package com.blueview.service;

import com.blueview.model.Department;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DepartmentService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Department> getDepartmentsSelective(Department record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Department record);

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
    int updateByPrimaryKeySelective(Department record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param department
     * @return
     */
    PageInfo<Department> getDepartmentsPageSelective(Integer start, Integer length, Department department);

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    Department selectByPrimaryKey(Integer id);
}
