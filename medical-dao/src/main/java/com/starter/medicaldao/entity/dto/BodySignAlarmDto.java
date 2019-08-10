package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class BodySignAlarmDto {
    private String id;

    private String doctorId;

    private String doctorName;

    private String doctorPhone;

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

    private String createTimeStr;
}