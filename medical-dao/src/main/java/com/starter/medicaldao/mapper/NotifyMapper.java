package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Notify;

public interface NotifyMapper {
    int insert(Notify record);

    int insertSelective(Notify record);

    Notify selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}