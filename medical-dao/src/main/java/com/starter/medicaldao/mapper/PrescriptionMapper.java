package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Prescription;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionMapper {
    int insert(Prescription record);

    int insertSelective(Prescription record);

    Prescription selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Prescription record);

    int updateByPrimaryKey(Prescription record);

    List<Prescription> selectByUserId(@Param("userId") String userId);

}