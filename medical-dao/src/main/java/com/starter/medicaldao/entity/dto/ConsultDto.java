package com.starter.medicaldao.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultDto {
    private String userName;

    private String userPhone;

    private String doctorName;

    private String doctorPhone;

    private String symptoms;

    private String description;

    private String recordUrls;

    private Integer state;

    private String createTimeStr;

}