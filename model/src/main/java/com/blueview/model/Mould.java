package com.blueview.model;

import lombok.Data;

@Data
public class Mould {
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
     * 所属设备编码
     */
    private String equipmentId;

    /**
     * 所属设备名称
     */
    private String  equipmentName;

}