package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Care;

public interface CareMapper {
    int insert(Care record);

    int insertSelective(Care record);

    Care selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Care record);

    int updateByPrimaryKey(Care record);
}