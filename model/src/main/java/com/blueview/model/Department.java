package com.blueview.model;

import lombok.Data;

@Data
public class Department {
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
     * 类型见数据字典
     */
    private String departmentType;

    /**
     * 成本中心
     */
    private Integer costCenterid;

    /**
     * 上级机构
     */
    private String parentId;


}