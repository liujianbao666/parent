package com.blueview.dao;

import com.blueview.model.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    List<Equipment> getEquipmentsSelective(Equipment record);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
}