package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Event;

public interface EventMapper {
    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);
}