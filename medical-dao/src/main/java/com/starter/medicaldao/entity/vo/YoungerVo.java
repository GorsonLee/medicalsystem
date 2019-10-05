package com.starter.medicaldao.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class YoungerVo {
    private String id;

    private String phone;

    private String name;

    private Integer birth;

    private Integer sex;

    private Date createTime;

    private Integer auditStatus;
}
