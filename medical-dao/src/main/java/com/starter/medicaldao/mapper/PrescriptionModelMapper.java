package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.PrescriptionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionModelMapper {
    int insert(PrescriptionModel record);

    int insertSelective(PrescriptionModel record);

    PrescriptionModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrescriptionModel record);

    int updateByPrimaryKey(PrescriptionModel record);

    List<PrescriptionModel> selectByDoctorId(@Param("doctorId") String doctorId);
}