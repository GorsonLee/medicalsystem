package com.starter.medicaldao.entity;

import java.util.Date;

public class Consult {
    private String id;

    private String userId;

    private String doctorId;

    private String symptoms;

    private String description;

    private String recordUrls;

    private String doctorReply;

    private Integer state;

    private Date replyTime;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms == null ? null : symptoms.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRecordUrls() {
        return recordUrls;
    }

    public void setRecordUrls(String recordUrls) {
        this.recordUrls = recordUrls == null ? null : recordUrls.trim();
    }

    public String getDoctorReply() {
        return doctorReply;
    }

    public void setDoctorReply(String doctorReply) {
        this.doctorReply = doctorReply == null ? null : doctorReply.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
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