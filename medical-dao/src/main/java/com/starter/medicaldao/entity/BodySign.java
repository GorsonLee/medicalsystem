package com.starter.medicaldao.entity;

import java.util.Date;

public class BodySign {
    private String id;

    private String userId;

    private Integer breathRate;

    private Integer systolic;

    private Integer diastolic;

    private Integer heartRate;

    private Integer spo2;

    private Integer sleepTime;

    private Integer bg;

    private Integer temperature;

    private Integer uricAcid;

    private Integer bloodFat;

    private Integer weight;

    private Integer waist;

    private Integer hip;

    private String description;

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

    public Integer getBreathRate() {
        return breathRate;
    }

    public void setBreathRate(Integer breathRate) {
        this.breathRate = breathRate;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Integer getBg() {
        return bg;
    }

    public void setBg(Integer bg) {
        this.bg = bg;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getUricAcid() {
        return uricAcid;
    }

    public void setUricAcid(Integer uricAcid) {
        this.uricAcid = uricAcid;
    }

    public Integer getBloodFat() {
        return bloodFat;
    }

    public void setBloodFat(Integer bloodFat) {
        this.bloodFat = bloodFat;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWaist() {
        return waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getHip() {
        return hip;
    }

    public void setHip(Integer hip) {
        this.hip = hip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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