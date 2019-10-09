package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Agency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyMapper {
    int insert(Agency record);

    int insertSelective(Agency record);

    Agency selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Agency record);

    int updateByPrimaryKey(Agency record);

    int deleteByPrimaryKey(@Param("id") String id);

    List<Agency> selectByParams(@Param("province") String province,
                                @Param("city") String city,
                                @Param("name") String name,
                                @Param("offset") Integer offset,
                                @Param("pageSize") Integer pageSize);

    List<Agency> selectByParamss(@Param("province") String province,
                                @Param("city") String city,
                                @Param("type") String agencyType,
                                @Param("offset") Integer offset,
                                @Param("pageSize") Integer pageSize);

    Integer selectCountByParamss(@Param("province") String province,
                                 @Param("city") String city,
                                 @Param("type") String agencyType);

    List<Agency> selectAgencyTypeList();

    List<Agency> selectAgencyIdList();
}