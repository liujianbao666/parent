package com.blueview.model;

import lombok.Data;

@Data
public class TableManage {
    /**
     * 主表
     */
    private String mainTable;

    /**
     * 是否同步
     */
    private Boolean isSynchronized;

    /**
     * 副表名称
     */
    private String sideTable;

    /**
     * 归属系统
     */
    private String system;

}