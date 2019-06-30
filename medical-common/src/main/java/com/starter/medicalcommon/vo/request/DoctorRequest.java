package com.starter.medicalcommon.vo.request;

import lombok.Data;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 20:45
 **/
@Data
public class DoctorRequest {
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
}
