package com.blueview.service.impl;

import com.blueview.dao.WorkCalendarMapper;
import com.blueview.model.WorkCalendar;
import com.blueview.service.WorkCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
