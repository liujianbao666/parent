package com.blueview.service;

import com.blueview.model.CostCenter;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CostCenterService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<CostCenter> getCostCentersSelective(CostCenter record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(CostCenter record);

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
    int updateByPrimaryKeySelective(CostCenter record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param costCenter
     * @return
     */
    PageInfo<CostCenter> getCostCentersPageSelective(Integer start, Integer length, CostCenter costCenter);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    CostCenter selectByPrimaryKey(Integer id);
}
