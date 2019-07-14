package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.New;

public interface NewMapper {
    int insert(New record);

    int insertSelective(New record);

    New selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(New record);

    int updateByPrimaryKey(New record);
}