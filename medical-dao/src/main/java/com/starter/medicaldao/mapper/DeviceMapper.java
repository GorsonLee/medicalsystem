package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    Device selectByUserIdAndDeviceId(@Param("userId") String userId,
                                     @Param("deviceId") String deviceId);

    List<Device> selectByUserId(@Param("userId") String userId);

    List<Device> selectByType(@Param("type") String type, @Param("offset") Integer offset,
                              @Param("pageSize") Integer pageSize);
}