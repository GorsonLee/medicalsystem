package com.starter.medicaldao.entity;

import java.util.Date;

public class BodySignAlarm {
    private String id;

    private String doctorId;

    private Integer breathRateMax;

    private Integer breathRateMin;

    private Integer systolicMax;

    private Integer systolicMin;

    private Integer diastolicMax;

    private Integer diastolicMin;

    private Integer heartRateMax;

    private Integer heartRateMin;

    private Integer spo2Max;

    private Integer spo2Min;

    private Integer sleepTimeMax;

    private Integer sleepTimeMin;

    private Integer bgMax;

    private Integer bgMin;

    private Integer temperatureMax;

    private Integer temperatureMin;

    private Integer uricAcidMax;

    private Integer uricAcidMin;

    private Integer bloodFatMax;

    private Integer bloodFatMin;

    private Integer weightMax;

    private Integer weightMin;

    private Integer waistMax;

    private Integer waistMin;

    private Integer hipMax;

    private Integer hipMin;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public Integer getBreathRateMax() {
        return breathRateMax;
    }

    public void setBreathRateMax(Integer breathRateMax) {
        this.breathRateMax = breathRateMax;
    }

    public Integer getBreathRateMin() {
        return breathRateMin;
    }

    public void setBreathRateMin(Integer breathRateMin) {
        this.breathRateMin = breathRateMin;
    }

    public Integer getSystolicMax() {
        return systolicMax;
    }

    public void setSystolicMax(Integer systolicMax) {
        this.systolicMax = systolicMax;
    }

    public Integer getSystolicMin() {
        return systolicMin;
    }

    public void setSystolicMin(Integer systolicMin) {
        this.systolicMin = systolicMin;
    }

    public Integer getDiastolicMax() {
        return diastolicMax;
    }

    public void setDiastolicMax(Integer diastolicMax) {
        this.diastolicMax = diastolicMax;
    }

    public Integer getDiastolicMin() {
        return diastolicMin;
    }

    public void setDiastolicMin(Integer diastolicMin) {
        this.diastolicMin = diastolicMin;
    }

    public Integer getHeartRateMax() {
        return heartRateMax;
    }

    public void setHeartRateMax(Integer heartRateMax) {
        this.heartRateMax = heartRateMax;
    }

    public Integer getHeartRateMin() {
        return heartRateMin;
    }

    public void setHeartRateMin(Integer heartRateMin) {
        this.heartRateMin = heartRateMin;
    }

    public Integer getSpo2Max() {
        return spo2Max;
    }

    public void setSpo2Max(Integer spo2Max) {
        this.spo2Max = spo2Max;
    }

    public Integer getSpo2Min() {
        return spo2Min;
    }

    public void setSpo2Min(Integer spo2Min) {
        this.spo2Min = spo2Min;
    }

    public Integer getSleepTimeMax() {
        return sleepTimeMax;
    }

    public void setSleepTimeMax(Integer sleepTimeMax) {
        this.sleepTimeMax = sleepTimeMax;
    }

    public Integer getSleepTimeMin() {
        return sleepTimeMin;
    }

    public void setSleepTimeMin(Integer sleepTimeMin) {
        this.sleepTimeMin = sleepTimeMin;
    }

    public Integer getBgMax() {
        return bgMax;
    }

    public void setBgMax(Integer bgMax) {
        this.bgMax = bgMax;
    }

    public Integer getBgMin() {
        return bgMin;
    }

    public void setBgMin(Integer bgMin) {
        this.bgMin = bgMin;
    }

    public Integer getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Integer temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Integer getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Integer temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getUricAcidMax() {
        return uricAcidMax;
    }

    public void setUricAcidMax(Integer uricAcidMax) {
        this.uricAcidMax = uricAcidMax;
    }

    public Integer getUricAcidMin() {
        return uricAcidMin;
    }

    public void setUricAcidMin(Integer uricAcidMin) {
        this.uricAcidMin = uricAcidMin;
    }

    public Integer getBloodFatMax() {
        return bloodFatMax;
    }

    public void setBloodFatMax(Integer bloodFatMax) {
        this.bloodFatMax = bloodFatMax;
    }

    public Integer getBloodFatMin() {
        return bloodFatMin;
    }

    public void setBloodFatMin(Integer bloodFatMin) {
        this.bloodFatMin = bloodFatMin;
    }

    public Integer getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(Integer weightMax) {
        this.weightMax = weightMax;
    }

    public Integer getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(Integer weightMin) {
        this.weightMin = weightMin;
    }

    public Integer getWaistMax() {
        return waistMax;
    }

    public void setWaistMax(Integer waistMax) {
        this.waistMax = waistMax;
    }

    public Integer getWaistMin() {
        return waistMin;
    }

    public void setWaistMin(Integer waistMin) {
        this.waistMin = waistMin;
    }

    public Integer getHipMax() {
        return hipMax;
    }

    public void setHipMax(Integer hipMax) {
        this.hipMax = hipMax;
    }

    public Integer getHipMin() {
        return hipMin;
    }

    public void setHipMin(Integer hipMin) {
        this.hipMin = hipMin;
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