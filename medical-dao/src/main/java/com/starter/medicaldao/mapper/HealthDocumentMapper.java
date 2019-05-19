package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.HealthDocument;

public interface HealthDocumentMapper {
    int insert(HealthDocument record);

    int insertSelective(HealthDocument record);

    HealthDocument selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HealthDocument record);

    int updateByPrimaryKey(HealthDocument record);
}