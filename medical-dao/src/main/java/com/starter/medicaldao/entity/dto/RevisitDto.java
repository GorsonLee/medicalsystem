package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class RevisitDto {
    private String userName;

    private String userPhone;

    private String doctorName;

    private String doctorPhone;

    private String method;

    private String content;

    private String visitTimeStr;

}