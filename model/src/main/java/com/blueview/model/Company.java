package com.blueview.model;

import lombok.Data;

@Data
public class Company {
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
     * 供应商编码
     */
    private String supplierId;

    /**
     * 接收方编码
     */
    private String receiveId;
    /**
     * 供应商信息
     */
    private Supplier supplier;


}