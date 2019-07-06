package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Revisit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RevisitMapper {
    int insert(Revisit record);

    int insertSelective(Revisit record);

    Revisit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Revisit record);

    int updateByPrimaryKey(Revisit record);

    List<Revisit> selectByDoctorId(@Param("doctorId") String doctorId);
}