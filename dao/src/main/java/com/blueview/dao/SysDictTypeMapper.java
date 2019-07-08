package com.blueview.dao;

import com.blueview.model.SysDictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysDictTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(Integer id);

    List<SysDictType> getSysDictTypesSelective(SysDictType record);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);
}