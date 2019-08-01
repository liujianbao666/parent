package com.blueview.dao;

import com.blueview.model.TableManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableManageMapper {
    int insert(TableManage record);

    int insertSelective(TableManage record);

    List<TableManage> getTableManagerSelective(TableManage record);
}