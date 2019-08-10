package com.starter.medicaldao.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewDto {
    private String id;

    private String title;

    private String content;

    private Integer type;

    private String createTimeStr;


}