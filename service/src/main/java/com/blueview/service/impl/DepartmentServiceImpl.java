package com.blueview.service.impl;

import com.blueview.dao.DepartmentMapper;
import com.blueview.model.Department;
import com.blueview.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentsSelective(Department record) {
        return departmentMapper.getDepartmentsSelective(record);
    }

    @Override
    public int insertSelective(Department record) {
        return departmentMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Department record) {
        return departmentMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public PageInfo<Department> getDepartmentsPageSelective(Integer start, Integer length, Department department) {
        PageHelper.offsetPage(start,length);
        List<Department> departmentSelective = departmentMapper.getDepartmentsSelective(department);
        PageInfo<Department> departmentPageInfo = new PageInfo<Department>(departmentSelective);
        return departmentPageInfo;
    }

    @Override
    public Department selectByPrimaryKey(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
}
