package com.blueview.model;

import lombok.Data;

@Data
public class Employer {
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
     * 部门编号
     */
    private String departmentId;

    /**
     * 岗位编码
     */
    private String jobId;

    /**
     * 工作
     */
    private Job job;
    /**
     * 部门
     */
    private Department department;
}