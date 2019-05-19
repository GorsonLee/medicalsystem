package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Doctor;

public interface DoctorMapper {
    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);
}