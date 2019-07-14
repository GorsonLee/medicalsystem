package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Hospital;

public interface HospitalMapper {
    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);
}