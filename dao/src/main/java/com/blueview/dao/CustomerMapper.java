package com.blueview.dao;

import com.blueview.model.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    List<Customer> getCustomersSelective(Customer record);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}