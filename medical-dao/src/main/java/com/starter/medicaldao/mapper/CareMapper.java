package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Care;
import com.starter.medicaldao.entity.Complaint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CareMapper {
    int insert(Care record);

    int insertSelective(Care record);

    Care selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Care record);

    int updateByPrimaryKey(Care record);

    int deleteByPrimaryKey(@Param("id") String id);

    List<Care> selectAll(@Param("offset") Integer offset,
                              @Param("pageSize") Integer pageSize);
}