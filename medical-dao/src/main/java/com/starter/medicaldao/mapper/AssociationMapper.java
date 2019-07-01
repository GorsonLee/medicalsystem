package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Association;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssociationMapper {
    int insert(Association record);

    int insertSelective(Association record);

    Association selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Association record);

    int updateByPrimaryKey(Association record);

    List<Association> selectByUserId(@Param("userId") String userId);

    List<Association> selectByAssociateUserId(@Param("associateUserId") String associateUserId);

}