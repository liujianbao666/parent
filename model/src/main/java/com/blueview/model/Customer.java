package com.blueview.model;

import lombok.Data;

@Data
public class Customer {
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
     * 客户类型见数据字典
     */
    private String customerType;
    /**
     *客户类型名称
     */
    private String customerTypeName;

}