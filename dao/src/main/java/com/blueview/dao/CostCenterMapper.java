package com.blueview.dao;

import com.blueview.model.CostCenter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CostCenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CostCenter record);

    int insertSelective(CostCenter record);

    CostCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CostCenter record);

    int updateByPrimaryKey(CostCenter record);

    List<CostCenter> getCostCentersSelective(CostCenter record);
}