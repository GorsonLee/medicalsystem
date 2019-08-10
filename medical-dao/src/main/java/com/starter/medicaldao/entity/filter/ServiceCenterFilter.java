package com.starter.medicaldao.entity.filter;

import lombok.Data;

import java.util.List;

@Data
public class ServiceCenterFilter extends CommonFilter {

    private Integer helpType;
    private List<Integer> stateList;
    private String managerId;
    private String province;

    private String city;

    private String country;

    private String town;

    private String community;

}
