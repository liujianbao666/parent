package com.blueview.service.impl;

import com.blueview.dao.ProductCapacityMapper;
import com.blueview.model.ProductCapacity;
import com.blueview.service.ProductCapacityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public PageInfo<ProductCapacity> getProductCapacitysPageSelective(Integer start, Integer length, ProductCapacity productCapacity) {
        PageHelper.offsetPage(start,length);
        List<ProductCapacity> productCapacitySelective = productCapacityMapper.getProductCapacitiesSelective(productCapacity);
        PageInfo<ProductCapacity> productCapacityPageInfo = new PageInfo<ProductCapacity>(productCapacitySelective);
        return productCapacityPageInfo;
    }

    @Override
    public ProductCapacity selectByPrimaryKey(Integer id) {
        return productCapacityMapper.selectByPrimaryKey(id);
    }
}
