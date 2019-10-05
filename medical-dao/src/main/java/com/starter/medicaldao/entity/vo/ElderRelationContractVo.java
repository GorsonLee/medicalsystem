package com.starter.medicaldao.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ElderRelationContractVo {
    private String id;

    private String phone;

    private String name;

    private Integer birth;

    private Integer sex;

    private String nation;

    private Integer provideState;

    private String agencyId;

    private Date createTime;

    // 关联子女
    private String relationChildId;

    // 签约医生
    private String doctorId;

    private Integer auditStatus;
}
