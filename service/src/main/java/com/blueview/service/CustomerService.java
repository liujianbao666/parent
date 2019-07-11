package com.blueview.service;

import com.blueview.model.Customer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CustomerService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Customer> getCustomersSelective(Customer record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Customer record);

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
    int updateByPrimaryKeySelective(Customer record);

    PageInfo<Customer> getCustomersPageSelective(Integer start, Integer length, Customer customer);

    Customer selectByPrimaryKey(Integer id);
}
