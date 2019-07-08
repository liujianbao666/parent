package com.blueview.service;

import com.blueview.model.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 根据字段查询列表
     * @param company
     * @return
     */
    List<Company> getCompanysSelective(Company company);

    /**
     * 添加,code与名称不能为空
     * @param company
     * @return
     */
    int insertSelective(Company company);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据id更新公司属性
     * @param company
     * @return
     */
    int updateByPrimaryKeySelective(Company company);
}
