package com.blueview.model;

import lombok.Data;

@Data
public class ProductCapacity {
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
     * 资源类型见数据字典
     */
    private String resourceType;

    /**
     * 标准产能件数
     */
    private Integer workAmount;

    /**
     * 标准产能工时
     */
    private Float workHour;

    /**
     * 效率
     */
    private Float efficiency;
    /**
     * 资源类型
     */
    private String resourceTypeName;
}