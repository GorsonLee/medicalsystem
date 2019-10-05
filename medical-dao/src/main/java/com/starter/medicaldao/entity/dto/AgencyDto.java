package com.starter.medicaldao.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AgencyDto {
    private String id;

    private String name;

    private String type;

    private String address;

    private String province;

    private String city;

    private String createTimeStr;

    private Integer elderCount;
}