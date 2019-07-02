package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Reservation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReservationMapper {
    int insert(Reservation record);

    int insertSelective(Reservation record);

    Reservation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Reservation record);

    int updateByPrimaryKey(Reservation record);

    List<Reservation> selectByUserId(@Param("userId") String userId);

    List<Reservation> selectByDoctorId(@Param("doctorId") String doctorId);

}