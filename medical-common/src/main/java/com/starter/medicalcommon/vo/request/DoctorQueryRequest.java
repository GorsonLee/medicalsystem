package com.starter.medicalcommon.vo.request;

import lombok.Data;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-07-13 18:09
 **/
@Data
public class DoctorQueryRequest {
    String hospital;
    String department;
    String secondDepartment;
}
