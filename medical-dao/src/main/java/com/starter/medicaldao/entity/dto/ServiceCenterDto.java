package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class ServiceCenterDto {
    private String id;

    private Integer helpType;

    private String userId;

    private String userPhone;

    private String userName;

    private String type;

    private Integer state;

    private String requirement;

    private String price;

    private String serviceTimeStr;

    private String managerId;

    private String managerPhone;

    private String managerName;

    private Integer serviceStar;

    private String finishTimeStr;

    private String recordUrl;

    private Integer evaluateStar;

    private String evaluateContent;

    private String evaluateTimeStr;

    private String createTimeStr;

}