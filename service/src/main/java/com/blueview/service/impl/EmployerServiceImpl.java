package com.blueview.service.impl;

import com.blueview.dao.EmployerMapper;
import com.blueview.model.Employer;
import com.blueview.service.EmployerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public PageInfo<Employer> getEmployersPageSelective(Integer start, Integer length, Employer employer) {
        PageHelper.offsetPage(start,length);
        List<Employer> employerSelective = employerMapper.getEmployersSelective(employer);
        PageInfo<Employer> employerPageInfo = new PageInfo<Employer>(employerSelective);
        return employerPageInfo;
    }

    @Override
    public Employer selectByPrimaryKey(Integer id) {
        return employerMapper.selectByPrimaryKey(id);
    }
}
