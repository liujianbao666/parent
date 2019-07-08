package com.blueview.dao;

import com.blueview.model.ProductCapacity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductCapacityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCapacity record);

    int insertSelective(ProductCapacity record);

    ProductCapacity selectByPrimaryKey(Integer id);

    List<ProductCapacity> getProductCapacitiesSelective(ProductCapacity record);

    int updateByPrimaryKeySelective(ProductCapacity record);

    int updateByPrimaryKey(ProductCapacity record);
}