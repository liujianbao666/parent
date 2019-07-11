package com.blueview.service.impl;

import com.blueview.dao.CostCenterMapper;
import com.blueview.model.CostCenter;
import com.blueview.service.CostCenterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CostCenterServiceImpl implements CostCenterService {
    @Autowired
    CostCenterMapper costCenterMapper;

    @Override
    public List<CostCenter> getCostCentersSelective(CostCenter record) {
        return costCenterMapper.getCostCentersSelective(record);
    }

    @Override
    public int insertSelective(CostCenter record) {
        return costCenterMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return costCenterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CostCenter record) {
        return costCenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<CostCenter> getCostCentersPageSelective(Integer start, Integer length, CostCenter costCenter) {
        PageHelper.offsetPage(start,length);
        List<CostCenter> costCentersSelective = costCenterMapper.getCostCentersSelective(costCenter);
        PageInfo<CostCenter> costCenterPageInfo = new PageInfo<>(costCentersSelective);
        return costCenterPageInfo;
    }

    @Override
    public CostCenter selectByPrimaryKey(Integer id) {
        return costCenterMapper.selectByPrimaryKey(id);
    }
}
