package com.blueview.dao;

import com.blueview.model.Mould;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MouldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mould record);

    int insertSelective(Mould record);

    Mould selectByPrimaryKey(Integer id);

    List<Mould> getMouldsSelective(Mould record);

    int updateByPrimaryKeySelective(Mould record);

    int updateByPrimaryKey(Mould record);
}