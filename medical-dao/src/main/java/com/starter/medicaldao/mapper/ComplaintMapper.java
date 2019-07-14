package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Complaint;

public interface ComplaintMapper {
    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);
}