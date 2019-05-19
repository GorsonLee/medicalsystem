package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}