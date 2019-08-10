package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class ComplaintDto {
    private String id;

    private String complainant;

    private String name;

    private String phoe;

    private String content;

    private String createTimeStr;

}