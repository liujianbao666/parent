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
     * 字典表中的部门名称
     */
    private String departmenttypename;
    /**
     * 成本中心
     */
    private Integer costCenterid;

    /**
     * 上级机构
     */
    private String parentId;

    /**
     * 成本中心
     */
    private CostCenter costCenter;
}