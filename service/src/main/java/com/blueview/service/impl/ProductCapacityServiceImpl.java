package com.blueview.service.impl;

import com.blueview.dao.ProductCapacityMapper;
import com.blueview.model.ProductCapacity;
import com.blueview.service.ProductCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCapacityServiceImpl implements ProductCapacityService {
    @Autowired
    ProductCapacityMapper productCapacityMapper;

    @Override
    public List<ProductCapacity> getProductCapacitiesSelective(ProductCapacity record) {
        return productCapacityMapper.getProductCapacitiesSelective(record);
    }

    @Override
    public int insertSelective(ProductCapacity record) {
        return productCapacityMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productCapacityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductCapacity record) {
        return productCapacityMapper.updateByPrimaryKeySelective(record);
    }
}
