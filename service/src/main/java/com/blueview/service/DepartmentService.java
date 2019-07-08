package com.blueview.service;

import com.blueview.model.Department;

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
}
