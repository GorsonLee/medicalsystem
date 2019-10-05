package com.starter.medicaldao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BodySign {
    private String id;

    private String userId;

    private String bodySign;

    private String content;

    private String description;

    private Date createTime;

    private Date modifyTime;
}