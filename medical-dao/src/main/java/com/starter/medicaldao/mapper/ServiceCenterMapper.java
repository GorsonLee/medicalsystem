package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.ServiceCenter;

public interface ServiceCenterMapper {
    int insert(ServiceCenter record);

    int insertSelective(ServiceCenter record);

    ServiceCenter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceCenter record);

    int updateByPrimaryKey(ServiceCenter record);
}