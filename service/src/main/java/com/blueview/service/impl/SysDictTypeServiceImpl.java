package com.blueview.service.impl;

import com.blueview.dao.SysDictTypeMapper;
import com.blueview.model.SysDictType;
import com.blueview.service.SysDictTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public PageInfo<SysDictType> getSysDictTypesPageSelective(Integer start, Integer length, SysDictType sysDictType) {
        PageHelper.offsetPage(start,length);
        List<SysDictType> sysDictTypeSelective = sysDictTypeMapper.getSysDictTypesSelective(sysDictType);
        PageInfo<SysDictType> sysDictTypePageInfo = new PageInfo<SysDictType>(sysDictTypeSelective);
        return sysDictTypePageInfo;
    }

    @Override
    public SysDictType selectByPrimaryKey(Integer id) {
        return sysDictTypeMapper.selectByPrimaryKey(id);
    }
}
