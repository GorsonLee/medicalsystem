package com.starter.medicaldao.mapper;

import com.starter.medicaldao.entity.Reservation;

public interface ReservationMapper {
    int insert(Reservation record);

    int insertSelective(Reservation record);

    Reservation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Reservation record);

    int updateByPrimaryKey(Reservation record);
}