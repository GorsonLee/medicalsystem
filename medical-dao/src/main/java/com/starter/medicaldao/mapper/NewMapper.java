package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Care;
import com.starter.medicaldao.entity.New;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewMapper {
    int insert(New record);

    int insertSelective(New record);

    New selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(New record);

    int updateByPrimaryKey(New record);

    int deleteByPrimaryKey(@Param("id") String id);

    List<New> selectByType(@Param("type") Integer type, @Param("offset") Integer offset,
                         @Param("pageSize") Integer pageSize);
}