package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Younger;
import org.apache.ibatis.annotations.Param;

public interface YoungerMapper {
    int insert(Younger record);

    int insertSelective(Younger record);

    Younger selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Younger record);

    int updateByPrimaryKey(Younger record);

    Younger selectByPhone(@Param("phone") String phone);

    Younger selectByPhoneAndPwd(@Param("phone") String phone,
                                @Param("pwd") String pwd);
}