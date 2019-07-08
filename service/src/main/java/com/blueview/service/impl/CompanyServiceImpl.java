package com.blueview.service.impl;

import com.blueview.dao.CompanyMapper;
import com.blueview.model.Company;
import com.blueview.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public List<Company> getCompanysSelective(Company company) {
        return companyMapper.getCompanysSelective(company);
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
