package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class PrescriptionDto {
    private String userName;

    private String userPhone;

    private String doctorName;

    private String doctorPhone;

    private String title;

    private String content;

    private String createTimeStr;
}