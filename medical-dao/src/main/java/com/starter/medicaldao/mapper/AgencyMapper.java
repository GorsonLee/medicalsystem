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

    List<Agency> selectByParams(@Param("province") String province,
                                @Param("city") String city,
                                @Param("name") String name,
                                @Param("offset") Integer offset,
                                @Param("pageSize") Integer pageSize);
}