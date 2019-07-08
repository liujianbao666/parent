package com.blueview.service.impl;

import com.blueview.dao.CostCenterMapper;
import com.blueview.model.CostCenter;
import com.blueview.service.CostCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
