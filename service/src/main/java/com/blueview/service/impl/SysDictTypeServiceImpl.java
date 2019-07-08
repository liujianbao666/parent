package com.blueview.service.impl;

import com.blueview.dao.SysDictTypeMapper;
import com.blueview.model.SysDictType;
import com.blueview.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Autowired
    SysDictTypeMapper sysDictTypeMapper;

    @Override
    public List<SysDictType> getSysDictTypesSelective(SysDictType record) {
        return sysDictTypeMapper.getSysDictTypesSelective(record);
    }

    @Override
    public int insertSelective(SysDictType record) {
        return sysDictTypeMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysDictTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDictType record) {
        return sysDictTypeMapper.updateByPrimaryKeySelective(record);
    }
}
