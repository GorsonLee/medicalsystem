package com.starter.medicaldao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Association {
    private String id;

    private String userId;

    private String associateUserId;

    private Integer associateRole;

    private Integer state;

    private Date createTime;

    private Date modifyTime;

}