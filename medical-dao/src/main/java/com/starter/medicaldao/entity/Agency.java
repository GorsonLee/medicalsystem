package com.starter.medicaldao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
public class Agency {
    private String id;

    @NotBlank(message = "机构名称不能为空")
    private String name;

    @NotBlank(message = "机构类型不能为空")
    private String type;

    private String address;

    @NotBlank(message = "机构省份不能为空")
    private String province;

    @NotBlank(message = "机构城市不能为空")
    private String city;

    private Date createTime;

    private Date modifyTime;
}