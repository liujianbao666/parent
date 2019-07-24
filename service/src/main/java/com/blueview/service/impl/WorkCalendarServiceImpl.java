package com.blueview.service.impl;

import com.blueview.dao.WorkCalendarMapper;
import com.blueview.model.WorkCalendar;
import com.blueview.service.WorkCalendarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkCalendarServiceImpl implements WorkCalendarService {
    @Autowired
    WorkCalendarMapper workCalendarMapper;

    @Override
    public List<WorkCalendar> getWorkCalendarsSelective(WorkCalendar record) {
        return workCalendarMapper.getWorkCalendarsSelective(record);
    }

    @Override
    public int insertSelective(WorkCalendar record) {
        return workCalendarMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workCalendarMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkCalendar record) {
        return workCalendarMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public WorkCalendar selectByPrimaryKey(Integer id) {
        return workCalendarMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WorkCalendar> selectByDate(Date date) {
        WorkCalendar record = new WorkCalendar();
        record.setDate(date);
        return  workCalendarMapper.getWorkCalendarsSelective(record);
    }

    @Override
    public PageInfo<WorkCalendar> getWorkCalendarPageSelective(Integer start, Integer length, WorkCalendar workCalendar) {
        PageHelper.offsetPage(start,length);
        List<WorkCalendar> customerSelective = workCalendarMapper.getWorkCalendarsSelective(workCalendar);
        PageInfo<WorkCalendar> customerPageInfo = new PageInfo<WorkCalendar>(customerSelective);
        return customerPageInfo;
    }

}
