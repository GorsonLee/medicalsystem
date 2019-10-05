package com.starter.medicaldao.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class DetailYougerDto {

    private String id;

    private String phone;

    private String pwd;

    private String identity;

    private String name;

    private Integer birth;

    private Integer sex;

    private String image;

    private String createTimeStr;

    private List<ElderDto> elderList;
}
