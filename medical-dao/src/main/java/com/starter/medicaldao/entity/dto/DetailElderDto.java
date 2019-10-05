package com.starter.medicaldao.entity.dto;

import com.starter.medicaldao.entity.BodySign;
import com.starter.medicaldao.entity.HealthDocument;
import lombok.Data;

import java.util.List;

@Data
public class DetailElderDto {

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

    private String agencyName;

    private String emergencyContact;

    private String emergencyPhone;

    private Integer provideState;

    private Integer verifyState;

    // 被关联列表
    List<RelationDto> relationList;

    // 签约医生列表
    List<DoctorDtos> doctorList;

    // 生命体征
    List<BodySign> bodySignList;

    // 健康档案
    List<HealthDocument> healthDocumentList;
}
