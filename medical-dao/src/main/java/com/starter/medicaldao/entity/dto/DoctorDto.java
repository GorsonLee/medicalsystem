package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private String id;

    private String phone;

    private String pwd;

    private String identity;

    private String name;

    private Integer sex;

    private Integer birth;

    private String hospital;

    private String department;

    private String secondDepartment;

    private String jobTitle;

    private String introduction;

    private String skill;

    private String createTimeStr;

    private Integer elderCount;

}
