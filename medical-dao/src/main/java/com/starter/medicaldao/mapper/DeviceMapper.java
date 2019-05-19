package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Device;

public interface DeviceMapper {
    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}