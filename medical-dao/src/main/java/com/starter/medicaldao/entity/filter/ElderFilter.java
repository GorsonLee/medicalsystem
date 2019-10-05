package com.starter.medicaldao.entity.filter;

import lombok.Data;

@Data
public class ElderFilter extends CommonFilter {

    private Integer provideState;

    private String agencyId;

    private Integer birth;

}
