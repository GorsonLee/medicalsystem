package com.starter.medicaldao.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ManagerDto {
    private String id;

    private String phone;

    private String pwd;

    private String identity;

    private String name;

    private Integer birth;

    private Integer sex;

    private String image;
    private String province;
    private String city;
    private String country;
    private String town;
    private String community;
    private String address;

    private String createTimeStr;
    private Integer serviceCount = 0;
    private Integer helpCount = 0;
    private BigDecimal starCount = BigDecimal.ZERO;
    private Integer serviceState = 0; // 0-空闲 1-正在帮扶 2-正在服务 3-同时在帮扶与服务

}