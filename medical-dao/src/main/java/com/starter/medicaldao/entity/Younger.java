package com.starter.medicaldao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Younger {
    private String id;

    private String phone;

    private String pwd;

    private String identity;

    private String name;

    private Integer birth;

    private Integer sex;

    private String image;

    private Date createTime;

    private Date modifyTime;

    private String birthString;

}