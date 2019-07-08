package com.blueview.model;

import java.util.Date;

public class WorkCalendar {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 编码
     */
    private Date date;

    /**
     * 名称
     */
    private Boolean isWork;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsWork() {
        return isWork;
    }

    public void setIsWork(Boolean isWork) {
        this.isWork = isWork;
    }
}