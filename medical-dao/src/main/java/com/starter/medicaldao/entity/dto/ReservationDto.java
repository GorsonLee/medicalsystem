package com.starter.medicaldao.entity.dto;

import lombok.Data;

@Data
public class ReservationDto {

    private String userName;

    private String userPhone;

    private String doctorName;

    private String doctorPhone;

    private String instructions;

    private Integer state;

    private String reserveTimeStr;

}
