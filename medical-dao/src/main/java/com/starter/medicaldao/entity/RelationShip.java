package com.starter.medicaldao.entity;

import java.util.Date;

public class RelationShip {
    private String id;

    private String youngerId;

    private String elderId;

    private Integer type;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getYoungerId() {
        return youngerId;
    }

    public void setYoungerId(String youngerId) {
        this.youngerId = youngerId == null ? null : youngerId.trim();
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId == null ? null : elderId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}