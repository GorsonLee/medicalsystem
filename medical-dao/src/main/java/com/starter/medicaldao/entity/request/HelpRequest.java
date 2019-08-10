package com.starter.medicaldao.entity.request;

import lombok.Data;

@Data
public class HelpRequest {

    private String userPhone;

    private String managerPhone;

    private String serviceTime;

    private String requirement;
}
