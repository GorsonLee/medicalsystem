package com.starter.medicaldao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Doctor {
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

    private Date createTime;

    private Date modifyTime;

    private String birthString;

}