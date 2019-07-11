package com.blueview.service.impl;

import com.blueview.dao.OrderClassMapper;
import com.blueview.model.OrderClass;
import com.blueview.service.OrderClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderClassServiceImpl implements OrderClassService {
    @Autowired
    OrderClassMapper orderClassMapper;

    @Override
    public List<OrderClass> getOrderClassesSelective(OrderClass record) {
        return orderClassMapper.getOrderClassesSelective(record);
    }

    @Override
    public int insertSelective(OrderClass record) {
        return orderClassMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderClassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderClass record) {
        return orderClassMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<OrderClass> getOrderClasssPageSelective(Integer start, Integer length, OrderClass orderClass) {
        PageHelper.offsetPage(start,length);
        List<OrderClass> orderClassSelective = orderClassMapper.getOrderClassesSelective(orderClass);
        PageInfo<OrderClass> orderClassPageInfo = new PageInfo<OrderClass>(orderClassSelective);
        return orderClassPageInfo;
    }

    @Override
    public OrderClass selectByPrimaryKey(Integer id) {
        return orderClassMapper.selectByPrimaryKey(id);
    }
}
