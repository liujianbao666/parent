package com.blueview.dao;

import com.blueview.model.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    List<Company> getCompanysSelective(Company record);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

}