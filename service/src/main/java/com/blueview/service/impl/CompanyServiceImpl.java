package com.blueview.service.impl;

import com.blueview.dao.CompanyMapper;
import com.blueview.model.Company;
import com.blueview.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public List<Company> getCompanysSelective(Company company) {
        return companyMapper.getCompanysSelective(company);
    }

    @Override
    public PageInfo<Company> getPageCompanysSelective(Integer start, Integer length, Company company) {
        PageHelper.offsetPage(start, length);
        List<Company> companies = companyMapper.getCompanysSelective(company);
        //获取分页查询后的数据
        PageInfo<Company> pageInfo = new PageInfo<>(companies);
        return pageInfo;
    }
    @Override
    public Company selectByPrimaryKey(Integer id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Company company) {
        return companyMapper.insertSelective(company);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return companyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Company company) {
        return companyMapper.updateByPrimaryKeySelective(company);
    }

}
