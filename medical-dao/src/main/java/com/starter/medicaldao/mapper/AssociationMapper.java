package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Association;

public interface AssociationMapper {
    int insert(Association record);

    int insertSelective(Association record);

    Association selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Association record);

    int updateByPrimaryKey(Association record);
}