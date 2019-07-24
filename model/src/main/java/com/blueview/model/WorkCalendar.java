package com.blueview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class WorkCalendar {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 编码
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    /**
     * 是否工作
     */
    private Boolean isWork;

}