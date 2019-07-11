package com.blueview.service;

import com.blueview.model.Supplier;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SupplierService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Supplier> getSuppliersSelective(Supplier record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Supplier record);

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
    int updateByPrimaryKeySelective(Supplier record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    Supplier selectByPrimaryKey(Integer id);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param supplier
     * @return
     */
    PageInfo<Supplier> getSuppliersPageSelective(Integer start, Integer length, Supplier supplier);
}
