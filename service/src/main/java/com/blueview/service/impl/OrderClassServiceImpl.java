package com.blueview.service.impl;

import com.blueview.dao.OrderClassMapper;
import com.blueview.model.OrderClass;
import com.blueview.service.OrderClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
