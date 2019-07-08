package com.blueview.service;

import com.blueview.model.ProductCapacity;

import java.util.List;

public interface ProductCapacityService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<ProductCapacity> getProductCapacitiesSelective(ProductCapacity record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(ProductCapacity record);

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
    int updateByPrimaryKeySelective(ProductCapacity record);
}
