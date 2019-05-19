package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Consult;

public interface ConsultMapper {
    int insert(Consult record);

    int insertSelective(Consult record);

    Consult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Consult record);

    int updateByPrimaryKey(Consult record);
}