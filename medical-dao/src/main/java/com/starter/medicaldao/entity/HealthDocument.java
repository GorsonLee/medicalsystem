package com.starter.medicaldao.entity;

import java.util.Date;

public class HealthDocument {
    private String id;

    private String userId;

    private String basicSign;

    private String lifestyle;

    private String organ;

    private String healthHistory;

    private String healthRecord;

    private String healthReport;

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

    public String getBasicSign() {
        return basicSign;
    }

    public void setBasicSign(String basicSign) {
        this.basicSign = basicSign == null ? null : basicSign.trim();
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle == null ? null : lifestyle.trim();
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ == null ? null : organ.trim();
    }

    public String getHealthHistory() {
        return healthHistory;
    }

    public void setHealthHistory(String healthHistory) {
        this.healthHistory = healthHistory == null ? null : healthHistory.trim();
    }

    public String getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(String healthRecord) {
        this.healthRecord = healthRecord == null ? null : healthRecord.trim();
    }

    public String getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(String healthReport) {
        this.healthReport = healthReport == null ? null : healthReport.trim();
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