package com.blueview.dao;

import com.blueview.model.WorkCalendar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface WorkCalendarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkCalendar record);

    int insertSelective(WorkCalendar record);

    WorkCalendar selectByPrimaryKey(Integer id);

    List<WorkCalendar> getWorkCalendarsSelective(WorkCalendar record);

    int updateByPrimaryKeySelective(WorkCalendar record);

    int updateByPrimaryKey(WorkCalendar record);
}