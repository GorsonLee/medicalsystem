package com.starter.medicaldao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Elder {
    private String id;

    private String phone;

    private String pwd;

    private String identity;

    private String name;

    private Integer birth;

    private Integer sex;

    private String nation;

    private String image;

    private Integer oldType;

    private Integer maritalStatus;

    private Integer liveStatus;

    private String address;

    private String province;

    private String city;

    private String country;

    private String town;

    private String community;

    private String agencyId;

    private String emergencyContact;

    private String emergencyPhone;

    private Integer provideState;

    private Integer verifyState;

    private Date createTime;

    private Date modifyTime;

    private String birthString;
}