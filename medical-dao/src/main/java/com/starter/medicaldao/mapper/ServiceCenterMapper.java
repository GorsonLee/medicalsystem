package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.ServiceCenter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceCenterMapper {
    int insert(ServiceCenter record);

    int insertSelective(ServiceCenter record);

    ServiceCenter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceCenter record);

    int updateByPrimaryKey(ServiceCenter record);

    List<ServiceCenter> selectBydState(@Param("state") Integer state);

    List<ServiceCenter> selectByUserIdAndState(@Param("userId") String userId,
                                               @Param("state") Integer state);

    List<ServiceCenter> selectByManagerIdAndState(@Param("managerId") String managerId,
                                                  @Param("state") Integer state);
}