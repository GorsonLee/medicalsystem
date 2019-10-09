package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.ServiceCenter;
import com.starter.medicaldao.entity.filter.ServiceCenterFilter;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceCenterMapper {
    int insert(ServiceCenter record);

    int insertSelective(ServiceCenter record);

    int deleteByPrimaryKey(@Param("id") String id);

    ServiceCenter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ServiceCenter record);

    int updateByPrimaryKey(ServiceCenter record);

    List<ServiceCenter> selectBydState(@Param("state") Integer state);

    List<ServiceCenter> selectByUserIdAndState(@Param("userId") String userId,
                                               @Param("state") Integer state);

    List<ServiceCenter> selectByManagerIdAndState(@Param("managerId") String managerId,
                                                  @Param("state") Integer state);

    Integer selectServiceCountByFilter(@Param("filter") ServiceCenterFilter filter);

    BigDecimal selectAvgStarCountByFilter(@Param("filter") ServiceCenterFilter filter);

    List<ServiceCenter> selectByFilter(@Param("filter") ServiceCenterFilter filter);

    int selectCountByFilter(@Param("filter") ServiceCenterFilter filter);
}