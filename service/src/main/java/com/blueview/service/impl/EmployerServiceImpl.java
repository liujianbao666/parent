package com.blueview.service.impl;

import com.blueview.dao.EmployerMapper;
import com.blueview.model.Employer;
import com.blueview.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    EmployerMapper employerMapper;

    @Override
    public List<Employer> getEmployersSelective(Employer record) {
        return employerMapper.getEmployersSelective(record);
    }

    @Override
    public int insertSelective(Employer record) {
        return employerMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return employerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Employer record) {
        return employerMapper.updateByPrimaryKeySelective(record);
    }
}
