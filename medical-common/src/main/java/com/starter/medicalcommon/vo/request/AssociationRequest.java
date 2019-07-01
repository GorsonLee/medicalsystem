package com.starter.medicalcommon.vo.request;

import lombok.Data;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 20:45
 **/
@Data
public class AssociationRequest {
    //关联者ID
    String userId;
    //被关联者手机号
    String associatePhone;
    //被关联者名字
    String associateName;
    //被关联者身份证
    String associateIdentity;
}