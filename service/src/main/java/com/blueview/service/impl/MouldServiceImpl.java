package com.blueview.service.impl;

import com.blueview.dao.MouldMapper;
import com.blueview.model.Mould;
import com.blueview.service.MouldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public PageInfo<Mould> getPageMouldsSelective(Integer start, Integer length, Mould mould) {
        PageHelper.offsetPage(start,length);
        List<Mould> mouldSelective = mouldMapper.getMouldsSelective(mould);
        PageInfo<Mould> mouldPageInfo = new PageInfo<Mould>(mouldSelective);
        return mouldPageInfo;
    }

    @Override
    public Mould selectByPrimaryKey(Integer id) {
        return mouldMapper.selectByPrimaryKey(id);
    }
}
