package com.blueview.service.impl;

import com.blueview.dao.CustomerMapper;
import com.blueview.model.Customer;
import com.blueview.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public PageInfo<Customer> getCustomersPageSelective(Integer start, Integer length, Customer customer) {
        PageHelper.offsetPage(start,length);
        List<Customer> customerSelective = customerMapper.getCustomersSelective(customer);
        PageInfo<Customer> customerPageInfo = new PageInfo<Customer>(customerSelective);
        return customerPageInfo;
    }

    @Override
    public Customer selectByPrimaryKey(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }
}
