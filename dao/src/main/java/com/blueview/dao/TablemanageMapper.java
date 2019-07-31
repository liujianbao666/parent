package com.blueview.dao;

import com.blueview.model.Tablemanage;

public interface TablemanageMapper {
    int insert(Tablemanage record);

    int insertSelective(Tablemanage record);
}