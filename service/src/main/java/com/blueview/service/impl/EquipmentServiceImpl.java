package com.blueview.service.impl;

import com.blueview.dao.EquipmentMapper;
import com.blueview.model.Equipment;
import com.blueview.service.EquipmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> getEquipmentsSelective(Equipment record) {
        return equipmentMapper.getEquipmentsSelective(record);
    }

    @Override
    public int insertSelective(Equipment record) {
        return equipmentMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return equipmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Equipment record) {
        return equipmentMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public PageInfo<Equipment> getEquipmentPageSelective(Integer start, Integer length, Equipment equipment) {
        PageHelper.offsetPage(start,length);
        List<Equipment> equipmentSelective = equipmentMapper.getEquipmentsSelective(equipment);
        PageInfo<Equipment> equipmentPageInfo = new PageInfo<Equipment>(equipmentSelective);
        return equipmentPageInfo;
    }

    @Override
    public Equipment selectByPrimaryKey(Integer id) {
        return equipmentMapper.selectByPrimaryKey(id);
    }
}
