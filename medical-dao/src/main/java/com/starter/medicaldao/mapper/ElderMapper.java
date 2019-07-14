package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Elder;
import org.apache.ibatis.annotations.Param;

public interface ElderMapper {
    int insert(Elder record);

    int insertSelective(Elder record);

    Elder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Elder record);

    int updateByPrimaryKey(Elder record);

    Elder selectByPhone(@Param("phone") String phone);

    Elder selectByPhoneAndPwd(@Param("phone") String phone,
                              @Param("pwd") String pwd);

    Elder selectByPhoneAndNameAndIdentity(@Param("phone") String phone,
                                          @Param("name") String name,
                                          @Param("identity") String identity);

    int countByOrganizationId(@Param("organizationId") String organizationId);
}