package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Elder;
import com.starter.medicaldao.entity.Hospital;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HospitalMapper {
    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);

    List<Hospital> queryList(@Param("province") String province,
                          @Param("city") String city,
                          @Param("country") String country,
                          @Param("town") String town,
                          @Param("community") String community,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize);
}