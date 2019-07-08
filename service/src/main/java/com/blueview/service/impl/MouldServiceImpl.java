package com.blueview.service.impl;

import com.blueview.dao.MouldMapper;
import com.blueview.model.Mould;
import com.blueview.service.MouldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MouldServiceImpl implements MouldService {
    @Autowired
    MouldMapper mouldMapper;

    @Override
    public List<Mould> getMouldsSelective(Mould record) {
        return mouldMapper.getMouldsSelective(record);
    }

    @Override
    public int insertSelective(Mould record) {
        return mouldMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mouldMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Mould record) {
        return mouldMapper.updateByPrimaryKeySelective(record);
    }
}
