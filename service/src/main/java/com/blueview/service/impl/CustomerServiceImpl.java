package com.blueview.service.impl;

import com.blueview.dao.CompanyMapper;
import com.blueview.dao.CustomerMapper;
import com.blueview.model.Company;
import com.blueview.model.Customer;
import com.blueview.service.CompanyService;
import com.blueview.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> getCustomersSelective(Customer record) {
        return customerMapper.getCustomersSelective(record);
    }

    @Override
    public int insertSelective(Customer record) {
        return customerMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Customer record) {
        return customerMapper.updateByPrimaryKeySelective(record);
    }
}
