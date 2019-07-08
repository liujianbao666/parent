package com.blueview.dao;

import com.blueview.model.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    List<Supplier> getSuppliersSelective(Supplier record);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}