package com.blueview.dao;

import com.blueview.model.OrderClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderClass record);

    int insertSelective(OrderClass record);

    OrderClass selectByPrimaryKey(Integer id);

    List<OrderClass> getOrderClassesSelective(OrderClass record);

    int updateByPrimaryKeySelective(OrderClass record);

    int updateByPrimaryKey(OrderClass record);
}