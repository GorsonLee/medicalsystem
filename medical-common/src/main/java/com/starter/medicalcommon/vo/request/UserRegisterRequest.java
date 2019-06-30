package com.starter.medicalcommon.vo.request;

import lombok.Data;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-05-19 20:45
 **/
@Data
public class UserRegisterRequest {
    private String phone;
    private String pwd;
    private String identity;
    private String name;
}
