package com.blueview.model;

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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public Integer getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(Integer workAmount) {
        this.workAmount = workAmount;
    }

    public Float getWorkHour() {
        return workHour;
    }

    public void setWorkHour(Float workHour) {
        this.workHour = workHour;
    }

    public Float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Float efficiency) {
        this.efficiency = efficiency;
    }
}