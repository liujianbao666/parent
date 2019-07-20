package com.blueview.model;

import lombok.Data;

@Data
public class OrderClass {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 总时段
     */
    private Integer timeIntervalTotal;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}