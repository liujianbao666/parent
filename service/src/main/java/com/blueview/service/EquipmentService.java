package com.blueview.service;

import com.blueview.model.Equipment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface EquipmentService {
    /**
     * 根据字段查询列表
     * @param record
     * @return
     */
    List<Equipment> getEquipmentsSelective(Equipment record);

    /**
     * 添加,code与名称不能为空
     * @param record
     * @return
     */
    int insertSelective(Equipment record);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据id更新公司属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Equipment record);

    /**
     * 分页查询
     * @param start
     * @param length
     * @param equipment
     * @return
     */
    PageInfo<Equipment> getEquipmentPageSelective(Integer start, Integer length, Equipment equipment);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    Equipment selectByPrimaryKey(Integer id);
}
