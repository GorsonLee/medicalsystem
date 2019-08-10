package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class CareDto {
    private String id;

    private String title;

    private String content;

    private String createTimeStr;

}