package com.blueview.model;

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
    private String costCenterid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getCostCenterid() {
        return costCenterid;
    }

    public void setCostCenterid(String costCenterid) {
        this.costCenterid = costCenterid == null ? null : costCenterid.trim();
    }
}