package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.BodySign;

public interface BodySignMapper {
    int insert(BodySign record);

    int insertSelective(BodySign record);

    BodySign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BodySign record);

    int updateByPrimaryKey(BodySign record);
}