package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class DeviceDto {
    private String id;

    private String userId;

    private String userPhone;

    private String userName;

    private String deviceId;

    private String category;

    private String type;

    private Integer state;

    private String createTimeStr;

}